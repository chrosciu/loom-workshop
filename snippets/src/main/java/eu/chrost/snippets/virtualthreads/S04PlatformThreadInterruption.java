package eu.chrost.snippets.virtualthreads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.Socket;

@Slf4j
public class S04PlatformThreadInterruption {
    @SneakyThrows
    public static void main(String[] args) {
        var thread = Thread.ofPlatform().start(S04PlatformThreadInterruption::connect);
        Thread.sleep(5000);
        log.info("Interrupting");
        thread.interrupt();
    }

    @SneakyThrows
    private static void connect() {
        try {
            log.info("Connecting");
            var socket = new Socket("192.168.0.2", 80);
            log.info("Connected: {}", socket);
        } catch (Exception e) {
            log.warn("Error during connection: ", e);
        } finally {
            log.info("After connecting");
        }
    }
}
