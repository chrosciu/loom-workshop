package eu.chrost.structuredconcurrency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HotelTask {
    static public String bookHotel(String destination) {
        log.info("Booking hotel started");
        try {
            Thread.sleep(5000);
            var result = String.format("Booked hotel in %s", destination);
            log.info("Booking hotel finished");
            return result;
        } catch (InterruptedException e) {
            log.info("Booking hotel interrupted");
            return "N/A";
        }
    }

    static public String bookFullHotel(String destination) {
        log.info("Booking hotel started");
        try {
            Thread.sleep(1000);
            log.info("Booking hotel failed");
            throw new RuntimeException(String.format("Cannot book hotel in %s - no rooms available", destination));
        } catch (InterruptedException e) {
            log.info("Booking hotel interrupted");
            return "N/A";
        }
    }
}
