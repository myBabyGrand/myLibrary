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

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private LocalDateTime joinedAt;

    private boolean emailVerified;
    private LocalDateTime emailVerifiedAt;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Builder
    public AccountDTO(Long id, String email, String nickname, String password, LocalDateTime joinedAt, boolean emailVerified, LocalDateTime emailVerifiedAt, AccountType accountType) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.joinedAt = joinedAt;
        this.emailVerified = emailVerified;
        this.emailVerifiedAt = emailVerifiedAt;
        this.accountType = accountType;
    }
}
