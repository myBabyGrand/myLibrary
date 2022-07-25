package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.common.entity.BaseEntity;
import com.glen.myLibrary.biz.library.Library;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borrow extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime startAt;

    private LocalDateTime expireAt;

    private int extendTimes = 0;

    @OneToOne
    private Account account;

    @OneToOne
    private Library library;
}
