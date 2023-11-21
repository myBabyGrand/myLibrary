package com.glen.myLibrary.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SaveResponse {

    private int processCount;

    private Long key;
}
