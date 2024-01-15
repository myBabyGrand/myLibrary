package com.glen.myLibrary.common.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends MyLibraryRuntimeException{
    private static final String MESSAGE = "데이터가 존재하지 않습니다.";

    public DataNotFoundException() {
    }
    public DataNotFoundException(String fieldName, String errorMessage) {
        super(MESSAGE);
        addValidation(fieldName, errorMessage);
    }
    public DataNotFoundException(String messageKey) {
        super(messageKey);
    }

    public DataNotFoundException(String messageKey, Object[] messageParameter) {
        super(messageKey, messageParameter);
    }

    public DataNotFoundException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
