package com.glen.myLibrary.biz.reservation;

import antlr.collections.List;
import com.glen.myLibrary.biz.library.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//    List<Reservation> findByLibraryBookAndReservationStatusOrderByRequestedAtDesc();
}
