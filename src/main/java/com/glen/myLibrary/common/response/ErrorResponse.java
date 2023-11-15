package com.glen.myLibrary.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * {
 *     "code" : "400",
 *     "message" : "잘못된 요청입니다.",
 *     "validation" : {
 *         "title" : "필수값입니다"
 *     }
 * }
 *
 * */
@Getter
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY) //공란은 보내지 않는다
public class ErrorResponse {

    private final String code;
    private final String message;

    private Map<String, String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation;
    }

    public void addValidation(String fieldName, String errorMessage){
        if(validation==null){
            validation = new HashMap<>();
        }
        this.validation.put(fieldName, errorMessage);
    }
}
