package com.glen.myLibrary.biz.library.book;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.glen.myLibrary.biz.book.Book;
import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.reservation.Reservation;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class LibraryBook extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LIBRARY_ID")
    @Setter //단방향
    private Library library;

    @Enumerated(EnumType.STRING)
    private LibraryBookStatus libraryBookStatus;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    @Setter //단방향
    private Book book;

    @OneToMany(mappedBy = "libraryBook")
    @JsonManagedReference
    private List<Reservation> reservationList = new ArrayList<>();

    @Builder
    public LibraryBook(Long id, Library library, LibraryBookStatus libraryBookStatus, Book book, List<Reservation> reservationList) {
        this.id = id;
        this.library = library;
        this.libraryBookStatus = libraryBookStatus;
        this.book = book;
        this.reservationList = reservationList;
    }
}
