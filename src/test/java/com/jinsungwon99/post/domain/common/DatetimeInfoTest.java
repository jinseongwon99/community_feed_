package com.jinsungwon99.post.domain.common;

import static org.junit.jupiter.api.Assertions.*;

import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.content.PostContent;
import com.jinsungwon99.post.domain.content.PostPublicatuionState;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.domain.UserInfo;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
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
        assertNotEquals(localDateTime,datetimeInfo.getDateTime());
    }

}