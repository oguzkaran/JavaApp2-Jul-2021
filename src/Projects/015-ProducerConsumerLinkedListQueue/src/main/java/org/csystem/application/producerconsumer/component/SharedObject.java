package org.csystem.application.producerconsumer.component;

import org.csystem.collection.LinkedListQueue;
import org.csystem.util.thread.ThreadUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;
import java.util.concurrent.Semaphore;

@Component
public class SharedObject {
    private final Semaphore m_producerSemaphore;
    private final Semaphore m_consumerSemaphore;
    private final LinkedListQueue<Integer> m_queue;

    public SharedObject(@Qualifier("producerSemaphore") Semaphore producerSemaphore,
                        @Qualifier("consumerSemaphore") Semaphore consumerSemaphore,
                        LinkedListQueue<Integer> queue)
    {
        m_producerSemaphore = producerSemaphore;
        m_consumerSemaphore = consumerSemaphore;
        m_queue = queue;
    }

    public void setVal(int val)
    {
        ThreadUtil.acquire(m_producerSemaphore, 1);
        m_queue.putItem(val);
        ThreadUtil.release(m_consumerSemaphore, 1);
    }

    public OptionalInt getVal()
    {
        ThreadUtil.acquire(m_consumerSemaphore, 1);
        var opt = m_queue.getItem();
        ThreadUtil.release(m_producerSemaphore, 1);

        return opt.map(OptionalInt::of).orElse(OptionalInt.empty());
    }
}
