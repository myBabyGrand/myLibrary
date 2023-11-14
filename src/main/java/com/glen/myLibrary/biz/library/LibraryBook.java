package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.book.Book;
import com.glen.myLibrary.biz.reservation.Reservation;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
    private List<Reservation> reservationList = new ArrayList<>();

}
