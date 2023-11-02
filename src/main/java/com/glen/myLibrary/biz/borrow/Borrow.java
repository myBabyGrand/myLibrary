package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.biz.account.Account;
import com.glen.myLibrary.biz.book.Book;
import com.glen.myLibrary.biz.library.LibraryBook;
import com.glen.myLibrary.biz.library.LibraryMember;
import com.glen.myLibrary.common.entity.BaseEntity;
import com.glen.myLibrary.biz.library.Library;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Borrow extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(updatable = false)
    private LocalDateTime startAt;

    private LocalDateTime expireAt;

    private LocalDateTime returnedAt;

    private int extendTimes;

//    private Long bookId;
//
//    private Long userId;

    @ManyToOne
    private LibraryBook libraryBook;

    @ManyToOne
    private LibraryMember borrower;

    @ManyToOne
    private Library library;


    public void returnBorrow(){
        this.setReturnedAt(LocalDateTime.now());
    }
    public void extendBorrow(long days){
        this.setExtendTimes(this.getExtendTimes()+1);
        this.setExpireAt(this.getExpireAt().plusDays(days));
    }

    @PrePersist
    public void prePersist(){
        extendTimes = 0;
        startAt = LocalDateTime.now();
    }

    @Builder
    public Borrow(LocalDateTime startAt, LocalDateTime expireAt, LocalDateTime returnedAt, int extendTimes, LibraryBook libraryBook, LibraryMember borrower, Library library) {

        this.startAt = startAt;
        this.expireAt = expireAt;
        this.returnedAt = returnedAt;
        this.extendTimes = extendTimes;
//        this.bookId = bookId;
//        this.userId = userId;
        this.libraryBook = libraryBook;
        this.borrower = borrower;
        this.library = library;
    }

    public BorrowResponse toResponse(){
        return BorrowResponse.builder()
                .id(this.getId())
                .borrower(this.getBorrower().getAccount())
                .book(this.getLibraryBook().getBook())
                .extendTimes(this.getExtendTimes())
                .expireAt(this.getExpireAt())
                .lender(this.getLibrary())
                .startAt(this.getStartAt())
                .returnedAt(this.getReturnedAt())
                .build();
    }
}
