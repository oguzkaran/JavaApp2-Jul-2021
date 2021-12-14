/*----------------------------------------------------------------------
    FILE        : LongCircularQueue.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 15.11.2021

    LongCircularQueue class that is the implementation of circular queue (FIFO) of longs

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.collection;

import java.util.Arrays;
import java.util.OptionalLong;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;

public class LongCircularQueue {
    private static final int DEFAULT_COUNT = 10;
    private final long [] m_items;
    private int m_head;
    private int m_tail;
    private int m_size;

    public LongCircularQueue()
    {
        //this(DEFAULT_COUNT);
        m_items = new long[DEFAULT_COUNT];
    }

    public LongCircularQueue(int count)
    {
        if (count <= 0)
            throw new IllegalArgumentException("count must be positive:" + count);

        m_items = new long[count];
    }

    public void clear()
    {
        m_head = m_tail = m_size = 0;
    }

    public int count()
    {
        return m_items.length;
    }

    public OptionalLong findFirst(LongPredicate predicate)
    {
        return Arrays.stream(m_items, m_head, m_tail).filter(predicate).findFirst();
    }

    public OptionalLong  findLast(LongPredicate predicate)
    {
        return Arrays.stream(m_items, m_head, m_tail).filter(predicate).findFirst();
    }

    public OptionalLong getItem()
    {
        if (m_size == 0)
            return OptionalLong.empty();

        var result = OptionalLong.of(m_items[m_head++]);
        m_head %= m_items.length;
        --m_size;

        return result;
    }

    public boolean putItem(long item)
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

    public void walk(LongConsumer consumer)
    {
        Arrays.stream(m_items, m_head, m_tail).forEach(consumer);
    }
}
