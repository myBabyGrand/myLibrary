package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.common.entity.BaseEntity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


public class LibraryMember extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Library library;

    @Enumerated(EnumType.STRING)
    private LibraryMemberType libraryMemberType;

    private Account account;

    private LocalDateTime joinedAt;


}
