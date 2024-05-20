/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import java.util.function.UnaryOperator;

class UnaryOperatorProcessChain<T, R> extends AbstractProcessChain<T, R, R> {
    private final UnaryOperator<R> unaryOperator;

    UnaryOperatorProcessChain(ProcessChainBuilder<T, R> previousBuilder, UnaryOperator<R> unaryOperator) {
        super(previousBuilder);
        this.unaryOperator = unaryOperator;
    }

    @Override
    protected R processPart(R toProcess) {
        return unaryOperator.apply(toProcess);
    }
}
