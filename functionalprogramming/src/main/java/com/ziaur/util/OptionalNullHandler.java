package com.ziaur.util;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class OptionalNullHandler {



    private static final List<Pair<String, String>> URL_TO_ENVIRONMENT = buildList();

    private static List<Pair<String, String>> buildList() {
        return Arrays.asList(
                new Pair("aaa.mysite.com", "aaa_something"),
                new Pair("bbb.mysite.com", "bbb_something"),
                new Pair("ccc.mysite.com", "ccc_comething"));
    }


    static Optional<String> get(String siteUrl) {

        Optional<Pair<String, String>> first = URL_TO_ENVIRONMENT.stream()
                .filter(p -> siteUrl.contains(p.getKey()))
                .findFirst();

        Optional optionalFirst = ofNullable(first)
                .orElse(Optional.of(new Pair(null, null)));

        return optionalFirst;
    }


    //this can be used instead of above
    static Optional<String> getNew(String siteUrl) {
        Optional<String> optionalFirst = URL_TO_ENVIRONMENT.stream()
                .filter(p -> siteUrl.contains(p.getKey()))
                .map(Pair::getValue)
                .findFirst();

        return optionalFirst;
    }


    public static void main(String[] args) {
        Optional<String> test = get("test");
        System.out.println(test.orElse("empty"));

        Optional<String> aNew = getNew("bbb.mysite.com");
        System.out.println(aNew);

    }
}
