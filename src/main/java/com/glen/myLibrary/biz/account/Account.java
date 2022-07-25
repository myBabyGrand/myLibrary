package com.glen.myLibrary.biz.account;

import com.glen.myLibrary.biz.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private LocalDateTime joinedAt;

    private boolean emailVerified = false;
    private LocalDateTime emailVerifiedAt;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
