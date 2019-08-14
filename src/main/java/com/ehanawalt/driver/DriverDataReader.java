package com.ehanawalt.driver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * DriverDataReader
 * reads in a stream of data with 2 possible commands per string: Driver and Trip.
 * <p>
 * The Driver command registers a new driver and has 1 arugment, the driver's name.
 * e.g. "Driver Dan"
 * <p>
 * The Trip commands records a trip for a driver and has parameters: name, start time, end time, and distance.
 * e.g. "Trip Dan 07:15 07:45 17.3"
 * For trips to record properly in the system the following conditions must be met:
 * driver must already be registered via the driver command,
 * times must be given in 24 hour format padded with zero e.g. "00:00", "01:30", "23:00",
 * distance must fit within the Double maximum value and be non-negative.
 */
public class DriverDataReader {
    Stream<String> inputStream;
    private ArrayList<Driver> drivers;
    private DateFormat dateFormat;

    /**
     * @param inputStream a stream of Strings, representing valid 'Driver' and 'Trip' commands.
     */
    public DriverDataReader(Stream<String> inputStream) {
        this.dateFormat = new SimpleDateFormat("HH:mm");
        this.inputStream = inputStream;
        this.drivers = new ArrayList<>();
    }

    /**
     * loadData
     * loads data from the input stream with which the object was initialized
     */
    public void loadData() {
        Map<String, Driver> inputMap = new HashMap<>();
        inputStream.forEach(line -> {
            String[] splitLine = line.split(" ");
            String command = splitLine[0];
            String name = splitLine[1];
            switch (command) {
                case "Driver":
                    inputMap.put(name, new Driver(name));
                    break;
                case "Trip":
                    try {
                        inputMap.get(name).recordTrip(dateFormat.parse(splitLine[2]), dateFormat.parse(splitLine[3]), new Double(splitLine[4]));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });
        drivers.addAll(inputMap.values());
    }

    /**
     * printSummary
     * prints a basic summary of the previously loaded data to System.out
     */
    public void printSummary() {
        for (Driver driver : drivers) {
            double distance = driver.getTotalDistance();
            double mph = driver.getMph();
            if (distance > 0.0) {
                System.out.println(String.format("%s: %.0f miles @ %.0f mph", driver.getName(), distance, mph));
            } else {
                System.out.println(String.format("%s: 0 miles", driver.getName()));
            }
        }
    }
}
