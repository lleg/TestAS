package ru.digitalspirit.asaka.bpm.service;

import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.digitalspirit.asaka.applicationlist.entity.BusinessProcess;
import ru.digitalspirit.asaka.applicationlist.repository.BusinessProcessRepository;
import ru.digitalspirit.asaka.bpm.dto.ProcessAppCheckDTO;
import ru.digitalspirit.asaka.bpm.dto.ProcessAppFullStartDTO;
import ru.digitalspirit.asaka.bpm.dto.TaskInfoDTO;
import ru.digitalspirit.asaka.bpm.model.BpmTaskInfo;
import ru.digitalspirit.asaka.bpm.model.UserContextData;
import ru.digitalspirit.asaka.config.enums.ParameterCode;
import ru.digitalspirit.asaka.config.service.ConfigParametersService;
import ru.digitalspirit.asaka.util.CoreUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(transactionManager = "refbookModelTransactionManager")
public class ProcessAppService {

    @Autowired
    private NbuBpmService bpmService;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private BusinessProcessRepository businessProcessRepository;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ConfigParametersService configParametersService;

    private static final String TASK_URL_PATTERN = "${frontBaseUrl}${businessProcessUrl}/?applicationId=${applicationId}&taskId=${taskId}";

    private static final Logger logger = LoggerFactory.getLogger(ProcessAppService.class);

    public TaskInfoDTO startProcess(ProcessAppFullStartDTO processAppStart) {
        logger.info("Start process request\n{}", CoreUtil.objectToString(processAppStart));
        BusinessProcess businessProcess = getBusinessProcess(processAppStart);
        UserContextData userContextData = mapperFacade.map(processAppStart, UserContextData.class);
        TaskInfoDTO task = startProcess(businessProcess, userContextData, processAppStart.getBpParams());
        logger.info("Start process response\n{}", CoreUtil.objectToString(task));
        return task;
    }

    public BusinessProcess getBusinessProcess(ProcessAppCheckDTO processAppStart) {
        if (!StringUtils.isEmpty(processAppStart.getBpCode())) {
            return businessProcessRepository.findOneByCode(processAppStart.getBpCode());
        } else if (!StringUtils.isEmpty(processAppStart.getBpId())) {
            return getById(Long.valueOf(processAppStart.getBpId()));
        } else if (!StringUtils.isEmpty(processAppStart.getBpSubjectId())) {
            return businessProcessRepository.findOneBySubjectId(processAppStart.getBpSubjectId());
        }
        throw new IllegalArgumentException("ID, Code and SubjectId are null!!!");
    }

    private TaskInfoDTO startProcess(BusinessProcess businessProcess, UserContextData userContextData, Map<String, Object> additionalInputParameters) {

        BpmTaskInfo task = bpmService.startProcess(businessProcess.getName(), businessProcess.getFirstTaskName(), userContextData, additionalInputParameters);
        TaskInfoDTO taskInfo = mapperFacade.map(task, TaskInfoDTO.class);
        if (taskInfo != null) {
            addTaskUrl(taskInfo, businessProcess);
        }
        return taskInfo;
    }

    public List<BusinessProcess> getAll() {
        return businessProcessRepository.findAll();
    }

    public BusinessProcess getById(Long id) {
        List<BusinessProcess> bp = businessProcessRepository.findAllById(Arrays.asList(id));
        return bp != null && !bp.isEmpty() ? bp.get(0) : null;
    }

    private void addTaskUrl(TaskInfoDTO task, BusinessProcess businessProcess) {
        Map<String, String> values = new HashMap<>();
        values.put("frontBaseUrl", getFrontBaseUrl());
        values.put("businessProcessUrl", businessProcess.getUrl());
        values.put("applicationId", task.getApplicationId());
        values.put("taskId", task.getId());
        StrSubstitutor sub = new StrSubstitutor(values, "${", "}");
        task.setUrl(sub.replace(TASK_URL_PATTERN));
    }

    private String getFrontBaseUrl() {
        return configParametersService.getParameterValue(ParameterCode.FRONT_BASE_URL);
    }
}
