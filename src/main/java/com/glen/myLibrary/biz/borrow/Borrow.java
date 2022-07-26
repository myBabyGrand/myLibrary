package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.common.entity.BaseEntity;
import com.glen.myLibrary.biz.library.Library;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borrow extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false)
    private LocalDateTime startAt;

    private LocalDateTime expireAt;

    private LocalDateTime returnedAt;

    private int extendTimes;

    @OneToOne
    private Account account;

    @OneToOne
    private Library library;

    @PrePersist
    public void prePersist(){
        extendTimes = 0;
        startAt = LocalDateTime.now();
    }
}
