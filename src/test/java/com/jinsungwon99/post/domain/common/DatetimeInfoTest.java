package com.jinsungwon99.post.domain.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DatetimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArgsUpdated(){
        //given
        DatetimeInfo datetimeInfo = new DatetimeInfo();
        LocalDateTime localDateTime = datetimeInfo.getDateTime();
        System.out.println("Local DateTime: " + localDateTime);

        //when
        datetimeInfo.updateEditDatetime();

        System.out.println("Updated DateTime: " + datetimeInfo.getDateTime());
        //then
        assertTrue(datetimeInfo.isEdited());
        //assertNotEquals(localDateTime,datetimeInfo.getDateTime());
    }

}