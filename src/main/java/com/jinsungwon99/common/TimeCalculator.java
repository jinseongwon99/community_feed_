package com.jinsungwon99.common;

import java.time.LocalDate;

public class TimeCalculator {
    
    //싱글톤
    private TimeCalculator(){

    }

    // 설정한 날짜의 값을 출력 <3일전이면 (현재 날짜 - 3일)을 한 값>
    public static LocalDate getDateDaysAgo(int daysAgd){
        LocalDate currDate = LocalDate.now();
        return currDate.minusDays(daysAgd);
    }
}
