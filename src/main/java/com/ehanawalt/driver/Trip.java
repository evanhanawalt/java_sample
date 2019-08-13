package com.ehanawalt.driver;

import java.time.Duration;
import java.util.Date;

/**
 * Trip
 * models the data for a trip
 */
public class Trip {
    private Duration duration;
    private Double distance;

    /**
     * @param startTime time the trip started
     * @param endTime   time the trip ended
     * @param distance  distance of the trip in miles
     */
    public Trip(Date startTime, Date endTime, Double distance) {
        this.duration = Duration.ofMillis(endTime.getTime() - startTime.getTime());
        this.distance = distance;
    }

    /**
     * @return the distance of the trip in miles
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * @return the duration of the trip, modeled by the java Duration object
     */
    public Duration getDuration() {
        return duration;
    }
}
