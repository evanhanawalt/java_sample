package com.ehanawalt.driver;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * com.ehanawalt.driver.DriverDataReaderTest
 * Tests the DriverDataReader object's ability to parse empty input, single input, and mulit-driver input
 */
class DriverDataReaderTest {

    /**
     * captureSystemOutToByteArray
     * sets the destination of system.out to be replaced with an objected returned by the method
     *
     * @return a ByteArrayOutputStream that will capture system output
     */
    private ByteArrayOutputStream captureSystemOutToByteArray() {
        ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputBytes));
        return outputBytes;
    }

    /**
     * initializeDataReader
     * initializes a DriverDataReader object from an array of strings
     *
     * @param inputArray an array of strings to be read by the DriverDataReader
     * @return a DriverDataReader object
     */
    private DriverDataReader initializeDataReader(String[] inputArray) {
        Stream<String> inputStream = Arrays.stream(inputArray);
        return new DriverDataReader(inputStream);
    }

    @Test
    void dataReaderTestEmpty() throws UnsupportedEncodingException {
        String[] inputArray = {};
        ByteArrayOutputStream outputBytes = captureSystemOutToByteArray();
        DriverDataReader dataReader = initializeDataReader(inputArray);
        dataReader.loadData();
        dataReader.printSummary();
        String output = outputBytes.toString("UTF-8");
        assertEquals("", output);
    }

    @Test
    void dataReaderTestOneDriver() throws UnsupportedEncodingException {
        String[] requiredLines = {
                "Dan: 39 miles @ 47 mph",
        };
        String[] inputArray = {
                "Driver Dan",
                "Trip Dan 07:15 07:45 17.3",
                "Trip Dan 06:12 06:32 21.8",
        };
        ByteArrayOutputStream outputBytes = captureSystemOutToByteArray();
        DriverDataReader dataReader = initializeDataReader(inputArray);
        dataReader.loadData();
        dataReader.printSummary();
        String output = outputBytes.toString("UTF-8");
        for (String line : requiredLines) {
            assertTrue(output.contains(line));
        }
    }

    @Test
    void dataReaderTestManyDrivers() throws UnsupportedEncodingException {
        String[] requiredLines = {
                "Dan: 39 miles @ 47 mph",
                "Alex: 42 miles @ 34 mph",
                "Bob: 0 miles"
        };
        String[] inputArray = {
                "Driver Dan",
                "Driver Alex",
                "Driver Bob",
                "Trip Dan 07:15 07:45 17.3",
                "Trip Dan 06:12 06:32 21.8",
                "Trip Alex 12:01 13:16 42.0"
        };
        ByteArrayOutputStream outputBytes = captureSystemOutToByteArray();
        DriverDataReader dataReader = initializeDataReader(inputArray);
        dataReader.loadData();
        dataReader.printSummary();
        String output = outputBytes.toString("UTF-8");
        for (String line : requiredLines) {
            assertTrue(output.contains(line));
        }
    }

}