package com.glen.myLibrary.biz.library;

import com.glen.myLibrary.common.entity.BaseEntity;

public class LibraryPolicy extends BaseEntity {

    private int borrowableCountAtOnce = 5;
    private int lentDays = 10;
    private int extendableCount = 2;
    private int extendDays = 10;
    private int alarmForReturnDay = 3;//반납안내알림
    private int reservationDueDay = 3;//예약한 도서를 대출할 수 있는 기한
    private Library library;
}
