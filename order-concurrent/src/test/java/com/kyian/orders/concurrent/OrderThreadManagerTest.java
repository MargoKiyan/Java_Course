package com.kyian.orders.concurrent;

import com.kyian.orders.domain.Clothing;
import com.kyian.orders.domain.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderThreadManagerTest {
    @Test void concurrentTasksAllExecuteExactlyOnce() {
        Order<Clothing> order = Order.create(Clothing.builder().sku("C").name("Пальто")
                .price(BigDecimal.TEN).size("M").build(), 1);
        var processed = ConcurrentHashMap.<Integer>newKeySet();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        try (OrderThreadManager manager = new OrderThreadManager(4)) {
            for (int i = 0; i < 100; i++) {
                int index = i;
                futures.add(manager.dispatch(order, ignored -> processed.add(index)));
            }
            CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
            assertEquals(100, manager.finishedTaskCount());
        }
        assertEquals(100, processed.size());
    }

    @Test void failedTaskIsCountedAsFinishedAttempt() {
        Order<Clothing> order = Order.create(Clothing.builder().sku("C-2").name("Шарф")
                .price(BigDecimal.TEN).size("L").build(), 1);
        try (OrderThreadManager manager = new OrderThreadManager(2)) {
            CompletableFuture<Void> future = manager.dispatch(order, ignored -> {
                throw new IllegalStateException("Тестова помилка обробника");
            });
            assertThrows(CompletionException.class, future::join);
            assertEquals(1, manager.finishedTaskCount());
        }
    }
}
