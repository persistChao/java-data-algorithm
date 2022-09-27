package com.answer.function.function;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 *
 * @author suchao
 * @date 2022/9/26 11:17
 **/
public class ConsumerTest {

    @Test
    public void testConsumer() {
        calculateTotalPrice(Arrays.asList(new Product(20)));
    }

    int calculateTotalPrice(List<Product> products) {

        AtomicInteger totalPrice = new AtomicInteger();
        Consumer<Product> consumer = x -> x.setPrice(x.getPrice() -10);
        for (Product product : products) {
            consumer.accept(product);
        }
        return totalPrice.get();
    }



    @Data
    @AllArgsConstructor
    class Product{
        private int price;
    }
}
