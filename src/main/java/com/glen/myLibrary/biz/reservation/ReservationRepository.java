package com.glen.myLibrary.biz.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByLibraryBookIdAndReservationStatusOrderByRequestedAtDesc(@Param("libraryBookId")Long libraryBookId, @Param("ReservationStatus")String reservationStatus);
//    List<Reservation> findByLibraryBookAndReservationStatusOrderByRequestedAtDesc();

    Optional<Reservation> findTopByLibraryBookIdAndReservationStatusOrderByRequestedAtDesc(@Param("libraryBookId")Long libraryBookId, @Param("ReservationStatus")ReservationStatus reservationStatus);
}
