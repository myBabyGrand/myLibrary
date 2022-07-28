package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.book.Book;
import com.glen.myLibrary.biz.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryBook extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Library library;

    @Enumerated(EnumType.STRING)
    private LibraryBookStatus libraryBookStatus;

    private Book book;

    //TODO : 예약기능 추가시 예약 과 연관관계 항목 추가

}
