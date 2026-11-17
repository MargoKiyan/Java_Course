package com.kyian.orders.concurrent;

import com.kyian.orders.concurrent.internal.SynchronizedTaskCounter;
import com.kyian.orders.domain.Order;
import com.kyian.orders.domain.Product;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public final class OrderThreadManager implements AutoCloseable {
    private final ExecutorService pool;
    private final SynchronizedTaskCounter finishedTasks = new SynchronizedTaskCounter();

    public OrderThreadManager(int threads) {
        if (threads <= 0) throw new IllegalArgumentException("Кількість потоків має бути додатною");
        pool = Executors.newFixedThreadPool(threads);
    }

    public <T extends Product> CompletableFuture<Void> dispatch(Order<T> order,
                                                                 Consumer<Order<T>> handler) {
        Objects.requireNonNull(order, "order");
        Objects.requireNonNull(handler, "handler");

        return CompletableFuture.runAsync(() -> {
            try {
                handler.accept(order);
            } finally {
                finishedTasks.increment();
            }
        }, pool);
    }

    public int finishedTaskCount() {
        return finishedTasks.value();
    }

    @Override
    public void close() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(30, TimeUnit.SECONDS)) pool.shutdownNow();
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
