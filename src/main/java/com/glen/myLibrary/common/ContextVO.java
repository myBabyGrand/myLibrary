package com.glen.myLibrary.common;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Getter
@Service
public class ContextVO {
    private String clientIP;

    private String userId;

    private Locale locale;

    private String appId;

}
