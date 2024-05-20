/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * @param <T> first chain input
 * @param <P> previous chain output, current chain input
 * @param <R> chain output
 */
abstract class AbstractProcessChainBuilder<T, P, R> implements ProcessChainBuilder<T, R>, ProcessChain<T, R> {
    private ProcessChainBuilder<T, P> previousBuilder;
    private boolean built = false;
    protected ProcessChain<T, P> previous;

    public AbstractProcessChainBuilder(ProcessChainBuilder<T, P> previousBuilder) {
        this.previousBuilder = previousBuilder;
    }

    @Override
    public <V> ProcessChainBuilder<T, V> then(Function<R, V> function) {
        return new FunctionProcessChain<>(this, function);
    }

    @Override
    public ProcessChainBuilder<T, R> then(UnaryOperator<R> unaryOperator) {
        return new UnaryOperatorProcessChain<>(this, unaryOperator);
    }

    @Override
    public ProcessChainBuilder<T, R> then(Consumer<R> consumer) {
        return new ConsumerProcessChain<>(this, consumer);
    }

    @Override
    public ProcessChainBuilder<T, R> then(Predicate<R> predicate) {
        return new PredicateProcessChain<>(this, predicate);
    }

    public ProcessChain<T, R> build() {
        if (built) {
            throw new IllegalStateException("ProcessChain is already built");
        }
        built = true;
        if (previousBuilder != null) {
            previous = previousBuilder.build();
            previousBuilder = null;
        }
        return this;
    }
}
