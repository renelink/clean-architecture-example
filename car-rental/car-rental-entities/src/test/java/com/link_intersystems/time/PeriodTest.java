package com.link_intersystems.time;

import org.junit.jupiter.api.Test;

import static com.link_intersystems.time.PeriodBuilder.from;
import static org.junit.jupiter.api.Assertions.*;

class PeriodTest {

    @Test
    void pickUpDateAfterReturnDate() {
//        assertThrows(IllegalArgumentException.class, new RentalPeriod())
    }

    @Test
    void getDays(){
        assertEquals(1, from("2023-01-15", "08:00:00").to("2023-01-15", "17:00:00").getDays());
        assertEquals(3, from("2023-01-15", "08:00:00").to("2023-01-17", "08:00:00").getDays());
    }

    /**
     * <pre>
     * period1    |------------------|
     * period2             |---------------|
     * </pre>
     */
    @Test
    void pickUpDateOverlaps() {
        Period period1 = from("2023-01-15", "08:00:00").to("2023-01-17", "17:00:00");
        Period period2 = from("2023-01-15", "09:00:00").to("2023-01-18", "17:00:00");

        assertTrue(period1.overlaps(period2));
        assertTrue(period2.overlaps(period1));
    }

    /**
     * <pre>
     * period1    |------------------|
     * period2             |-----|
     * </pre>
     */
    @Test
    void pickUpAndReturnDateDateOverlaps() {
        Period period1 = from("2023-01-15", "08:00:00").to("2023-01-17", "17:00:00");
        Period period2 = from("2023-01-15", "09:00:00").to("2023-01-17", "16:00:00");

        assertTrue(period1.overlaps(period2));
        assertTrue(period2.overlaps(period1));
    }

    /**
     * <pre>
     * period1    |------------------|
     * period2             |-----|
     * </pre>
     */
    @Test
    void onlyReturnDateOverlaps() {
        Period period1 = from("2023-01-15", "08:00:00").to("2023-01-17", "17:00:00");
        Period period2 = from("2023-01-14", "08:00:00").to("2023-01-15", "09:00:00");

        assertTrue(period1.overlaps(period2));
        assertTrue(period2.overlaps(period1));
    }

    /**
     * <pre>
     * period1         |-----|
     * period2  |-----|
     * </pre>
     */
    @Test
    void nonOverlappingBefore() {
        Period period1 = from("2023-01-15", "08:00:00").to("2023-01-17", "17:00:00");
        Period period2 = from("2023-01-14", "08:00:00").to("2023-01-15", "07:00:00");

        assertFalse(period1.overlaps(period2));
        assertFalse(period2.overlaps(period1));
    }

}