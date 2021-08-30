package org.csystem.application.producerconsumer.component;

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
        try {
            m_producerSemaphore.acquire(m_queue.length);
            m_queue[m_tail++] = val;
            m_tail %= m_queue.length;
            m_consumerSemaphore.release(m_queue.length);
        }
        catch (InterruptedException ignore) {

        }
    }

    public int getVal()
    {
        int val = 0;

        try {
            m_consumerSemaphore.acquire(m_queue.length);
            val = m_queue[m_head++];
            m_head %= m_queue.length;
            m_producerSemaphore.release(m_queue.length);
        }
        catch (InterruptedException ignore) {

        }

        return val;
    }
}
