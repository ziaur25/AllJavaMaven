package com.ziaur.sales;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class RandomSale {


    public static Stream<Sale> streamOf(long qty) {
        return Stream.generate(supplier).limit(qty);
    }

    public static Supplier<Sale> supplier = () -> new Sale(
            pickAnyStore(),
            new Date(),
            pickAnyCustomer(),
            randomListOfItems());

    private static Random random = new Random();
    private static final int MAX_ITEMS = 4;


    private static Store pickAnyStore() {
        return Store.values()[new Random().nextInt(2)];
    }

    private static Optional<String> pickAnyCustomer() {
        List names = Arrays.asList("Sara", "Ted", "");

        Optional<String> randomCustomer;
        randomCustomer = names.stream().findAny();
        return randomCustomer;
    }

    private static List<Item> randomListOfItems() {

        int howMany = random.nextInt(MAX_ITEMS - 1) + 1;


        List<Item> items = Arrays.asList(
                new Item("carrot", 19.333),
                new Item("onion", 33.33),
                new Item("thyme", 2.2),
                new Item("carrot", 19.333),
                new Item("tomato", 33.33),
                new Item("cherry", 2.2),
                new Item("fish", 22.2)
        );

        return items.subList(0, howMany);
    }

}
