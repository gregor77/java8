package com.rhyno.java8.stream;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Dish {
    @Getter
    private String name;

    @Getter
    private Type type;

    @Getter
    private int calories;

    enum Type {
        FISH, MEAT, OTHER
    }
}
