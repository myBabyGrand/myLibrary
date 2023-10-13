package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.common.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class LibraryMember extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Library library;

    @Enumerated(EnumType.STRING)
    private LibraryMemberType libraryMemberType;

    @OneToOne
    private Account account;

    private LocalDateTime joinedAt;


}
