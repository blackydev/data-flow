/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public interface ProcessChainBuilder<T, R> {
    <V> ProcessChainBuilder<T, V> then(Function<R, V> function);

    ProcessChainBuilder<T, R> then(UnaryOperator<R> unaryOperator);

    ProcessChainBuilder<T, R> then(Consumer<R> consumer);

    ProcessChainBuilder<T, R> then(Predicate<R> predicate);

    ProcessChain<T, R> build();
}
