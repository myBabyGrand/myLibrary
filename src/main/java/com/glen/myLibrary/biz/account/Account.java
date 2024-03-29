package com.glen.myLibrary.biz.account;

import com.glen.myLibrary.biz.account.dto.AccountUpdateDTO;
import com.glen.myLibrary.common.entity.BaseEntity;
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

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Lob
    private String description;

    private LocalDateTime joinedAt;

    private boolean emailVerified;
    private LocalDateTime emailVerifiedAt;


//    @OneToMany(mappedBy = "account")
//    private List<LibraryMember> libraryMemberList = new ArrayList<>();

    //귀찮더라도, 생성자method에 Builder를 달아주는게 좋다
    //class에 달 경우 여러가지 제약조건(field가 final이라든지, default 값이 지정되어 있다든지)이 생긴다. 간단한 class가 아닌 경우에는 지양하자.
    @Builder
    public Account(String email, String nickname, String password, AccountType accountType, AccountStatus accountStatus, String description, LocalDateTime joinedAt, boolean emailVerified, LocalDateTime emailVerifiedAt) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.description = description;
        this.joinedAt = joinedAt;
        this.emailVerified = emailVerified;
        this.emailVerifiedAt = emailVerifiedAt;
    }


    //TODO : mapstruct 적용하기
    public void from(AccountUpdateDTO updateDTO){
        this.email = updateDTO.getEmail() != null ? updateDTO.getEmail() : this.email;
        this.nickname = updateDTO.getNickname() != null ? updateDTO.getNickname() : this.nickname;
        this.password = updateDTO.getPassword() != null ? updateDTO.getPassword() : this.password;
        this.accountType = updateDTO.getAccountType() != null ? updateDTO.getAccountType() : this.accountType;
        this.accountStatus = updateDTO.getAccountStatus() != null ? updateDTO.getAccountStatus() : this.accountStatus;
        this.description = updateDTO.getDescription() != null ? updateDTO.getDescription() : this.description;
        this.emailVerified = updateDTO.isEmailVerified() != this.emailVerified ? updateDTO.isEmailVerified() : this.emailVerified;
        this.emailVerifiedAt = updateDTO.getEmailVerifiedAt() != null ?  updateDTO.getEmailVerifiedAt() : this.emailVerifiedAt;
    }
}
