package com.glen.myLibrary.biz.account;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public class AccountResponse {

    private Long id;

    private String email;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String description;

    private LocalDateTime joinedAt;

    private boolean emailVerified;
    private LocalDateTime emailVerifiedAt;

    @Builder
    public AccountResponse(Long id, String email, String nickname, String password, AccountType accountType, String description, LocalDateTime joinedAt, boolean emailVerified, LocalDateTime emailVerifiedAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.accountType = accountType;
        this.description = description;
        this.joinedAt = joinedAt;
        this.emailVerified = emailVerified;
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public AccountResponse(Account account){
        this.id = account.getId();
        this.email = account.getEmail();
        this.nickname = account.getNickname();
        this.password = account.getPassword();
        this.accountType = account.getAccountType();
        this.description = account.getDescription();
        this.joinedAt = account.getJoinedAt();
        this.emailVerified = account.isEmailVerified();
        this.emailVerifiedAt = account.getEmailVerifiedAt();
    }
}
