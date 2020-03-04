package com.ligz.questionnaire.exception;

import com.ligz.questionnaire.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<CommonResult> handleOtherException(Throwable e) {
        log.error(String.format("System error, error info={}", e.getMessage()), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonResult.failed("UNKNOWN FAILED"));
    }

}
