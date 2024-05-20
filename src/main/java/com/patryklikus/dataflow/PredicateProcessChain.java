/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import java.util.function.Predicate;

class PredicateProcessChain<T, R> extends AbstractProcessChain<T, R, R> {
    private final Predicate<R> predicate;

    PredicateProcessChain(ProcessChainBuilder<T, R> previousBuilder, Predicate<R> predicate) {
        super(previousBuilder);
        this.predicate = predicate;
    }

    @Override
    protected R processPart(R toProcess) {
        return predicate.test(toProcess) ? toProcess : null;
    }
}
