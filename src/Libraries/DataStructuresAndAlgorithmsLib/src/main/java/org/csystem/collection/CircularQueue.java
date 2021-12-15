/*----------------------------------------------------------------------
    FILE        : CircularQueue.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 15.12.2021

    CircularQueue class that is the implementation of circular queue (FIFO)

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.collection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CircularQueue<T> {
    private static final int DEFAULT_COUNT = 10;
    private final T [] m_items;
    private int m_head;
    private int m_tail;
    private int m_size;

    @SuppressWarnings("unchecked")
    public CircularQueue()
    {
        this(DEFAULT_COUNT);
    }

    @SuppressWarnings("unchecked")
    public CircularQueue(int count)
    {
        if (count <= 0)
            throw new IllegalArgumentException("count must be positive:" + count);

        m_items = (T []) Array.newInstance(Object[].class.getComponentType(), count);
    }

    public void clear()
    {
        IntStream.range(m_head, m_tail).forEach(i -> m_items[i] = null);
        m_head = m_tail = m_size = 0;
    }

    public int count()
    {
        return m_items.length;
    }

    public Optional<T> findFirst(Predicate<T> predicate)
    {
        return Arrays.stream(m_items, m_head, m_tail).filter(predicate).findFirst();
    }

    public Optional<T> findLast(Predicate<T> predicate)
    {
        return Arrays.stream(m_items, m_head, m_tail).filter(predicate).reduce((r, s) -> s);
    }

    public Optional<T> getItem()
    {
        if (m_size == 0)
            return Optional.empty();

        var result = Optional.of(m_items[m_head]);
        m_items[m_head++] = null;
        m_head %= m_items.length;
        --m_size;

        return result;
    }

    public boolean putItem(T item)
    {
        if (m_items.length == m_size)
            return false;

        m_items[m_tail++] = item;
        m_tail %= m_items.length;
        ++m_size;

        return true;
    }

    public int size()
    {
        return m_size;
    }

    public void walk(Consumer<T> consumer)
    {
        Arrays.stream(m_items, m_head, m_tail).forEach(consumer);
    }
}
