package com.glen.myLibrary.biz.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    public void reservationArrival(Long libraryBookId) {
    }

    public List<Reservation> getReservations(Long libraryBookId) {
//        return reservationRepository.findByLibraryBookAndReservationStatusOrderByRequestedAtDesc();
        //TODO : 구현
        return null;
    }
}
