package com.glen.myLibrary.biz.library.book;

import com.glen.myLibrary.biz.book.Book;
import com.glen.myLibrary.biz.library.Library;
import com.glen.myLibrary.biz.reservation.ReservationService;
import com.glen.myLibrary.common.Exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LibraryBookService {


    private final LibraryBookRepository libraryBookRepository;

    private final ReservationService reservationService;

    public LibraryBook makeTestLibraryBook(Book book, Library library){
        LibraryBook libraryBook = LibraryBook
                .builder()
                .book(book)
                .library(library)
                .libraryBookStatus(LibraryBookStatus.BORROWABLE)
                .build();
        return libraryBookRepository.save(libraryBook);
    }

    public LibraryBook getLibraryBook(Long libraryBookId){
        return libraryBookRepository.findById(libraryBookId).orElseThrow(DataNotFoundException::new);
    }
    public void returnBook(Long libraryBookId) {
        LibraryBook libraryBook = getLibraryBook(libraryBookId);

        if (!LibraryBookStatus.ON_LOAN.equals(libraryBook.getLibraryBookStatus())) {
            throw new IllegalStateException("반납할 수 없는 상태입니다. " + libraryBook.getLibraryBookStatus());
        }
        libraryBook.setLibraryBookStatus(LibraryBookStatus.BORROWABLE);
        libraryBookRepository.save(libraryBook);
    }

    public boolean isBorrowAble(LibraryBook libraryBook, Long libraryMemberId){
        if(LibraryBookStatus.BORROWABLE == libraryBook.getLibraryBookStatus()){
            return true;
        }

        if(LibraryBookStatus.RESERVED == libraryBook.getLibraryBookStatus()){
            return reservationService.isMyTurn(libraryBook.getId(), libraryMemberId);
        }

        return false;
    }


}
