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
import ru.digitalspirit.asaka.setstatus.model.SetStatusProcessRequestDTO;
import ru.digitalspirit.asaka.setstatus.service.SetStatusProcessService;

import javax.validation.Valid;

@Slf4j(topic = "SetStatusProcessController")
@RestController
public class SetStatusProcessController {

    SetStatusProcessService setStatusProcessService;

    public SetStatusProcessController(SetStatusProcessService setStatusProcessService) {
        this.setStatusProcessService = setStatusProcessService;
    }

    @ApiOperation(value = "Старт BPM процесса для изменения стуса заявки", notes = "", response = CommonResponseDTO.class, tags={ "bpm" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CommonResponseDTO.class)})
    @RequestMapping(value = "/process/set-status",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<CommonResponseDTO> startProcess(@ApiParam(value = "Входные параметры для процесса" ,required=true )
                                                           @Valid @RequestBody SetStatusProcessRequestDTO body) {
        log.debug("################################# START SET STATUS PROCESS SERVICE: Request = " + body);
        CommonResponseDTO response = new CommonResponseDTO();
        String appId = setStatusProcessService.startProcess(body);
        response.setCode("0");
        return new ResponseEntity<CommonResponseDTO>(response, HttpStatus.OK);
    }
}
