package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ICDCodeTabularOptimizedForTimeTest {
    static ICDCodeTabular codes;
    @BeforeAll
    static void loadData() {
        codes = new ICDCodeTabularOptimizedForTime("/home/beatka/Documents/Dydaktyka/java_oop/lb7/icd10.txt");
    }

    @Test
    void getDescriptionTest() {
        String result = codes.getDescription("V01.9");
        Assertions.assertEquals("Pedestrian injured in collision with pedal cycle, unspecified whether traffic or nontraffic accident",result);
    }

    @Test
    void getDescriptionThrowsIllegalIndexTest() {
        Assertions.assertThrows( IndexOutOfBoundsException.class, ()->codes.getDescription("X13.37"));
    }

}