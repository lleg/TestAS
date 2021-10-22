package ru.digitalspirit.asaka.services.startprocess.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.digitalspirit.asaka.bpm.service.BpmService;
import ru.digitalspirit.asaka.services.startprocess.model.StartProcessRequestDTO;

import java.util.HashMap;
import java.util.Map;

@Service("asakaStartProcessService")
public class StartProcessService {

    private BpmService bpmServiceImpl;

    private static final Logger log = LoggerFactory.getLogger(StartProcessService.class);

    public String startProcess(StartProcessRequestDTO inputData, String processName){
        String appId = inputData.getApplicationId();
        log.debug("################################# StartProcessService: Application ID = " + appId);
      //  ApplicationEntity applicationEntity = applicationService.findByAppNumber(appNumber);
//        if(applicationEntity == null){
//            throw new NotFoundException("Application with Number = " + appNumber + " not found!");
//        }
//        if(!applicationEntity.getStatus().equals(APP_NEW_STATUS)){
//            throw new DuplicateApplicationException("Business Process for applicationNumber = " + appNumber + " has started already!");
//        }


        String processId = bpmServiceImpl.startApplication(processName, getProcessInput(appId));
        log.debug("################################# StartProcessService: Process ID = " + processId);
        return appId;
    }

    private Map<String, Object> getProcessInput(String applicationId) {
        Map<String, Object> applicationInput = new HashMap<>();
        applicationInput.put("applicationId", applicationId);
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("applicationInput", applicationInput);
        return inputData;
    }

}
