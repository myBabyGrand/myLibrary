package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.library.book.LibraryBook;
import com.glen.myLibrary.biz.library.member.LibraryMember;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BorrowResponse {

    private Long id;

    private LocalDateTime startAt;

    private LocalDateTime expireAt;

    private LocalDateTime returnedAt;

    private int extendTimes;
    private LibraryBook book;
    private LibraryMember borrower;
    private Library lender;

    @Builder
    public BorrowResponse(Long id, LocalDateTime startAt, LocalDateTime expireAt, LocalDateTime returnedAt, int extendTimes, LibraryBook book, LibraryMember borrower, Library lender) {
        this.id = id;
        this.startAt = startAt;
        this.expireAt = expireAt;
        this.returnedAt = returnedAt;
        this.extendTimes = extendTimes;
        this.book = book;
        this.borrower = borrower;
        this.lender = lender;
    }

    public void fromEntity(Borrow borrow){
        this.id = borrow.getId();
        this.startAt = borrow.getStartAt();
        this.expireAt = borrow.getExpireAt();
        this.returnedAt = borrow.getReturnedAt();
        this.book = borrow.getLibraryBook();
        this.borrower = borrow.getBorrower();
        this.lender = borrow.getLibrary();
    }




}
