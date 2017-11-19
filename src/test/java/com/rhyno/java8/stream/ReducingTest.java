package com.rhyno.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;
import static org.assertj.core.api.Assertions.assertThat;

public class ReducingTest {
    @Test
    public void change_joining_collector_to_reducing_collector() throws Exception {
        List<Dish> menus = Arrays.asList(Dish.builder().name("first").build(),
                Dish.builder().name("second").build(),
                Dish.builder().name("third").build());

        String menuNames = menus.stream()
                .map(Dish::getName)
                .collect(joining());
        assertThat(menuNames).isEqualTo("firstsecondthird");

        String result1 = menus.stream()
                .map(Dish::getName)
                .collect(reducing("", (d1, d2) -> d1 + d2));
        assertThat(result1).isEqualTo("firstsecondthird");

        String result2 = menus.stream()
                .collect(reducing("", Dish::getName, (d1, d2) -> d1 + d2));
        assertThat(result2).isEqualTo("firstsecondthird");

        String result3 = menus.stream()
                .map(Dish::getName)
                .collect(reducing((d1, d2) -> d1 + d2)).get();
        assertThat(result3).isEqualTo("firstsecondthird");

    }
}
