/*----------------------------------------------------------------------
FILE        : Vector.java
AUTHOR      : JavaApp2-Jul-2021 group
LAST UPDATE : 03.10.2021

Vector class that is used for dynamic array

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.collection.java;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Vector<T> extends AbstractList<T> implements List<T>, RandomAccess, Cloneable, Serializable {
    private static final int DEFAULT_CAPACITY = 10;
    private T [] m_elems;
    private int m_index;

    private static void throwIndexOfBoundsException(String msg)
    {
        throw new IndexOutOfBoundsException(msg);
    }

    private void checkIndexOfBoundsException(int index, String msg)
    {
        if (index < 0 || index >= m_index)
            throwIndexOfBoundsException(msg);
    }

    @SuppressWarnings("unchecked")
    public Vector()
    {
        m_elems = (T[])new Object[DEFAULT_CAPACITY];
        throw new UnsupportedOperationException();
    }

    public Vector(int initialCapacity)
    {
        throw new UnsupportedOperationException();
    }

    public Vector(int initialCapacity, int capacityIncrement)
    {
        throw new UnsupportedOperationException();
    }

    public Vector(Collection<? extends T> collection)
    {
        throw new UnsupportedOperationException();
    }

    public int capacity()
    {
        return m_elems.length;
    }

    @Override
    public T get(int index)
    {
        checkIndexOfBoundsException(index, "invalid value: index < 0 || index >= size():" + index);

        return m_elems[index];
    }

    @Override
    public int size()
    {
        return m_index;
    }

    @Override
    public T set(int index, T element)
    {
        checkIndexOfBoundsException(index, "invalid value: index < 0 || index >= size():" + index);

        T old = m_elems[index];

        m_elems[index] = element;

        return old;
    }

    ///////////////////////////////

    @Override
    public void forEach(Consumer<? super T> action)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Spliterator<T> spliterator()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<T> stream()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<T> parallelStream()
    {
        throw new UnsupportedOperationException();
    }


    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super T> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object clone()
    {
        throw new UnsupportedOperationException();
    }
}
