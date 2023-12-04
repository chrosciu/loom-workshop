package eu.chrost.booking;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingService {
    private final LimitedResources limitedResources;

    @SneakyThrows
    public String book(String destination) {
        log.info("[{}] Booking start", destination);
        limitedResources.blockFor(1000);
        log.info("[{}] Booking end", destination);
        return "Booked travel to: " + destination;
    }
}
