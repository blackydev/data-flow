/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConsumerProcessChainTest {
    private final Function<Integer, List<Integer>> numberToEmptyList = number -> new LinkedList<>();
    private final Consumer<List<Integer>> addNumber = list -> list.add(1);
    private final ProcessChain<Integer, List<Integer>> processChain = ProcessChain.of(Integer.class)
            .then(numberToEmptyList)
            .then(addNumber)
            .build();

    @Test
    @DisplayName("Should return list with single element")
    void successTest() {
        List<Integer> result = processChain.process(0);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0));
    }

    @Test
    @DisplayName("Should return null if provided parameter is null")
    void nullSafeTest() {
        List<Integer> result = processChain.process(null);
        assertNull(result);
    }
}
