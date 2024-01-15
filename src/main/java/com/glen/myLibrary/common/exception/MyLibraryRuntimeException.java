package com.glen.myLibrary.common.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class MyLibraryRuntimeException extends RuntimeException{

    private String messageKey;

    private Object[] messageParameter;

    private Map<String, String> validation = new HashMap<>();

    public MyLibraryRuntimeException(String messageKey) {
        this.messageKey = messageKey;
        this.messageParameter = null;
    }

    public MyLibraryRuntimeException(String messageKey, Object[] messageParameter) {
        super(messageKey);
        this.messageKey = messageKey;
        this.messageParameter = messageParameter;
    }

    public MyLibraryRuntimeException(String messageKey, Throwable cause){
        super(messageKey, cause);
    }

    public MyLibraryRuntimeException(String message, Throwable cause, String messageKey, Object[] messageParameter) {
        super(message, cause);
        this.messageKey = messageKey;
        this.messageParameter = messageParameter;
    }

    public MyLibraryRuntimeException() {
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String errorMessage){
        validation.put(fieldName, errorMessage);
    }
}
