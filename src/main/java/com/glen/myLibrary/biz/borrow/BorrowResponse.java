package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.book.Book;
import com.glen.myLibrary.biz.library.Library;
import lombok.Builder;

import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class BorrowResponse {

    private Long id;

    private LocalDateTime startAt;

    private LocalDateTime expireAt;

    private LocalDateTime returnedAt;

    private int extendTimes;
    private Book book;
    private Account borrower;
    private Library lender;

    public void fromEntity(Borrow borrow){
        this.id = borrow.getId();
        this.startAt = borrow.getStartAt();
        this.expireAt = borrow.getExpireAt();
        this.returnedAt = borrow.getReturnedAt();
        this.book = borrow.getLibraryBook().getBook();
        this.borrower = borrow.getBorrower().getAccount();
        this.lender = borrow.getLibrary();
    }


    @Builder
    public BorrowResponse(Long id, LocalDateTime startAt, LocalDateTime expireAt, LocalDateTime returnedAt, int extendTimes, Book book, Account borrower, Library lender) {
        this.id = id;
        this.startAt = startAt;
        this.expireAt = expireAt;
        this.returnedAt = returnedAt;
        this.extendTimes = extendTimes;
        this.book = book;
        this.borrower = borrower;
        this.lender = lender;
    }
}
