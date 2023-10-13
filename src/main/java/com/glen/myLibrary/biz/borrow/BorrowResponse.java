package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.book.Book;
import com.glen.myLibrary.biz.library.Library;

import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class BorrowResponse {

    private Long id;

    private LocalDateTime startAt;

    private LocalDateTime expireAt;

    private LocalDateTime returnedAt;

    private int extendTimes;

    @OneToOne
    private Book book;

    @OneToOne
    private Account borrower;

    @OneToOne
    private Library lender;

}
