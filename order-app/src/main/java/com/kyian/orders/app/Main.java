package com.kyian.orders.app;

import com.github.javafaker.Faker;
import com.kyian.orders.concurrent.OrderThreadManager;
import com.kyian.orders.domain.Clothing;
import com.kyian.orders.domain.Electronics;
import com.kyian.orders.domain.Order;
import com.kyian.orders.domain.OrderStatus;
import com.kyian.orders.domain.Product;
import com.kyian.orders.processing.OrderProcessor;
import com.kyian.orders.storage.OrderRepository;
import com.kyian.orders.storage.Repositories;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        Faker faker = new Faker(Locale.US);
        OrderRepository storage = Repositories.inMemory();
        OrderProcessor<Electronics> electronicsProcessor =
                new OrderProcessor<>(Electronics.class, storage, Main::handleElectronics);
        OrderProcessor<Clothing> clothingProcessor =
                new OrderProcessor<>(Clothing.class, storage, Main::handleClothing);

        List<Order<Electronics>> electronicsOrders = new ArrayList<>();
        List<Order<Clothing>> clothingOrders = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Electronics electronics = Electronics.builder()
                    .sku("EL-" + i)
                    .name(faker.commerce().productName())
                    .price(BigDecimal.valueOf(faker.number().numberBetween(100, 3000)))
                    .warrantyMonths(12 + i)
                    .build();
            Clothing clothing = Clothing.builder()
                    .sku("CL-" + i)
                    .name(faker.commerce().productName())
                    .price(BigDecimal.valueOf(faker.number().numberBetween(10, 400)))
                    .size(i % 2 == 0 ? "M" : "L")
                    .build();
            electronicsOrders.add(Order.create(electronics, i));
            clothingOrders.add(Order.create(clothing, i + 1));
        }

        electronicsOrders.forEach(storage::save);
        clothingOrders.forEach(storage::save);

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        try (OrderThreadManager threads = new OrderThreadManager(4)) {

            electronicsOrders.forEach(order ->
                    futures.add(threads.dispatch(order, electronicsProcessor::process)));
            clothingOrders.forEach(order ->
                    futures.add(threads.dispatch(order, clothingProcessor::process)));
            CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

            System.out.println("Завершених спроб завдань: " + threads.finishedTaskCount());
        }

        long completed = storage.findAll().stream()
                .filter(order -> order.status() == OrderStatus.COMPLETED)
                .count();
        long clothingCount = storage.findAll().stream()
                .filter(order -> order.product() instanceof Clothing)
                .count();
        System.out.println("--- Підсумок ---");
        System.out.printf("Замовлень: %d; одяг: %d; завершено: %d%n",
                storage.findAll().size(), clothingCount, completed);
        storage.findAll().stream().sorted(Comparator.comparing(order -> order.product().getSku()))
                .forEach(order -> System.out.printf("%s | %s | %s%n",
                        order.product().getSku(), order.product().getName(), order.status()));
    }

    private static void handleElectronics(Order<Electronics> order) {
        printOrder(order);
    }

    private static void handleClothing(Order<Clothing> order) {
        printOrder(order);
    }

    private static <T extends Product> void printOrder(Order<T> order) {
        System.out.printf("[%s] Замовлення %s: %s, кількість %d%n",
                Thread.currentThread().getName(), order.id(), order.product().getName(), order.quantity());
    }
}
