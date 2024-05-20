/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import java.util.function.Function;

class FunctionProcessChain<T, P, R> extends AbstractProcessChain<T, P, R> {
    private final Function<P, R> function;

    FunctionProcessChain(ProcessChainBuilder<T, P> previousBuilder, Function<P, R> function) {
        super(previousBuilder);
        this.function = function;
    }

    @Override
    protected R processPart(P toProcess) {
        return function.apply(toProcess);
    }
}
