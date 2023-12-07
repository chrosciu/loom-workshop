package eu.chrost.virtualthreads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class S02MultiplePlatformThreads {
    @SneakyThrows
    public static void main(String[] args) {
        var threads = new Thread[1_000_000];
        for (int i = 0; i < 1_000_000; ++i) {
            log.info("Create: {}", i);
            threads[i] = Thread.ofPlatform().unstarted(S02MultiplePlatformThreads::task);
        }
        for (int i = 0; i < 1_000_000; ++i) {
            log.info("Start: {}", i);
            threads[i].start();
        }
        for (int i = 0; i < 1_000_000; ++i) {
            threads[i].join();
        }
    }

    @SneakyThrows
    private static void task() {
        for (;;) {
            Thread.sleep(1000);
        }
    }
}
