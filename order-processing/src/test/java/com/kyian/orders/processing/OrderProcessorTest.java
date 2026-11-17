package com.kyian.orders.processing;

import com.kyian.orders.domain.Clothing;
import com.kyian.orders.domain.Order;
import com.kyian.orders.domain.OrderStatus;
import com.kyian.orders.storage.OrderRepository;
import com.kyian.orders.storage.Repositories;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderProcessorTest {
    @Test void processesOrderToCompleted() {
        OrderRepository storage = Repositories.inMemory();
        Order<Clothing> order = newOrder();
        storage.save(order);
        new OrderProcessor<>(Clothing.class, storage, ignored -> {}).process(order);
        assertEquals(OrderStatus.COMPLETED, storage.findById(order.id()).orElseThrow().status());
    }

    @Test void handlerFailureMarksOrderFailed() {
        OrderRepository storage = Repositories.inMemory();
        Order<Clothing> order = newOrder();
        storage.save(order);
        OrderProcessor<Clothing> processor = new OrderProcessor<>(Clothing.class, storage,
                ignored -> { throw new IllegalStateException("Немає товару"); });
        assertThrows(OrderProcessingException.class, () -> processor.process(order));
        assertEquals(OrderStatus.FAILED, storage.findById(order.id()).orElseThrow().status());
    }

    @Test void duplicateProcessingIsRejected() {
        OrderRepository storage = Repositories.inMemory();
        Order<Clothing> order = newOrder();
        storage.save(order);
        OrderProcessor<Clothing> processor = new OrderProcessor<>(Clothing.class, storage, ignored -> {});
        processor.process(order);
        assertThrows(OrderStateException.class, () -> processor.process(order));
    }

    @Test @SuppressWarnings({"rawtypes", "unchecked"})
    void rawTypeMismatchIsRejectedAtRuntime() {
        OrderRepository storage = Repositories.inMemory();
        Order<Clothing> order = newOrder();
        storage.save(order);

        Order raw = order;
        OrderProcessor<com.kyian.orders.domain.Electronics> processor =
                new OrderProcessor<>(com.kyian.orders.domain.Electronics.class, storage, ignored -> {});
        assertThrows(InvalidProductTypeException.class, () -> processor.process(raw));
    }

    private static Order<Clothing> newOrder() {
        return Order.create(Clothing.builder().sku("C").name("Шапка")
                .price(BigDecimal.TEN).size("L").build(), 1);
    }
}
