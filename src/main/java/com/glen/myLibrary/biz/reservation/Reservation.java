package com.glen.myLibrary.biz.reservation;

import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.library.LibraryBook;
import com.glen.myLibrary.biz.library.LibraryBookStatus;
import com.glen.myLibrary.biz.library.LibraryMember;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Data;
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
    @JoinColumn(name = "LIBRARY_MEMBER_ID")
    private LibraryMember libraryMember;

    public void setLibraryMember(LibraryMember libraryMember){
        this.libraryMember = libraryMember;
        libraryMember.getReservationList().add(this);
    }

    @ManyToOne
    @JoinColumn(name = "LIBRARY_BOOK_ID")
    private LibraryBook libraryBook;

    public void setLibraryBook(LibraryBook libraryBook){
        this.libraryBook = libraryBook;
        libraryBook.getReservationList().add(this);
    }

    @ManyToOne
    @JoinColumn(name = "LIBRARY_ID")
    @Setter //단방향
    private Library library;

    @Enumerated(EnumType.STRING)
    @Setter
    private ReservationStatus reservationStatus;

    public void setArrival() {
        this.arrivalAt = LocalDateTime.now();
        this.reservationStatus = ReservationStatus.ARRIVAL;
    }


}
