package com.algorithmcampus.global.common.response.code;

public interface ResponseCode {
    String getCode();
    String getMessage();
    int getHttpStatus();
}
