package eu.chrost.virtualthreads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class S01SimpleVirtualThread {
    @SneakyThrows
    public static void main(String[] args) {
        var virtualThread = Thread.startVirtualThread(S01SimpleVirtualThread::task);
        virtualThread.join();
    }

    @SneakyThrows
    private static void task() {
        log.info("Is virtual {}", Thread.currentThread().isVirtual());
        for (;;) {
            Thread.sleep(1000);
        }
    }
}
