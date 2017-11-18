package com.rhyno.java8.stream;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MappingTest {
    @Test
    public void return_rootList() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> rootNumbers = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        assertThat(rootNumbers).isEqualTo(Arrays.asList(1, 4, 9, 16, 25));
    }

    @Test
    public void return_모든숫자쌍의리스트() throws Exception {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> result = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        assertThat(result.size()).isEqualTo(6);
        assertThat(result).containsAll(Arrays.asList(new int[]{1, 3}, new int[]{1 ,4},
                new int[]{2, 3}, new int[]{2, 4},
                new int[]{3, 3}, new int[]{3, 4}
        ));
    }

    @Test
    public void 합이3으로_나누어떨어지는쌍만_리턴() throws Exception {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> result = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList());

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(new int[]{2, 4}, new int[]{3, 3});
    }

}