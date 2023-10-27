package com.glen.myLibrary.biz.borrow;

import com.glen.myLibrary.biz.account.AccountStatus;
import com.glen.myLibrary.biz.library.*;
import com.glen.myLibrary.biz.library.dto.LibraryMemberDTO;
import com.glen.myLibrary.biz.reservation.Reservation;
import com.glen.myLibrary.biz.reservation.ReservationService;
import com.glen.myLibrary.common.DateTimeUtil;
import com.glen.myLibrary.common.entity.SaveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;

    private final LibraryService libraryService;
    private final LibraryBookService libraryBookService;

    private final LibraryMemberService libraryMemberService;

    private final ReservationService reservationService;


    public List<BorrowResponse> getBorrows(BorrowPageSearch borrowPageSearch) {
        //TODO : 구현필요
        return new ArrayList<>();
    }

    public SaveResponse createBorrow(BorrowCreateDTO borrowCreateDTO) {
        //TODO : 이용자 상태 (APPROVED)
        LibraryMemberDTO libraryMemberDTO = libraryMemberService.getLibraryMember(borrowCreateDTO.getLibraryMemberId());
        if(AccountStatus.APPROVED != libraryMemberDTO.getAccount().getAccountStatus()){

        }
        if(LibraryMemberStatus.APPROVED != libraryMemberDTO.getLibraryMemberStatus()){

        }

        Library library = libraryService.getLibrary(borrowCreateDTO.getLibraryId());
        //TODO : 도서관 상태 (OPERATING)
        if(LibraryStatus.OPERATING != library.getLibraryStatus()){

        }

        //TODO : 책 상태 (BORROWABLE 이거나, 본인의 RESERVATION)
        if(!libraryBookService.isBorrowAble(borrowCreateDTO.getLibraryBookId(), borrowCreateDTO.getLibraryMemberId())){

        }

        int lentDays = 10;//TODO 정책에서 가져오기, 휴관일 고려.

        Borrow borrow = Borrow
                .builder()
                .startAt(LocalDateTime.now())
                .expireAt(DateTimeUtil.endDateTime(LocalDateTime.now().plusDays(lentDays)))
                .build();

        Borrow saveBorrow = borrowRepository.save(borrow);

        //예약정보 갱신
        reservationService.executeReservation(borrowCreateDTO.getLibraryBookId(), borrowCreateDTO.getLibraryMemberId());


        return new SaveResponse(1, saveBorrow.getId());
    }

    public SaveResponse returnBorrow(Long id) {

        Optional<Borrow> borrow = borrowRepository.findById(id);
        if(!borrow.isPresent()){
            throw new IllegalArgumentException("존재하지 않는 대여건입니다. "+id);
        }
        borrow.get().setReturnedAt(LocalDateTime.now());
        Borrow returnedBorrow = borrowRepository.save(borrow.get());

        //도서관 책상태 갱신
        Long libraryBookId = borrow.get().getLibraryBook().getId();
        libraryBookService.returnBook(libraryBookId);

        //예약정보관리 호출 - 예약정보갱신, 다음예약자 알림
        reservationService.reservationArrival(libraryBookId);

        return new SaveResponse(1, returnedBorrow.getId());
    }


    public SaveResponse extendBorrow(Long id) {

        Optional<Borrow> borrow = borrowRepository.findById(id);
        if(!borrow.isPresent()){
            throw new IllegalArgumentException("존재하지 않는 대여건입니다. "+id);
        }

        List<Reservation> reservations = reservationService.getReservations(borrow.get().getLibraryBook().getId());
        //TODO : 예약건이 있다면 연장불가
        if(!CollectionUtils.isEmpty(reservations)){

        }

        //TODO : 도서관의 연장일수 정책 가져오기
        //TODO : 연장횟수 초과여부
        //TODO : 연장일자 계산 default 일주일(7)

        borrow.get().extendBorrow(7);
        Borrow extendedBorrow = borrowRepository.save(borrow.get());

        return new SaveResponse(1, extendedBorrow.getId());
    }
}
