package com.glen.myLibrary.common.util;

import java.time.LocalDateTime;

public class DateTimeUtil {

    public static LocalDateTime endDateTime(LocalDateTime localDateTime){
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(),23,59,59);
    }
}
