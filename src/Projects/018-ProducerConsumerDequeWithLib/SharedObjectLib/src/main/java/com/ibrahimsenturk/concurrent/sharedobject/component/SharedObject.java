package com.ibrahimsenturk.concurrent.sharedobject.component;

import com.ibrahimsenturk.concurrent.sharedobject.global.SharedObjectBean;
import org.csystem.util.thread.ThreadUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.OptionalInt;
import java.util.concurrent.Semaphore;

@Component
@Scope("prototype")
public class SharedObject {
    private final Semaphore m_producerSemaphore;
    private final Semaphore m_consumerSemaphore;
    private final Deque<Integer> m_queue; //Kutulama ve kutu açma maliyetine dikkar ediniz

    public SharedObject(@Qualifier("com.ibrahimsenturk.concurrent.sharedobject.producerSemaphore") Semaphore producerSemaphore,
                        @Qualifier("com.ibrahimsenturk.concurrent.sharedobject.consumerSemaphore") Semaphore consumerSemaphore,
                        @Qualifier(SharedObjectBean.DEQUE_BEAN) Deque<Integer> queue)
    {
        m_producerSemaphore = producerSemaphore;
        m_consumerSemaphore = consumerSemaphore;
        m_queue = queue;
    }

    public void setVal(int val)
    {
        ThreadUtil.acquire(m_producerSemaphore, 1);
        m_queue.addLast(val);
        ThreadUtil.release(m_consumerSemaphore, 1);
    }

    public OptionalInt getVal()
    {
        ThreadUtil.acquire(m_consumerSemaphore, 1);
        var result = m_queue.pollFirst();
        ThreadUtil.release(m_producerSemaphore, 1);

        return result != null ? OptionalInt.of(result) : OptionalInt.empty();
    }
}
