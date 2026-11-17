package com.kyian.orders.concurrent.internal;

public final class SynchronizedTaskCounter {
    private int finished;

    public synchronized void increment() {
        finished++;
    }

    public synchronized int value() {
        return finished;
    }
}
