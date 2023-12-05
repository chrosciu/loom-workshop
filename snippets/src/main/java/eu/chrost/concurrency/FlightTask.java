package eu.chrost.concurrency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlightTask {
    static public String bookFlight(String destination) {
        log.info("Booking flight started");
        try {
            Thread.sleep(5000);
            var result = String.format("Booked flight to %s", destination);
            log.info("Booking flight finished");
            return result;
        } catch (InterruptedException e) {
            log.info("Booking flight interrupted");
            return "N/A";
        }
    }
}
