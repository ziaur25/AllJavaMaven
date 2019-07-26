package com.ziaur.sales;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TodaySalesMain {
    static final List<Sale> sales = Arrays.asList(
            new Sale(Store.DERRY, new Date(), Optional.of("Sara"),
                    Arrays.asList(
                            new Item("carrot", 19.333),
                            new Item("tomato", 33.33),
                            new Item("cherry", 2.2)
                    )),
            new Sale(Store.LETTERKENNY, new Date(),
                    Arrays.asList(
                            //new Item("meat", 59.333),
                            new Item("fish", 43.33),
                            new Item("fish", 22.2)
                    )),
            new Sale(Store.DERRY, new Date(), Optional.of("Ted"),
                    Arrays.asList(
                            new Item("carrot", 19.333),
                            new Item("onion", 33.33),
                            new Item("thyme", 2.2)
                    ))
    );


    //only once the stream instance is created
    private static Stream<Sale> salesStream() {
        //return sales.stream();
        return RandomSale.streamOf(60000);
    }


    public static void main(String[] args) {


        //how many sales
        long salesCount = salesStream().count();
        System.out.println("Count:" + salesCount);

        //big sale day
        boolean bigSaleDay = salesStream().anyMatch(sale -> sale.total() > 100);
        System.out.println("big sale day:" + bigSaleDay);

        //maximum sale amount
        DoubleSummaryStatistics stats =
                salesStream().mapToDouble(Sale::total).summaryStatistics();
        System.out.println("Max sale amount:" + stats.getMax());
        System.out.println("Statistics on total:\n" + stats);


        //how many items sold today
        Supplier<Stream<Item>> itemStream = () -> salesStream().flatMap(sale -> sale.items.stream());
        long totalItems = itemStream.get().count();
        System.out.println("total sold items:" + totalItems);

        //how many distinct items sold today
        long totalDistinctItems = itemStream.get()
                .map(item -> item.getName())
                .distinct().count();
        System.out.println("total distinct items:" + totalDistinctItems);

        //which different items were sold?
        List<String> distinctItems = itemStream.get()
                .map(item -> item.getName())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct items:" + distinctItems);


        //Collectors group by
        //Map<Store, DoubleSummaryStatistics> storeListMap = salesStream()
        Map<String, DoubleSummaryStatistics> storeListMap = salesStream()
                .parallel()
                .collect(
                        Collectors.groupingBy(
                                //sale -> sale.store,
                                sale -> Thread.currentThread().getName(),
                                Collectors.summarizingDouble(Sale::total)));


        System.out.println("Sales by Store");

        storeListMap.keySet().stream().forEach(
                store -> System.out.println(store+ " : "+storeListMap.get(store))
        );


    }


}
