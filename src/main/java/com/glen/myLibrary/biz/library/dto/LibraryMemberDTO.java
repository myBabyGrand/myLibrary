package com.glen.myLibrary.biz.library.dto;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.library.LibraryMemberStatus;
import com.glen.myLibrary.biz.library.LibraryMemberType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
