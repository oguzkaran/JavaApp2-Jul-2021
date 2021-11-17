package org.csystem.application.producerconsumer.component;

import org.csystem.util.thread.ThreadUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

@Component
public class SharedObject {
    private final Semaphore m_producerSemaphore;
    private final Semaphore m_consumerSemaphore;
    private final int [] m_queue;
    private int m_head;
    private int m_tail;

    public SharedObject(@Qualifier("producerSemaphore") Semaphore producerSemaphore,
                        @Qualifier("consumerSemaphore") Semaphore consumerSemaphore,
                        int [] queue)
    {
        m_producerSemaphore = producerSemaphore;
        m_consumerSemaphore = consumerSemaphore;
        m_queue = queue;
    }

    public void setVal(int val)
    {
        ThreadUtil.acquire(m_producerSemaphore, 1);
        m_queue[m_tail++] = val;
        m_tail %= m_queue.length;
        ThreadUtil.release(m_consumerSemaphore, 1);
    }

    public int getVal()
    {
        ThreadUtil.acquire(m_consumerSemaphore, 1);
        int val = m_queue[m_head++];
        m_head %= m_queue.length;
        ThreadUtil.release(m_producerSemaphore, 1);

        return val;
    }
}
