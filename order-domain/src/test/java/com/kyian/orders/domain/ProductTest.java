package com.kyian.orders.domain;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test void electronicBuilderWorks() {
        Electronics p = Electronics.builder().sku("EL-1").name("Телефон")
                .price(new BigDecimal("599.99")).warrantyMonths(24).build();
        assertEquals("Телефон", p.getName());
        assertEquals(24, p.getWarrantyMonths());
    }

    @Test void clothingBuilderWorks() {
        Clothing p = Clothing.builder().sku("CL-1").name("Куртка")
                .price(new BigDecimal("80.00")).size("M").build();
        assertEquals("M", p.getSize());
    }

    @Test void wrongPriceRejected() {
        assertThrows(InvalidProductException.class, () -> Clothing.builder().sku("CL-2")
                .name("Футболка").price(BigDecimal.ZERO).size("L").build());
    }

    @Test void wrongQuantityRejected() {
        Electronics p = Electronics.builder().sku("E").name("Ноутбук")
                .price(BigDecimal.TEN).warrantyMonths(12).build();
        assertThrows(InvalidOrderException.class, () -> Order.create(p, 0));
    }
}
