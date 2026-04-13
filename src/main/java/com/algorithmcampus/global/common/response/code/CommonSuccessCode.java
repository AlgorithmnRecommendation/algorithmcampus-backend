package com.algorithmcampus.global.common.response.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonSuccessCode implements ResponseCode {
    OK("S000","요청이 성공했습니다.", 200),
    CREATED("S001","리소스가 생성되었습니다.",201),
    NO_CONTENT("S002","처리가 완료되었습니다.",204);

    private final String code;
    private final String message;
    private final int httpStatus;
}
