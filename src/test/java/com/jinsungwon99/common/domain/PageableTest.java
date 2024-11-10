package com.jinsungwon99.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PageableTest {

     @Test
     void givenPageableIndexIsZero_whenGetOffSet_thenShouldBeReturn0(){

         //given
         Pageable pageable = new Pageable();

         //when
         int offset = pageable.getOffset();
         int limit = pageable.getLimit();

         //then
         assertEquals(0,offset);
         assertEquals(10,limit);
     }

    @Test
    void givenPageableIndexIs2Size10_whenGetOffSet_thenShouldBeReturn10(){

        //given
        Pageable pageable = new Pageable(2,10);

        //when
        int offset = pageable.getOffset();
        int limit = pageable.getLimit();

        //then
        assertEquals(10,offset);
        assertEquals(10,limit);
    }

}
