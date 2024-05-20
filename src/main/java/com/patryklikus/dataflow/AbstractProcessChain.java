/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

/**
 * @param <T> first chain input
 * @param <P> previous chain output, current chain input
 * @param <R> chain output
 */
abstract class AbstractProcessChain<T, P, R> extends AbstractProcessChainBuilder<T, P, R> {
    AbstractProcessChain(ProcessChainBuilder<T, P> previousBuilder) {
        super(previousBuilder);
    }

    @SuppressWarnings("unchecked")
    public R process(T toProcess) {
        P partiallyProcessed = previous == null
                ? (P) toProcess // it's first chain
                : previous.process(toProcess);
        return partiallyProcessed == null ? null : processPart(partiallyProcessed);
    }

    /**
     * @return processed object
     */
    protected abstract R processPart(P toProcess);
}
