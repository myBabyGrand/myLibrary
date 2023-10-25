package com.glen.myLibrary.biz.reservation;

import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.library.LibraryBook;
import com.glen.myLibrary.biz.library.LibraryBookStatus;
import com.glen.myLibrary.biz.library.LibraryMember;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime requestedAt;

    private LocalDateTime canceledAt;

    private LocalDateTime arrivalAt;

    @OneToOne
    private LibraryMember libraryMember;

    @ManyToOne
    @JoinColumn(name = "LIBRARY_BOOK_ID")
    private LibraryBook libraryBook;

    @OneToOne
    private Library library;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
}
