/*----------------------------------------------------------------------
    FILE        : Deque.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 01.12.2021

    Deque class that is the implementation of double ended queue

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.collection;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class Deque<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T [] m_items;
    private int m_head;
    private int m_tail;

    @SuppressWarnings("unchecked")
    private void resizeDeque(boolean head)
    {
        var objs = new Object[m_items.length == 0 ? 1 : m_items.length * 2];

        if (head) {
            System.arraycopy(m_items, 0, objs, m_items.length, m_tail);
            m_head = m_items.length;
            m_tail = m_items.length + m_tail;
        }
        else
            System.arraycopy(m_items, m_head, objs, m_head, m_tail - m_head);

        m_items = (T[])objs;
    }

    public Deque()
    {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public Deque(int initialCapacity)
    {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("initialCapacity can be negativ:" + initialCapacity);

        m_items = (T[])new Object[initialCapacity];
        m_head = m_tail = m_items.length / 2;
    }

    public void addItemFirst(T item)
    {
        if (m_head == 0)
            resizeDeque(true);

        m_items[--m_head] = item;
    }

    public void addItemLast(T item)
    {
        if (m_tail == m_items.length)
            resizeDeque(false);

        m_items[m_tail++] = item;
    }

    public int capacity()
    {
        return m_items.length;
    }

    public void clear()
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public T get(int index)
    {
        if (index < 0 || size() <= index)
            throw new IndexOutOfBoundsException("Index out of bounds:" + index);

        return m_items[m_head + index];
    }

    public Optional<T> getItemFirst()
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public Optional<T> getItemLast()
    {
        throw new UnsupportedOperationException("TODO:");
    }

    public int size()
    {
        return m_tail - m_head;
    }

    public void walk(Consumer<T> consumer)
    {
        Arrays.stream(m_items, m_head, m_tail).forEach(consumer);
    }

    public void walkReverse(Consumer<T> consumer)
    {
        throw new UnsupportedOperationException("TODO:");
    }
}
