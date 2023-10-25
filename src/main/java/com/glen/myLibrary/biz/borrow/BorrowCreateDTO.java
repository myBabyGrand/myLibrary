package com.glen.myLibrary.biz.borrow;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ToString
@Setter
@Getter
public class BorrowCreateDTO {

    @NotNull(message = "libraryBookId는 null 일 수 없습니다.")
    private Long libraryBookId;

    @NotNull(message = "libraryMemberId는 null 일 수 없습니다.")
    private Long libraryMemberId;

    @NotNull(message = "libraryId는 null 일 수 없습니다.")
    private Long libraryId;

    private LocalDateTime expiredAt;
}
