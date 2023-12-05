package eu.chrost.concurrency;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.StructuredTaskScope;

import static java.util.concurrent.StructuredTaskScope.Subtask.State.FAILED;
import static java.util.concurrent.StructuredTaskScope.Subtask.State.SUCCESS;

@Slf4j
public class S03StructuredTaskScope {
    @SneakyThrows
    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope<>("Booking scope", VirtualNamedThreadFactory.getInstance())) {
            log.info("Booking started");
            //var hotelSubtask = scope.fork(() -> HotelTask.bookHotel("Katowice"));
            var hotelSubtask = scope.fork(() -> HotelTask.bookFullHotel("Katowice"));
            var flightSubtask = scope.fork(() -> FlightTask.bookFlight("Katowice"));
            scope.join();
            if (hotelSubtask.state() == SUCCESS && flightSubtask.state() == SUCCESS) {
                var hotel = hotelSubtask.get();
                var flight = flightSubtask.get();
                var aggregate = String.join(", ", hotel, flight);
                log.info("Result: {}", aggregate);
                log.info("Booking finished");
            } else if (hotelSubtask.state() == FAILED) {
                throw hotelSubtask.exception();
            } else if (flightSubtask.state() == FAILED) {
                throw flightSubtask.exception();
            }
        }
    }
}
