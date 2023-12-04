package eu.chrost.booking;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LimitedResources {
    private final Lock[] locks;
    private final Random random = new Random();
    public LimitedResources(int resourcesCount) {
        locks = new Lock[resourcesCount];
        for (int i = 0; i < resourcesCount; ++i) {
            locks[i] = new ReentrantLock();
        }
    }

    @SneakyThrows
    public void blockFor(long millis) {
        var lockNum = random.nextInt(locks.length);

        locks[lockNum].lock();
        try {
            Thread.sleep(millis);
        } finally {
            locks[lockNum].unlock();
        }
    }
}
