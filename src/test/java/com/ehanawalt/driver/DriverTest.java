package com.ehanawalt.driver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * com.ehanawalt.driver.DriverTest
 * Tests the driver object's initialization and trip recording functionality,
 * ensuring the getTotalDistance and getMph are accurate for various cases
 */
class DriverTest {
    DateFormat dateFormat = new SimpleDateFormat("HH:mm");

    @org.junit.jupiter.api.Test
    void getNameTest() {
        String[] names = {"Dan", "Evan", "Regina", "Shelly"};
        for (int i = 0; i < names.length; i++) {
            Driver driver = new Driver(names[i]);
            assertEquals(names[i], driver.getName());
        }
    }

    @org.junit.jupiter.api.Test
    void getTotalDistanceEqualsZeroTest() throws ParseException {
        Driver driver = new Driver("Evan");
        assertEquals(0.0, driver.getTotalDistance());
        driver.recordTrip(dateFormat.parse("05:05"), dateFormat.parse("11:11"), 0.0);
        assertEquals(0.0, driver.getTotalDistance());
    }

    @org.junit.jupiter.api.Test
    void getTotalDistanceGreaterThanZeroTest() throws ParseException {
        Driver driver = new Driver("Evan");
        driver.recordTrip(dateFormat.parse("00:00"), dateFormat.parse("01:00"), 1.0);
        assertEquals(1.0, driver.getTotalDistance());
        driver.recordTrip(dateFormat.parse("00:00"), dateFormat.parse("01:00"), 5.0);
        assertEquals(6.0, driver.getTotalDistance());
        driver.recordTrip(dateFormat.parse("10:00"), dateFormat.parse("17:45"), 0.0);
        assertEquals(6.0, driver.getTotalDistance());
        driver.recordTrip(dateFormat.parse("19:00"), dateFormat.parse("21:30"), 123.0);
        assertEquals(129.0, driver.getTotalDistance());
    }

    @org.junit.jupiter.api.Test
    void getMphEqualsZeroTest() throws ParseException {
        Driver driver = new Driver("Evan");
        assertEquals(0.0, driver.getMph());
        driver.recordTrip(dateFormat.parse("05:05"), dateFormat.parse("11:11"), 0.0);
        assertEquals(0.0, driver.getMph());
    }

    @org.junit.jupiter.api.Test
    void getMphGreaterThanZeroTest() throws ParseException {
        Driver driver = new Driver("Evan");
        driver.recordTrip(dateFormat.parse("00:00"), dateFormat.parse("01:00"), 1.0);
        assertEquals(1.0, driver.getMph());
        driver.recordTrip(dateFormat.parse("00:00"), dateFormat.parse("01:00"), 5.0);
        assertEquals(3.0, driver.getMph());
        driver.recordTrip(dateFormat.parse("10:00"), dateFormat.parse("17:45"), 0.0);
        assertEquals((6.0 / 9.75), driver.getMph());
        driver.recordTrip(dateFormat.parse("19:00"), dateFormat.parse("21:30"), 123.0);
        assertEquals((129.0 / 12.25), driver.getMph());

    }
}