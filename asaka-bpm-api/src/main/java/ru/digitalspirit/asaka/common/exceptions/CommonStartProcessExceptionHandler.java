package ru.digitalspirit.asaka.common.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.digitalspirit.asaka.common.dto.CommonResponseDTO;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice("ru.digitalspirit.asaka.process.controller")
@Slf4j(topic = "CommonStartProcessExceptionHandler")
public class CommonStartProcessExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<CommonResponseDTO> handleInvalidInputException(
            NotFoundException ex) {
        CommonResponseDTO response = new CommonResponseDTO();
        response.setCode("11");
        response.setMessage(ex.getMessage());
        log.error("###################### ASAKA BPM PROCESS SERVICE Error.", ex);
        return buildResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String error = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        log.info("###################### ASAKA BPM PROCESS SERVICE Error. " + error);
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError->fieldError.getDefaultMessage()).collect(Collectors.toList());
        log.debug("###################### ASAKA BPM PROCESS SERVICE Error. ErrorList " + validationList);
        CommonResponseDTO response = new CommonResponseDTO();
        response.setCode("12");
        response.setMessage(error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponseDTO> handleCommonException(
            Exception ex) {
        CommonResponseDTO response = new CommonResponseDTO();
        response.setCode("12");
        response.setMessage(ex.getMessage());
        log.error("###################### ASAKA BPM PROCESS SERVICE Error.", ex);
        return buildResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    private ResponseEntity<CommonResponseDTO> buildResponseEntity(CommonResponseDTO apiError, HttpStatus status) {
        return new ResponseEntity<>(apiError, status);
    }
}
