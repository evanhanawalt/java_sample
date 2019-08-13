package com.ehanawalt.driver;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * com.ehanawalt.driver.TripTest
 * basic testing of the Trip class's stored data integrity
 */
class TripTest {
    DateFormat dateFormat = new SimpleDateFormat("HH:mm");

    @Test
    void getDistanceTest() throws ParseException {
        Trip t = new Trip(dateFormat.parse("00:00"), dateFormat.parse("01:00"), 60.0);
        assertEquals(60.0, t.getDistance());
        t = new Trip(dateFormat.parse("13:00"), dateFormat.parse("15:00"), 30.0);
        assertEquals(30, t.getDistance());
        t = new Trip(dateFormat.parse("20:00"), dateFormat.parse("23:00"), 0.0);
        assertEquals(0, t.getDistance());
    }

    @Test
    void getDurationTest() throws ParseException {
        Trip t = new Trip(dateFormat.parse("00:00"), dateFormat.parse("00:00"), 60.0);
        assertEquals(Duration.ZERO, t.getDuration());
        t = new Trip(dateFormat.parse("13:00"), dateFormat.parse("15:00"), 30.0);
        assertEquals(Duration.ofHours(2), t.getDuration());
        t = new Trip(dateFormat.parse("20:00"), dateFormat.parse("23:00"), 0.0);
        assertEquals(Duration.ofHours(3), t.getDuration());
    }
}