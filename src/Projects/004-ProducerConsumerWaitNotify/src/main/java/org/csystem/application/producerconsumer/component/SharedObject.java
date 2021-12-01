package org.csystem.application.producerconsumer.component;

import org.csystem.util.thread.ThreadUtil;
import org.springframework.stereotype.Component;

@Component
public class SharedObject {
    private int m_val;
    private volatile boolean m_produce = true;

    public synchronized void setVal(int val)
    {
        while (!m_produce)
            ThreadUtil.wait(this);

        m_val = val;
        m_produce = false;

        ThreadUtil.notify(this);
    }

    public synchronized int getVal()
    {
        while (m_produce)
            ThreadUtil.wait(this);

        int val = m_val;
        m_produce = true;

        ThreadUtil.notify(this);

        return val;
    }
}
