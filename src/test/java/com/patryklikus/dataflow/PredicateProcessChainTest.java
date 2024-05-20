/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PredicateProcessChainTest {
    private final Predicate<Integer> isPositive = number -> number > 0;
    private final ProcessChain<Integer, Integer> processChain = ProcessChain.of(Integer.class)
            .then(isPositive)
            .build();

    @Test
    @DisplayName("Should return number if predicate returns true")
    void successTest() {
        Integer result = processChain.process(1);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Should return null if predicate returns false")
    void failTest() {
        Integer result = processChain.process(0);
        assertNull(result);
    }

    @Test
    @DisplayName("Should return null if provided parameter is null")
    void nullSafeTest() {
        Integer result = processChain.process(null);
        assertNull(result);
    }
}
