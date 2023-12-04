package eu.chrost.booking;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingService {
    @SneakyThrows
    public String book(String destination) {
        log.info("[{}] Booking start", destination);
        Thread.sleep(3000);
        log.info("[{}] Booking end", destination);
        return "Booked travel to: " + destination;
    }
}
