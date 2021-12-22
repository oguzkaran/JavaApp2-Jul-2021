package org.csystem.coding.challenge.collection;

import java.util.ArrayDeque;
import java.util.Queue;

public class StackViaQueue<E> {
    private final Queue<E> m_queue1;
    private final Queue<E> m_queue2;
    private E m_peek;
    private int m_size;

    public StackViaQueue()
    {
        m_queue1 = new ArrayDeque<>();
        m_queue2 = new ArrayDeque<>();
    }

    public void push(E item)
    {
        if (m_queue1.isEmpty()) {
            
        }
        else {

        }
    }

    public E pop()
    {
        throw new UnsupportedOperationException();
    }

    public E peek()
    {
        throw new UnsupportedOperationException();
    }

    public int size()
    {
        return m_size;
    }
}
