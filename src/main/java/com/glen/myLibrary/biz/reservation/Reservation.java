package com.glen.myLibrary.biz.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.library.book.LibraryBook;
import com.glen.myLibrary.biz.library.member.LibraryMember;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime requestedAt;

    private LocalDateTime canceledAt;

    private LocalDateTime arrivalAt;

    @ManyToOne
    @JsonBackReference //양방향
    @JoinColumn(name = "LIBRARY_MEMBER_ID")
    private LibraryMember libraryMember;
    @ManyToOne
    @JsonBackReference //양방향
    @JoinColumn(name = "LIBRARY_BOOK_ID")
    private LibraryBook libraryBook;

    @ManyToOne
    @JoinColumn(name = "LIBRARY_ID")
    @Setter //단방향
    private Library library;
    @Enumerated(EnumType.STRING)
    @Setter
    private ReservationStatus reservationStatus;

    public void setLibraryMember(LibraryMember libraryMember){
        this.libraryMember = libraryMember;
        libraryMember.getReservationList().add(this);
    }

    public void setLibraryBook(LibraryBook libraryBook){
        this.libraryBook = libraryBook;
        libraryBook.getReservationList().add(this);
    }

    public void setArrival() {
        this.arrivalAt = LocalDateTime.now();
        this.reservationStatus = ReservationStatus.ARRIVAL;
    }


}
