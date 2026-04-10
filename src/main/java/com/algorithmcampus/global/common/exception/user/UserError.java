package com.algorithmcampus.global.common.exception.user;

import com.algorithmcampus.global.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum UserError implements ErrorCode {
    ;

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }

    @Override
    public String getMessage() {
        return "";
    }
}
