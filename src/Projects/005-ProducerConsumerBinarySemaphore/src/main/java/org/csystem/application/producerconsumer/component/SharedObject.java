package org.csystem.application.producerconsumer.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

@Component
public class SharedObject {
    private final Semaphore m_producerSemaphore;
    private final Semaphore m_consumerSemaphore;

    private int m_val;

    public SharedObject(@Qualifier("producerSemaphore") Semaphore producerSemaphore,
                        @Qualifier("consumerSemaphore") Semaphore consumerSemaphore)
    {
        m_producerSemaphore = producerSemaphore;
        m_consumerSemaphore = consumerSemaphore;
    }

    public void setVal(int val)
    {
        try {
            m_producerSemaphore.acquire();
            m_val = val;
            m_consumerSemaphore.release();
        }
        catch (InterruptedException ignore) {

        }
    }

    public int getVal()
    {
        int val = 0;

        try {
            m_consumerSemaphore.acquire();
            val = m_val;
            m_producerSemaphore.release();
        }
        catch (InterruptedException ignore) {

        }

        return val;
    }
}
