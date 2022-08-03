package com.glen.myLibrary.biz.account;

import com.glen.myLibrary.biz.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Lob
    private String description;


    private LocalDateTime joinedAt;

    private boolean emailVerified = false;
    private LocalDateTime emailVerifiedAt;

    @Builder
    public Account(String email, String nickname, String password, AccountType accountType, String description) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.accountType = accountType;
        this.description = description;
    }
}
