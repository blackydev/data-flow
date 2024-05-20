/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.UnaryOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UnaryOperatorProcessChainTest {
    private final UnaryOperator<Integer> multiplyByTwo = number -> number * 2;
    private final ProcessChain<Integer, Integer> processChain = ProcessChain.of(Integer.class)
            .then(multiplyByTwo)
            .build();

    @Test
    @DisplayName("Should return multiplied number")
    void successTest() {
        Integer result = processChain.process(1);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Should return null if provided parameter is null")
    void nullSafeTest() {
        Integer result = processChain.process(null);
        assertNull(result);
    }
}
