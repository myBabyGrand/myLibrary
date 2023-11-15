package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.borrow.Borrow;
import com.glen.myLibrary.biz.reservation.Reservation;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
public class LibraryMember extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LIBRARY_ID")
    @Setter //단방향
    private Library library;

    @Enumerated(EnumType.STRING)
    private LibraryMemberType libraryMemberType;

    @Enumerated(EnumType.STRING)
    private LibraryMemberStatus libraryMemberStatus;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    public void setAccount(Account account){
        this.account = account;
//        if(!account.getLibraryMemberList().contains(this)){
//            account.getLibraryMemberList().add(this);
//        }
    }

    private LocalDateTime joinedAt;

    @OneToMany(mappedBy = "borrower")
    private List<Borrow> borrowList = new ArrayList<>();

    @OneToMany(mappedBy = "libraryMember")
    private List<Reservation> reservationList = new ArrayList<>();
}
