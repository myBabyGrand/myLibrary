package com.glen.myLibrary.biz.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    public void reservationArrival(Long libraryBookId) {
        Optional<Reservation> reservation = reservationRepository.findTopByLibraryBookIdAndReservationStatusOrderByRequestedAtDesc(libraryBookId, ReservationStatus.WAITING);
        if(reservation.isPresent()){
            Reservation arrivalReservation = reservation.get();
            arrivalReservation.setArrival();
            //TODO : NOTIFY to Client(예약도착)
        }

    }

    public List<Reservation> getReservations(Long libraryBookId) {
        return reservationRepository.findByLibraryBookIdAndReservationStatusOrderByRequestedAtDesc(libraryBookId, ReservationStatus.WAITING.name());
    }

    public boolean isMyTurn(Long libraryBookId, Long libraryMemberId){
        Optional<Reservation> reservation = reservationRepository.findTopByLibraryBookIdAndReservationStatusOrderByRequestedAtDesc(libraryBookId, ReservationStatus.ARRIVAL);
        if(reservation.isPresent()) {
            Reservation arrivalReservation = reservation.get();
            if(libraryMemberId.equals(arrivalReservation.getLibraryMember().getId())){
                return true;
            }
        }
        return false;
    }

    public void executeReservation(Long libraryBookId, Long libraryMemberId){
        Optional<Reservation> reservation = reservationRepository.findTopByLibraryBookIdAndReservationStatusOrderByRequestedAtDesc(libraryBookId, ReservationStatus.ARRIVAL);
        if(reservation.isPresent()) {
            Reservation doneReservation = reservation.get();
            if(libraryMemberId.equals(doneReservation.getLibraryMember().getId())){//필요한가..?
                doneReservation.setReservationStatus(ReservationStatus.DONE);
            }
        }
    }
}
