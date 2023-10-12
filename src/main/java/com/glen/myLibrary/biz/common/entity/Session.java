package com.glen.myLibrary.biz.common.entity;

import com.glen.myLibrary.biz.account.AccountType;
import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.library.LibraryMemberType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class Session {

    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private LocalDateTime loginAt;

    private LocalDateTime estimatedLogoutAt;

    //LIBRARY_ADMIN의 경우
    private Library library;

    private LibraryMemberType libraryMemberType;
}
