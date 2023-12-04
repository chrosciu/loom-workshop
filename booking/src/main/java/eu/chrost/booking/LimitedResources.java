package eu.chrost.booking;

import lombok.SneakyThrows;

import java.util.Random;

public class LimitedResources {
    private final Object[] locks;
    private final Random random = new Random();
    public LimitedResources(int resourcesCount) {
        locks = new Object[resourcesCount];
        for (int i = 0; i < resourcesCount; ++i) {
            locks[i] = new Object();
        }
    }

    @SneakyThrows
    public void blockFor(long millis) {
        var lockNum = random.nextInt(locks.length);

        synchronized (locks[lockNum]) {
            Thread.sleep(millis);
        }
    }
}
