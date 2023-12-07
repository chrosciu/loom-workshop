package eu.chrost.structuredconcurrency;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;

@Slf4j
public class S02ExecutorService {
    @SneakyThrows
    public static void main(String[] args) {
        try (var executorService = Executors.newThreadPerTaskExecutor(VirtualNamedThreadFactory.getInstance())) {
            log.info("Booking started");
            var hotelFuture = executorService.submit(() -> HotelTask.bookHotel("Katowice"));
            var flightFuture = executorService.submit(() -> FlightTask.bookFlight("Katowice"));
            var hotel = hotelFuture.get();
            var flight = flightFuture.get();
            var aggregate = String.join(", ", hotel, flight);
            log.info("Result: {}", aggregate);
            log.info("Booking finished");
        }

    }
}
