package com.ehanawalt.driver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

/**
 * Driver
 * models data representing a vehicle driver and the trips they have driven
 */
public class Driver {
    private String name;
    private ArrayList<Trip> trips;

    /**
     * @param name the name of the driver
     */
    public Driver(String name) {
        trips = new ArrayList<>();
        this.name = name;
    }

    /**
     * @return the name of the driver
     */
    public String getName() {
        return this.name;
    }

    /**
     * recordTrip
     * Records a trip driven by a driver. Assuming startTime < endTime.
     *
     * @param startTime start time of the trip
     * @param endTime   end time of the trip
     * @param distance  trip distance in miles
     */
    public void recordTrip(Date startTime, Date endTime, double distance) {
        trips.add(new Trip(startTime, endTime, distance));
    }

    /**
     * @return total distance of this driver's trips
     */
    public double getTotalDistance() {
        double distance = 0.0;
        for (Trip trip : trips) {
            distance += trip.getDistance();
        }
        return distance;
    }

    /**
     * @return average miles per hour for of this driver's trips
     */
    public double getMph() {
        double distance = 0.0;
        Duration duration = Duration.ZERO;
        for (Trip trip : trips) {
            duration = duration.plus(trip.getDuration());
            distance += trip.getDistance();
        }

        double mph = 0.0;
        if (trips.size() > 0) {
            mph = distance / (duration.getSeconds() / 3600.0);
        }
        return mph;
    }

}
