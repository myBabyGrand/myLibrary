package com.glen.myLibrary.common.Exception;

import lombok.Getter;

@Getter
public class MyLibraryRuntimeException extends RuntimeException{

    private final String messageKey;

    private final Object[] messageParameter;

    public MyLibraryRuntimeException(String messageKey) {
        this.messageKey = messageKey;
        this.messageParameter = null;
    }

    public MyLibraryRuntimeException(String messageKey, Object[] messageParameter) {
        super(messageKey);
        this.messageKey = messageKey;
        this.messageParameter = messageParameter;
    }

}
