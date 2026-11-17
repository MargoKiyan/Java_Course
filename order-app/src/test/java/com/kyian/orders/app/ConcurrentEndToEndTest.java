package com.kyian.orders.app;

import com.kyian.orders.concurrent.OrderThreadManager;
import com.kyian.orders.domain.Clothing;
import com.kyian.orders.domain.Order;
import com.kyian.orders.domain.OrderStatus;
import com.kyian.orders.processing.OrderProcessor;
import com.kyian.orders.storage.OrderRepository;
import com.kyian.orders.storage.Repositories;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConcurrentEndToEndTest {
    @Test void sameOrderCanOnlyBeClaimedOnce() {
        OrderRepository repository = Repositories.inMemory();
        Order<Clothing> order = Order.create(Clothing.builder()
                .sku("ONE").name("Светр").price(BigDecimal.TEN).size("M").build(), 1);
        repository.save(order);
        OrderProcessor<Clothing> processor = new OrderProcessor<>(Clothing.class, repository, ignored -> {});
        CompletableFuture<Void> first;
        CompletableFuture<Void> second;
        try (OrderThreadManager manager = new OrderThreadManager(2)) {
            first = manager.dispatch(order, processor::process);
            second = manager.dispatch(order, processor::process);

            try { CompletableFuture.allOf(first, second).join(); }
            catch (java.util.concurrent.CompletionException expected) {   }
        }
        long successful = java.util.stream.Stream.of(first, second)
                .filter(future -> !future.isCompletedExceptionally()).count();
        assertEquals(1L, successful);
        assertEquals(OrderStatus.COMPLETED, repository.findById(order.id()).orElseThrow().status());
    }

    @Test void fiftyOrdersReachCompletedState() {
        OrderRepository repository = Repositories.inMemory();
        OrderProcessor<Clothing> processor = new OrderProcessor<>(Clothing.class, repository, order -> {});
        List<CompletableFuture<Void>> results = new ArrayList<>();
        try (OrderThreadManager manager = new OrderThreadManager(8)) {
            for (int i = 0; i < 50; i++) {
                Order<Clothing> order = Order.create(Clothing.builder()
                        .sku("C-" + i).name("Куртка-" + i).price(BigDecimal.TEN).size("M").build(), 1);
                repository.save(order);
                results.add(manager.dispatch(order, processor::process));
            }
            CompletableFuture.allOf(results.toArray(CompletableFuture[]::new)).join();
        }
        assertEquals(50, repository.findAll().size());
        assertTrue(repository.findAll().stream().allMatch(o -> o.status() == OrderStatus.COMPLETED));
    }
}
