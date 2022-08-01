package com.glen.myLibrary.biz.borrow;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ToString
@Setter
@Getter
public class BorrowCreateDTO {

    @NotNull(message = "bookId는 null 일 수 없습니다.")
    private Long bookId;

    @NotNull(message = "userId는 null 일 수 없습니다.")
    private Long userId;
}
