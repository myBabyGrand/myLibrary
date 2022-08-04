package com.glen.myLibrary.biz.account;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDTO {

    private Long id;

    @Column(unique = true)
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String description;

    private LocalDateTime joinedAt;

    private boolean emailVerified;
    private LocalDateTime emailVerifiedAt;

    @Builder
    public AccountDTO(String email, String nickname, String password, AccountType accountType, String description, LocalDateTime joinedAt, boolean emailVerified, LocalDateTime emailVerifiedAt) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.accountType = accountType;
        this.description = description;
        this.joinedAt = joinedAt;
        this.emailVerified = emailVerified;
        this.emailVerifiedAt = emailVerifiedAt;
    }
}
