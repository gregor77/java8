package com.rhyno.java8.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupTest {
    private List<Dish> menus;
    private Dish prawns;
    private Dish salmon;
    private Dish pork;
    private Dish beef;
    private Dish chicken;
    private Dish french_fries;
    private Dish rice;
    private Dish season_fruit;
    private Dish pizza;

    @Before
    public void setUp() throws Exception {
        prawns = Dish.builder().name("prawns").type(Dish.Type.FISH).calories(300).build();
        salmon = Dish.builder().type(Dish.Type.FISH).name("salmon").calories(300).build();
        pork = Dish.builder().type(Dish.Type.MEAT).name("pork").calories(800).build();
        beef = Dish.builder().type(Dish.Type.MEAT).name("beef").calories(700).build();
        chicken = Dish.builder().type(Dish.Type.MEAT).name("chicken").calories(600).build();
        french_fries = Dish.builder().type(Dish.Type.OTHER).name("french fries").calories(500).build();
        rice = Dish.builder().type(Dish.Type.OTHER).name("rice").calories(300).build();
        season_fruit = Dish.builder().type(Dish.Type.OTHER).name("season fruit").calories(100).build();
        pizza = Dish.builder().type(Dish.Type.OTHER).name("pizza").calories(750).build();

        menus = Arrays.asList(prawns, salmon,
                pork, beef, chicken,
                french_fries, rice, season_fruit, pizza);
    }

    @Test
    public void returnGroupMenusByType() throws Exception {
        Map<Dish.Type, List<Dish>> result = menus.stream().collect(groupingBy(Dish::getType));

        assertThat(result.get(Dish.Type.FISH).size()).isEqualTo(2);
        assertThat(result.get(Dish.Type.FISH)).contains(prawns, salmon);

        assertThat(result.get(Dish.Type.MEAT).size()).isEqualTo(3);
        assertThat(result.get(Dish.Type.MEAT)).contains(pork, beef, chicken);

        assertThat(result.get(Dish.Type.OTHER).size()).isEqualTo(4);
        assertThat(result.get(Dish.Type.OTHER)).contains(french_fries, rice, season_fruit, pizza);
    }

    @Test
    public void returnGroupByCalories() throws Exception {
        //400칼로리 이하 : diet
        //400~700 : normal
        //700 초과 : fat
        Map<CaloricLevel, List<Dish>> result = menus.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() > 400 && dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }));

        assertThat(result.get(CaloricLevel.DIET).size()).isEqualTo(4);
        assertThat(result.get(CaloricLevel.DIET)).contains(prawns, salmon, rice, season_fruit);

        assertThat(result.get(CaloricLevel.NORMAL).size()).isEqualTo(3);
        assertThat(result.get(CaloricLevel.NORMAL)).contains(beef, chicken,french_fries);

        assertThat(result.get(CaloricLevel.FAT).size()).isEqualTo(2);
        assertThat(result.get(CaloricLevel.FAT)).contains(pork, pizza);
    }
}
