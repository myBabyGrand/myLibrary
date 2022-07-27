package com.glen.myLibrary.biz.borrow;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class BorrowCreateDTO {

    private Long bookId;

    private Long userId;
}
