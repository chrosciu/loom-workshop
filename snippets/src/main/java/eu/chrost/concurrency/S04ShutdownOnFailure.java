package eu.chrost.concurrency;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.StructuredTaskScope;

@Slf4j
public class S04ShutdownOnFailure {
    @SneakyThrows
    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure("Booking scope", VirtualNamedThreadFactory.getInstance())) {
            log.info("Booking started");
            //var hotelSubtask = scope.fork(() -> HotelTask.bookHotel("Katowice"));
            var hotelSubtask = scope.fork(() -> HotelTask.bookFullHotel("Katowice"));
            var flightSubtask = scope.fork(() -> FlightTask.bookFlight("Katowice"));
            scope.join().throwIfFailed();
            var hotel = hotelSubtask.get();
            var flight = flightSubtask.get();
            var aggregate = String.join(", ", hotel, flight);
            log.info("Result: {}", aggregate);
            log.info("Booking finished");
        }
    }
}
