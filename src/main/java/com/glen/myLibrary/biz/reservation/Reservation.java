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

//    @ManyToOne
//    private LibraryMember libraryMember;
//
//    @ManyToOne
//    @JoinColumn(name = "LIBRARY_BOOK_ID")
//    private LibraryBook libraryBook;
//
//    @OneToOne
//    private Library library;

    private String libraryMemberId;

    private String libraryBookId;

    private String LibraryId;

    @Enumerated(EnumType.STRING)
    @Setter
    private ReservationStatus reservationStatus;

    public void setArrival() {
        this.arrivalAt = LocalDateTime.now();
        this.reservationStatus = ReservationStatus.ARRIVAL;
    }
}
