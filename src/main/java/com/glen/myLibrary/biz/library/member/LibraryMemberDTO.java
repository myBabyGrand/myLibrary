package com.glen.myLibrary.biz.library.member;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.library.Library;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
public class LibraryMemberDTO {

    private Long id;

    private Library library;

    @Enumerated(EnumType.STRING)
    private LibraryMemberType libraryMemberType;

    @Enumerated(EnumType.STRING)
    private LibraryMemberStatus libraryMemberStatus;

    private Account account;

    private LocalDateTime joinedAt;
}
