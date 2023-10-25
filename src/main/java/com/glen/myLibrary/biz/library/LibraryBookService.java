package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.biz.reservation.Reservation;
import com.glen.myLibrary.biz.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LibraryBookService {


    private final LibraryBookRepository libraryBookRepository;

    private final ReservationService reservationService;

    public LibraryBook getLibraryBook(Long libraryBookId){
        Optional<LibraryBook> libraryBook = libraryBookRepository.findById(libraryBookId);

        if(!libraryBook.isPresent()){
            throw new IllegalArgumentException("존재하지 않는 책입니다. "+libraryBookId);
        }
        return libraryBook.get();
    }
    public void returnBook(Long libraryBookId) {
        LibraryBook libraryBook = getLibraryBook(libraryBookId);

        if (!LibraryBookStatus.ON_LOAN.equals(libraryBook.getLibraryBookStatus())) {
            throw new IllegalStateException("반납할 수 없는 상태입니다. " + libraryBook.getLibraryBookStatus());
        }
        libraryBook.setLibraryBookStatus(LibraryBookStatus.BORROWABLE);
        libraryBookRepository.save(libraryBook);
    }

    public boolean isBorrowAble(Long libraryBookId, Long libraryMemberId){
        LibraryBook libraryBook = this.getLibraryBook(libraryBookId);
        if(LibraryBookStatus.BORROWABLE == libraryBook.getLibraryBookStatus()){
            return true;
        }

        if(LibraryBookStatus.RESERVED == libraryBook.getLibraryBookStatus()){
            return reservationService.isMyTurn(libraryBookId, libraryMemberId);
        }

        return false;
    }
}
