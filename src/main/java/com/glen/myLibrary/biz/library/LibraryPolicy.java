package com.glen.myLibrary.biz.library;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class LibraryPolicy extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private int maxBorrowCount = 5;//최대대출수
    private int lentDays = 7;//대출일자
    private int maxExtendCount = 2;//최대연장횟수
    private int extendDays = 7;//1회연장시 일수
    private int alarmForReturnDay = 3;//반납안내알림
    private int reservationDueDay = 3;//예약한 도서를 대출할 수 있는 기한

    private int maxReservations = 5;//최대예약수

    @OneToOne
    @JoinColumn(name = "LIBRARY_ID", updatable = false)
    @JsonBackReference
    private Library library;

    @Builder
    public LibraryPolicy(int maxBorrowCount, int lentDays, int maxExtendCount, int extendDays, int alarmForReturnDay, int reservationDueDay, Library library) {
        this.maxBorrowCount = maxBorrowCount;
        this.lentDays = lentDays;
        this.maxExtendCount = maxExtendCount;
        this.extendDays = extendDays;
        this.alarmForReturnDay = alarmForReturnDay;
        this.reservationDueDay = reservationDueDay;
        this.library = library;
    }

}
