package com.glen.myLibrary.common.Exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends MyLibraryRuntimeException{

    public DataNotFoundException() {
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
