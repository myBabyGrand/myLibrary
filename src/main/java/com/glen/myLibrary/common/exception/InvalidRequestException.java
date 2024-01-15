package com.glen.myLibrary.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidRequestException extends MyLibraryRuntimeException{

    private static final String MESSAGE = "잘못된 요청자입니다.";

    public InvalidRequestException() {
    }

    public InvalidRequestException(String fieldName, String errorMessage) {
        super(MESSAGE);
        addValidation(fieldName, errorMessage);
    }

    public InvalidRequestException(String messageKey) {
        super(messageKey);
    }

    public InvalidRequestException(String messageKey, Object[] messageParameter) {
        super(messageKey, messageParameter);
    }

    public InvalidRequestException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
