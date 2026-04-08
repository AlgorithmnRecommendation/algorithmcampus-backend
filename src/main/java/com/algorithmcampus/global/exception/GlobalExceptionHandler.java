package com.algorithmcampus.global.exception;

import com.algorithmcampus.global.exception.common.CommonError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        ErrorCode errorCode = e.getErrorCode();
        log.warn("CustomException: [{}] {}", errorCode.name(), e.getMessage());

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.of(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.warn("MethodArgumentNotValidException: {}", e.getMessage());
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse(CommonError.INVALID_INPUT_VALUE.getMessage());

        return ResponseEntity.status(CommonError.INVALID_INPUT_VALUE.getHttpStatus())
                .body(ErrorResponse.of(CommonError.INVALID_INPUT_VALUE, message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        log.error("Exception: {}", e.getMessage(),e);
        return ResponseEntity.status(CommonError.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ErrorResponse.of(CommonError.INTERNAL_SERVER_ERROR));
    }
}
