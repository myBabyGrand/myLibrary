package com.glen.myLibrary.biz.account.dto;

import com.glen.myLibrary.biz.account.AccountType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountUpdateDTO {

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

    private boolean emailVerified;
    private LocalDateTime emailVerifiedAt;

    @Builder
    public AccountUpdateDTO(String email, String nickname, String password, AccountType accountType, String description, boolean emailVerified, LocalDateTime emailVerifiedAt) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.accountType = accountType;
        this.description = description;
        this.emailVerified = emailVerified;
        this.emailVerifiedAt = emailVerifiedAt;
    }
}
