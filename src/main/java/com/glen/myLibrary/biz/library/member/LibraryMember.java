package com.glen.myLibrary.biz.library.member;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.borrow.Borrow;
import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.reservation.Reservation;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
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
    @Setter
    private Account account;
    private LocalDateTime joinedAt;
    @OneToMany(mappedBy = "borrower")
    private List<Borrow> borrowList = new ArrayList<>();
    @OneToMany(mappedBy = "libraryMember")
    private List<Reservation> reservationList = new ArrayList<>();

    @Builder
    public LibraryMember(Long id, Library library, LibraryMemberType libraryMemberType, LibraryMemberStatus libraryMemberStatus, Account account, LocalDateTime joinedAt, List<Borrow> borrowList, List<Reservation> reservationList) {
        this.id = id;
        this.library = library;
        this.libraryMemberType = libraryMemberType;
        this.libraryMemberStatus = libraryMemberStatus;
        this.account = account;
        this.joinedAt = joinedAt;
        this.borrowList = borrowList;
        this.reservationList = reservationList;
    }
}
