/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProcessChainTest {
    private final Function<Integer, List<Integer>> numberToList = number -> new LinkedList<>(List.of(number));
    private final Consumer<List<Integer>> addNumber = list -> list.add(2);
    private final Function<List<Integer>, Integer> sumList =
            list -> (Integer) list.stream().mapToInt(Integer::intValue).sum();
    private final Predicate<Integer> isEqualToThree = number -> number == 3;
    private final ProcessChain<Integer, Integer> processChain = ProcessChain.of(Integer.class) // put 1
            .then(numberToList) // list.of(1)
            .then(addNumber) // list.of(1, 2)
            .then(sumList) // 3
            .then(isEqualToThree) // true, return 3
            .build();

    @Test
    @DisplayName("Should add half and return float")
    void successTest() {
        Integer result = processChain.process(1);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("Should return null if provided parameter is null")
    void nullSafeTest() {
        Integer result = processChain.process(null);
        assertNull(result);
    }
}
