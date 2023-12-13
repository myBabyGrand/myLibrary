package com.glen.myLibrary.common.Exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MyLibraryBizException extends RuntimeException{

    private String messageKey;

    private Object[] messageParameter;

    private Map<String, String> validation = new HashMap<>();
    public MyLibraryBizException(String messageKey) {
        this.messageKey = messageKey;
        this.messageParameter = null;
    }

    public MyLibraryBizException(String messageKey, Object[] messageParameter) {
        super(messageKey);
        this.messageKey = messageKey;
        this.messageParameter = messageParameter;
    }

    public MyLibraryBizException(String messageKey, Throwable cause){
        super(messageKey, cause);
    }

    public MyLibraryBizException(String message, Throwable cause, String messageKey, Object[] messageParameter) {
        super(message, cause);
        this.messageKey = messageKey;
        this.messageParameter = messageParameter;
    }
}
