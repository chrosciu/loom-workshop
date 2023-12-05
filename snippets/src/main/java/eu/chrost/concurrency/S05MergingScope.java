package eu.chrost.concurrency;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.StructuredTaskScope.Subtask.State.FAILED;
import static java.util.concurrent.StructuredTaskScope.Subtask.State.SUCCESS;

@Slf4j
public class S05MergingScope {
    public static class RacingScope<T> extends StructuredTaskScope<T> {
        private String result;

        public RacingScope(String name, ThreadFactory threadFactory) {
            super(name, threadFactory);
        }

        @Override
        protected void handleComplete(Subtask<? extends T> subtask) {
            synchronized (this) {
                if (subtask.state() == SUCCESS) {
                    result = subtask.get().toString();
                } else if (subtask.state() == FAILED) {
                    result = subtask.exception().getMessage();
                }
                shutdown();
            }
        }

        public String getResult() {
            ensureOwnerAndJoined();
            return result;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        try (var scope = new RacingScope<>("Booking scope", VirtualNamedThreadFactory.getInstance())) {
            log.info("Booking started");
            //var hotelSubtask = scope.fork(() -> HotelTask.bookHotel("Katowice"));
            var hotelSubtask = scope.fork(() -> HotelTask.bookFullHotel("Katowice"));
            var flightSubtask = scope.fork(() -> FlightTask.bookFlight("Katowice"));
            scope.join();
            var aggregate = scope.getResult();
            log.info("Result: {}", aggregate);
            log.info("Booking finished");
        }
    }
}
