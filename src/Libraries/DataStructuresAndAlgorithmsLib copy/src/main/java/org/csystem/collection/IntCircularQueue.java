/*----------------------------------------------------------------------
    FILE        : IntCircularQueue.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 15.11.2021

    IntCircularQueue class that is the implementation of circular queue (FIFO) of ints

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.collection;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

public class IntCircularQueue {
    private static final int DEFAULT_COUNT = 10;
    private final int [] m_items;
    private int m_head;
    private int m_tail;
    private int m_size;

    public IntCircularQueue()
    {
        //this(DEFAULT_COUNT);
        m_items = new int[DEFAULT_COUNT];
    }

    public IntCircularQueue(int count)
    {
        if (count <= 0)
            throw new IllegalArgumentException("count must be positive:" + count);

        m_items = new int[count];
    }

    public void clear()
    {
        m_head = m_tail = m_size = 0;
    }

    public int count()
    {
        return m_items.length;
    }

    public OptionalInt findFirst(IntPredicate predicate)
    {
        return Arrays.stream(m_items, m_head, m_tail).filter(predicate).findFirst();
    }

    public OptionalInt  findLast(IntPredicate predicate)
    {
        return Arrays.stream(m_items, m_head, m_tail).filter(predicate).findFirst();
    }

    public OptionalInt getItem()
    {
        if (m_size == 0)
            return OptionalInt.empty();

        var result = OptionalInt.of(m_items[m_head++]);
        m_head %= m_items.length;
        --m_size;

        return result;
    }

    public boolean putItem(int item)
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

    public void walk(IntConsumer consumer)
    {
        Arrays.stream(m_items, m_head, m_tail).forEach(consumer);
    }
}
