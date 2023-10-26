package com.glen.myLibrary.biz.reservation;

import com.glen.myLibrary.biz.library.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByLibraryBookAndReservationStatusOrderByRequestedAtDesc();
//    List<Reservation> findByLibraryBookAndReservationStatusOrderByRequestedAtDesc();
}
