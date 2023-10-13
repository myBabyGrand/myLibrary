package com.glen.myLibrary.biz.borrow;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BorrowSearch {

    private Long userId;

    private Long libraryId;

    private LocalDateTime startAt;

    private LocalDateTime endAt;
}
