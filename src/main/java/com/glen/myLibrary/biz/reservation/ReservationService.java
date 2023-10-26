package com.glen.myLibrary.biz.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    public void reservationArrival(Long libraryBookId) {
    }

    public List<Reservation> getReservations(Long libraryBookId) {
        return reservationRepository.findByLibraryBookAndReservationStatusOrderByRequestedAtDesc();
    }

    public boolean isMyTurn(Long libraryBookId, Long libraryMemberId){
        List<Reservation> reservations = getReservations(libraryBookId);
        if(!CollectionUtils.isEmpty(reservations)){
            if(libraryMemberId.equals(reservations.get(0).getLibraryMember().getId())){
                return true;
            }
        }
        return false;
    }

    public void executeReservation(Long libraryBookId, Long libraryMemberId){
        List<Reservation> reservations = getReservations(libraryBookId);
        if(!CollectionUtils.isEmpty(reservations)){
            if(ReservationStatus.ARRIVAL == reservations.get(0).getReservationStatus()
               && reservations.get(0).getLibraryMember().getId().equals(libraryMemberId)){
                reservations.remove(0);
            }
        }
    }
}
