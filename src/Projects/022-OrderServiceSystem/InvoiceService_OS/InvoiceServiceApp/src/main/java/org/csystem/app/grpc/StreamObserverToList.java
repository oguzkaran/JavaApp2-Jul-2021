package org.csystem.app.grpc;

import io.grpc.stub.StreamObserver;

import java.util.List;

public abstract class StreamObserverToList<S, T> implements StreamObserver<S> {
    protected final List<T> list;

    protected StreamObserverToList(List<T> list)
    {
        this.list = list;
    }
}
