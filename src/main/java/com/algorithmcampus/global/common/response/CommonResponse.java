package com.algorithmcampus.global.common.response;

import com.algorithmcampus.global.common.response.code.CommonSuccessCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponse<T> {

    private String code;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(CommonSuccessCode successCode, T data){
        CommonResponse<T> response = new CommonResponse<>();
        response.code = successCode.getCode();
        response.message = successCode.getMessage();
        response.data = data;
        return response;
    }

    public static <T> CommonResponse<T> success(CommonSuccessCode successCode){
        return success(successCode, null);
    }
}
