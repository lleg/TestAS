package ru.digitalspirit.asaka.process.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalspirit.asaka.common.dto.CommonResponseDTO;
import ru.digitalspirit.asaka.startprocess.model.StartProcessRequestDTO;
import ru.digitalspirit.asaka.startprocess.service.StartProcessService;

@Slf4j(topic = "StartProcessController")
@RestController
public class StartProcessController {

    StartProcessService startProcessService;

    public StartProcessController(StartProcessService startProcessService) {
        this.startProcessService = startProcessService;
    }

    @ApiOperation(value = "Старт BPM процесса", notes = "", response = CommonResponseDTO.class, tags={ "bpm" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CommonResponseDTO.class)})
    @RequestMapping(value = "/process/start",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<CommonResponseDTO> startProcess(@ApiParam(value = "Входные параметры для процесса" ,required=true )
                                                            @RequestBody StartProcessRequestDTO body) {
        log.debug("################################# START PROCESS SERVICE: Request = " + body);
        CommonResponseDTO response = new CommonResponseDTO();
        String appId = startProcessService.startProcess(body);
        response.setCode("0");
        return new ResponseEntity<CommonResponseDTO>(response, HttpStatus.OK);
    }
}
