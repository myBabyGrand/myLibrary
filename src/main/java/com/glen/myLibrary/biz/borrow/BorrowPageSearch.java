package com.glen.myLibrary.biz.borrow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;


public class BorrowPageSearch extends BorrowSearch{

    private Page page;

    BorrowPageSearch(Long userId, Long libraryId, LocalDateTime startAt, LocalDateTime endAt) {
        super(userId, libraryId, startAt, endAt);
    }
}
