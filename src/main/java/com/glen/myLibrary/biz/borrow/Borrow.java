package com.glen.myLibrary.biz.borrow;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.library.book.LibraryBook;
import com.glen.myLibrary.biz.library.member.LibraryMember;
import com.glen.myLibrary.common.entity.BaseEntity;
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

    @ManyToOne
    @JsonBackReference //양방향 연관관계 일때 순환참조 방지, 또는 mapped by 부분에 @JsonManagedReference 를 달아줘서 순서를 정해준다. (또는 둘다)
    @JoinColumn(name = "LIBRARY_BOOK_ID")
    private LibraryBook libraryBook;

    @ManyToOne
    @JsonBackReference //양방향
    @JoinColumn(name = "LIBRARY_MEMBER_ID")
    private LibraryMember borrower;
    @ManyToOne
    @JsonBackReference //양방향
    @JoinColumn(name = "LIBRARY_ID")
    private Library library;

    @Builder
    public Borrow(LocalDateTime startAt, LocalDateTime expireAt, LocalDateTime returnedAt, int extendTimes, LibraryBook libraryBook, LibraryMember borrower, Library library) {

        this.startAt = startAt;
        this.expireAt = expireAt;
        this.returnedAt = returnedAt;
        this.extendTimes = extendTimes;
        this.libraryBook = libraryBook;
        this.borrower = borrower;
        this.library = library;
    }

    public void setBorrower(LibraryMember libraryMember){
        this.borrower = libraryMember;
        if(!borrower.getBorrowList().contains(this)){
            borrower.getBorrowList().add(this);
        }
    }

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

    public BorrowResponse toResponse() {
        return BorrowResponse
                    .builder()
                    .id(this.getId())
                    .borrower(this.getBorrower())
                    .book(this.getLibraryBook())
                    .extendTimes(this.getExtendTimes())
                    .expireAt(this.getExpireAt())
                    .lender(this.getLibrary())
                    .startAt(this.getStartAt())
                    .returnedAt(this.getReturnedAt())
                    .build();
    }
}
