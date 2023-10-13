package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.book.Book;
import com.glen.myLibrary.biz.reservation.Reservation;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LibraryBook extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "LIBRARY_BOOK_ID")
    private Long id;

    @ManyToOne
    private Library library;

    @Enumerated(EnumType.STRING)
    private LibraryBookStatus libraryBookStatus;

    @OneToOne
    private Book book;

    //TODO : 예약기능 추가시 예약 과 연관관계 항목 추가

    @OneToMany
    private List<Reservation> reservation;
}
