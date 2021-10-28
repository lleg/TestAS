package ru.digitalspirit.asaka.setstatus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalspirit.asaka.bpm.entity.ApplicationEntity;
import ru.digitalspirit.asaka.bpm.service.BpmService;
import ru.digitalspirit.asaka.common.exceptions.NotFoundException;
import ru.digitalspirit.asaka.orm.service.microloan.ApplicationService;
import ru.digitalspirit.asaka.setstatus.model.SetStatusProcessRequestDTO;
import ru.digitalspirit.asaka.startprocess.model.StartProcessRequestDTO;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service("asakaSetStatusProcessService")
public class SetStatusProcessService {

    @Autowired
    private BpmService bpmServiceImpl;

    @Autowired
    private ApplicationService applicationService;

    private static final Logger log = LoggerFactory.getLogger(SetStatusProcessService.class);

    public String startProcess(SetStatusProcessRequestDTO inputData){
        String claimNumBpm = inputData.getClaimNumBpm();
        String process = "Set Application Status Start UCA";//inputData.getProcessName();
        log.debug("################################# SetStatusProcessService: START PROCESS " + process + " for ClaimNumBpm = " + claimNumBpm);

        if(!applicationService.existsByApplicationNumber(claimNumBpm)){
            throw new NotFoundException("Application with claimNumBpm = " + claimNumBpm + " not found!");
        }

        String processId = bpmServiceImpl.startApplication(process, getProcessInput(claimNumBpm, inputData.getStatus()));
        log.debug("################################# SetStatusProcessService: Process ID = " + processId);

        return processId;
    }

    private Map<String, Object> getProcessInput(String claimNumBpm, String status) {
        Map<String, Object> processInput = new HashMap<>();
        processInput.put("claimNumBpm", claimNumBpm);
        processInput.put("status", status);
        Map<String, Object> inputData = new HashMap<>();
        inputData.put("processInput", processInput);
        return inputData;
    }

}
