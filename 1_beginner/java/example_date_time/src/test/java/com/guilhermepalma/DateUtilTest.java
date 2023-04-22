package com.guilhermepalma;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void dateToString() {
        LocalDate localDate = LocalDate.of(2022, 6, 21);
        assertEquals("21/06/2022", DateUtil.dateToString(localDate));

        LocalDate localDateTwo = LocalDate.of(2018, 12, 14);
        assertEquals("14/12/2018", DateUtil.dateToString(localDateTwo));

        assertThrows(DateTimeException.class, () -> LocalDate.of(2022, 6, 31));
    }

    @Test
    void stringToAmericanDate() {
        assertEquals(DateUtil.stringToAmericanDate("13/12/2006"), LocalDate.of(2006, 12, 13));
        assertEquals(DateUtil.stringToAmericanDate("26/12/2006"), LocalDate.of(2006, 12, 26));
        assertEquals(DateUtil.stringToAmericanDate("05/03/1750"), LocalDate.of(1750, 3, 5));
        assertEquals(DateUtil.stringToAmericanDate("25/09/2053"), LocalDate.of(2053, 9, 25));
        assertEquals(DateUtil.stringToAmericanDate("13-12-2006"), LocalDate.of(2006, 12, 13));
        assertEquals(DateUtil.stringToAmericanDate("26-12-2006"), LocalDate.of(2006, 12, 26));
        assertEquals(DateUtil.stringToAmericanDate("05-03-1750"), LocalDate.of(1750, 3, 5));
        assertEquals(DateUtil.stringToAmericanDate("25-09-2053"), LocalDate.of(2053, 9, 25));
    }

    @Test
    void validStringDate() {
        assertTrue(DateUtil.validStringDate("13/12/2006"));
        assertTrue(DateUtil.validStringDate("26/12/2006"));
        assertTrue(DateUtil.validStringDate("05/03/1750"));
        assertTrue(DateUtil.validStringDate("25/09/2053"));
        assertTrue(DateUtil.validStringDate("13-12-2006"));
        assertTrue(DateUtil.validStringDate("26-12-2006"));
        assertTrue(DateUtil.validStringDate("05-03-1750"));
        assertTrue(DateUtil.validStringDate("25-09-2053"));

        assertFalse(DateUtil.validStringDate("as/as/dasd"));
        assertFalse(DateUtil.validStringDate("5/09/2053"));
        assertFalse(DateUtil.validStringDate("25/9/2053"));
        assertFalse(DateUtil.validStringDate("25/09/253"));
        assertFalse(DateUtil.validStringDate("25_09_2053"));
        assertFalse(DateUtil.validStringDate("25 09 2053"));
    }
}