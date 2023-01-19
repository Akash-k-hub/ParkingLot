package com.parkingLot.constants;

import java.time.LocalDateTime;
import java.time.Month;

public class Constant {

    public static final LocalDateTime DEFAULT_DATE_TIME =
            LocalDateTime.of(2023, Month.JANUARY, 1, 12, 0, 0);

    public static final int PARKING_CAPACITY = 120;

    public static final int RESERVED_PARKING_CAPACITY = (int) (0.2*PARKING_CAPACITY);

    public static final int GENERAL_PARKING_CAPACITY = (int) (0.8*PARKING_CAPACITY);
}
