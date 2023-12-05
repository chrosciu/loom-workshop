package eu.chrost.concurrency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class S01NoConcurrency {
    public static void main(String[] args) {
        log.info("Booking started");
        var hotel = HotelTask.bookHotel("Katowice");
        var flight = FlightTask.bookFlight("Katowice");
        var aggregate = String.join(", ", hotel, flight);
        log.info("Result: {}", aggregate);
        log.info("Booking finished");
    }
}
