/*----------------------------------------------------------------------
    FILE        : DoubleCircularQueue.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 17.11.2021

    DoubleCircularQueue class that is the implementation of circular queue (FIFO) of doubles

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.collection;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

public class DoubleCircularQueue {
    private static final int DEFAULT_COUNT = 10;
    private final double [] m_items;
    private int m_head;
    private int m_tail;
    private int m_size;

    public DoubleCircularQueue()
    {
        //this(DEFAULT_COUNT);
        m_items = new double[DEFAULT_COUNT];
    }

    public DoubleCircularQueue(int count)
    {
        if (count <= 0)
            throw new IllegalArgumentException("count must be positive:" + count);

        m_items = new double[count];
    }

    public void clear()
    {
        m_head = m_tail = m_size = 0;
    }

    public int count()
    {
        return m_items.length;
    }

    public OptionalDouble findFirst(DoublePredicate predicate)
    {
        return Arrays.stream(m_items, m_head, m_tail).filter(predicate).findFirst();
    }

    public OptionalDouble findLast(DoublePredicate predicate)
    {
        return Arrays.stream(m_items, m_head, m_tail).filter(predicate).findFirst();
    }

    public OptionalDouble getItem()
    {
        if (m_size == 0)
            return OptionalDouble.empty();

        var result = OptionalDouble.of(m_items[m_head++]);
        m_head %= m_items.length;
        --m_size;

        return result;
    }

    public boolean putItem(double item)
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

    public void walk(DoubleConsumer consumer)
    {
        Arrays.stream(m_items, m_head, m_tail).forEach(consumer);
    }
}
