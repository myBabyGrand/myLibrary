package com.glen.myLibrary.common.response;


import lombok.Builder;

public class MyLibraryResponse {

    Object Data;

    ErrorResponse errorResponse;

    @Builder
    public MyLibraryResponse(Object data, ErrorResponse errorResponse) {
        Data = data;
        this.errorResponse = errorResponse;
    }
}
