/* Copyright Patryk Likus All Rights Reserved. */
package com.patryklikus.dataflow;

import java.util.function.Consumer;

class ConsumerProcessChain<T, R> extends AbstractProcessChain<T, R, R> {
    private final Consumer<R> consumer;

    ConsumerProcessChain(ProcessChainBuilder<T, R> previousBuilder, Consumer<R> consumer) {
        super(previousBuilder);
        this.consumer = consumer;
    }

    @Override
    protected R processPart(R toProcess) {
        consumer.accept(toProcess);
        return toProcess;
    }
}
