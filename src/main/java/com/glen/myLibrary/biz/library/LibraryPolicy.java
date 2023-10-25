package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class LibraryPolicy extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private int borrowableCountAtOnce = 5;
    private int lentDays = 10;
    private int extendableCount = 2;
    private int extendDays = 10;
    private int alarmForReturnDay = 3;//반납안내알림
    private int reservationDueDay = 3;//예약한 도서를 대출할 수 있는 기한

    @Builder
    public LibraryPolicy(int borrowableCountAtOnce, int lentDays, int extendableCount, int extendDays, int alarmForReturnDay, int reservationDueDay) {
        this.borrowableCountAtOnce = borrowableCountAtOnce;
        this.lentDays = lentDays;
        this.extendableCount = extendableCount;
        this.extendDays = extendDays;
        this.alarmForReturnDay = alarmForReturnDay;
        this.reservationDueDay = reservationDueDay;
    }
}
