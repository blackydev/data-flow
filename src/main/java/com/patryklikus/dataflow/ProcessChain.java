/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import java.util.function.UnaryOperator;

public interface ProcessChain<T, R> {
    static <V> ProcessChainBuilder<V, V> of(Class<V> clazz) {
        return new UnaryOperatorProcessChain<>(null, UnaryOperator.identity());
    }

    R process(T toProcess);
}
