package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.reservation.Reservation;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
public class LibraryMember extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Library library;

    @Enumerated(EnumType.STRING)
    private LibraryMemberType libraryMemberType;

    @Enumerated(EnumType.STRING)
    private LibraryMemberStatus libraryMemberStatus;

    @OneToOne
    private Account account;

    private LocalDateTime joinedAt;

//    @OneToMany
//    @JoinColumn(name = "RESERVATION_ID")
//    private List<Reservation> reservations;

}
