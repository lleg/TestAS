package ru.digitalspirit.asaka.startprocess.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalspirit.asaka.bpm.entity.ApplicationEntity;
import ru.digitalspirit.asaka.bpm.service.BpmService;
import ru.digitalspirit.asaka.common.exceptions.NotFoundException;
import ru.digitalspirit.asaka.orm.service.microloan.ApplicationService;
import ru.digitalspirit.asaka.startprocess.model.StartProcessRequestDTO;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service("asakaStartProcessService")
public class StartProcessService {

    private static final String APP_STATUS_NEW = "NEW";

    @Autowired
    private BpmService bpmServiceImpl;

    @Autowired
    ApplicationService applicationService;

    private static final Logger log = LoggerFactory.getLogger(StartProcessService.class);

    public String startProcess(StartProcessRequestDTO inputData){
        String appId = inputData.getApplicationId();
        String claimNumBpm = inputData.getClaimNumBpm();
        String process = inputData.getProcessName();
        log.debug("################################# StartProcessService: START PROCESS " + process + " for Application ID = " + appId);
        ApplicationEntity applicationEntity = applicationService.findByApplicationId(new BigInteger(appId));
        if(!applicationService.existsByApplicationNumber(claimNumBpm)){
            throw new NotFoundException("Application with claimNumBpm = " + claimNumBpm + " not found!");
        }

        String processId = bpmServiceImpl.startApplication(process, getProcessInput(appId, claimNumBpm));
        log.debug("################################# StartProcessService: Process ID = " + processId);
        applicationEntity.setStatus(APP_STATUS_NEW);
        applicationService.save(applicationEntity);
        return appId;
    }

    private Map<String, Object> getProcessInput(String applicationId, String claimNumBpm) {
        Map<String, Object> applicationInput = new HashMap<>();
        applicationInput.put("applicationId", applicationId);
        applicationInput.put("claimNumBpm", claimNumBpm);
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("applicationInput", applicationInput);
        return inputData;
    }

}
