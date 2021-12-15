/*----------------------------------------------------------------------
    FILE        : ArrayStack.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 15.12.2021

    ArrayStack class that is the implementation of stack (LIFO)

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

public class ArrayStack<T> {
    private static final int DEFAULT_COUNT = 10;
    private final T [] m_items;
    private int m_index;

    public ArrayStack()
    {
        this(DEFAULT_COUNT);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int count)
    {
        if (count <= 0)
            throw new IllegalArgumentException("count must be positive:" + count);

        m_items = (T [])Array.newInstance(Object[].class.getComponentType(), count);
        m_index = m_items.length;
    }


    public void clear()
    {
        IntStream.range(m_index, m_items.length).forEach(i -> m_items[i] = null);
        m_index = m_items.length;
    }

    public int count()
    {
        return m_items.length;
    }

    public Optional<T> findFirst(Predicate<T> predicate)
    {
        return Arrays.stream(m_items, m_index, m_items.length).filter(predicate).findFirst();
    }

    public Optional<T> findLast(Predicate<T> predicate)
    {
        return Arrays.stream(m_items, m_index, m_items.length).filter(predicate).reduce((r, s) -> s);
    }

    public boolean isEmpty()
    {
        return m_index == m_items.length;
    }

    public Optional<T> pop()
    {
        if (m_index == m_items.length)
            return Optional.empty();

        var item = m_items[m_index];

        m_items[m_index++] = null; //***
        return Optional.of(item);
    }

    public boolean push(T item)
    {
        if (m_index == 0)
            return false;

        m_items[--m_index] = item;

        return true;
    }

    public int size()
    {
        return m_items.length - m_index;
    }

    public Optional<T> top()
    {
        if (m_index == m_items.length)
            return Optional.empty();

        return Optional.of(m_items[m_index]);
    }

    public void walk(Consumer<T> consumer)
    {
        Arrays.stream(m_items, m_index, m_items.length).forEach(consumer);
    }
}
