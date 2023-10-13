package com.glen.myLibrary.biz.borrow;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;


@Builder
public class BorrowPageSearch extends BorrowSearch{

    private Page page;
}
