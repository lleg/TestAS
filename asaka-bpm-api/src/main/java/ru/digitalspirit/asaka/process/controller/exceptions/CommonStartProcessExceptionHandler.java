package ru.digitalspirit.asaka.process.controller.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.digitalspirit.asaka.common.exceptions.NotFoundException;
import ru.digitalspirit.asaka.startprocess.model.StartProcessResponseDTO;


@ControllerAdvice("ru.digitalspirit.asaka.process.controller")
@Slf4j(topic = "StartProcessController")
public class CommonStartProcessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<StartProcessResponseDTO> handleInvalidInputException(
            NotFoundException ex) {
        StartProcessResponseDTO response = new StartProcessResponseDTO();
        response.setCode("11");
        response.setMessage(ex.getMessage());
        log.error("###################### START PROCESS SERVICE Error.", ex);
        return buildResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<StartProcessResponseDTO> handleCommonException(
            Exception ex) {
        StartProcessResponseDTO response = new StartProcessResponseDTO();
        response.setCode("12");
        response.setMessage(ex.getMessage());
        log.error("###################### START PROCESS SERVICE Error.", ex);
        return buildResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<StartProcessResponseDTO> buildResponseEntity(StartProcessResponseDTO apiError, HttpStatus status) {
        return new ResponseEntity<>(apiError, status);
    }
}
