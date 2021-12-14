/*----------------------------------------------------------------------
    FILE        : Deque.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 06.12.2021

    Deque class that is the implementation of double ended queue

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.collection;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

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
            throw new IllegalArgumentException("initialCapacity can be negative:" + initialCapacity);

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
        IntStream.range(m_head, m_tail).forEach(i -> m_items[i] = null);
        m_head = m_tail = m_items.length / 2;
    }

    public void deleteItem(int index)
    {
        throw new UnsupportedOperationException("deleteItem");
    }

    public void deleteItemFirst()
    {
        throw new UnsupportedOperationException("deleteItemFirst");
    }

    public void deleteItemLast()
    {
        throw new UnsupportedOperationException("deleteItemLast");
    }

    public T get(int index)
    {
        if (index < 0 || size() <= index)
            throw new IndexOutOfBoundsException("Index out of bounds:" + index);

        return m_items[m_head + index];
    }

    public Optional<T> getItemFirst()
    {
        return size() == 0 ? Optional.empty() : Optional.of(m_items[m_head]);
    }

    public Optional<T> getItemLast()
    {
        return size() == 0 ? Optional.empty() : Optional.of(m_items[m_tail - 1]);
    }

    public boolean isEmpty()
    {
        return size() == 0;
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
