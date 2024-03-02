package eu.chrost.booking;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LimitedResources {
    private final Lock[] locks;
    private final AtomicInteger index = new AtomicInteger(0);
    public LimitedResources(int resourcesCount) {
        locks = new Lock[resourcesCount];
        for (int i = 0; i < resourcesCount; ++i) {
            locks[i] = new ReentrantLock();
        }
    }

    @SneakyThrows
    public void blockFor(long millis) {
        var lockNum = index.getAndIncrement() % locks.length;
        locks[lockNum].lock();
        try {
            Thread.sleep(millis);
        } finally {
            locks[lockNum].unlock();
        }
    }
}
