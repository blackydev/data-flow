/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FunctionProcessChainTest {
    private final Function<Integer, Float> addHalf = number -> number + 0.5f;
    private final ProcessChain<Integer, Float> processChain = ProcessChain.of(Integer.class)
            .then(addHalf)
            .build();

    @Test
    @DisplayName("Should add half and return float")
    void successTest() {
        Float result = processChain.process(1);
        assertEquals(1.5f, result);
    }

    @Test
    @DisplayName("Should return null if provided parameter is null")
    void nullSafeTest() {
        Float result = processChain.process(null);
        assertNull(result);
    }
}
