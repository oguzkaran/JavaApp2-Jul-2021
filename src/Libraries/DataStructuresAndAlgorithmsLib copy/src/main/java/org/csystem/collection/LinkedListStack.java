/*----------------------------------------------------------------------
    FILE        : LinkedListStack.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 01.12.2021

    LinkedListStack class that is the implementation of stack (LIFO)

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.collection;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LinkedListStack<T> {
    private final DLinkedList<T> m_list = new DLinkedList<>();

    public void clear()
    {
        m_list.clear();
    }

    public int count()
    {
        return m_list.size();
    }

    public Optional<T> findFirst(Predicate<T> predicate)
    {
        return m_list.findFirst(predicate);
    }

    public Optional<T> findLast(Predicate<T> predicate)
    {
        return m_list.findLast(predicate);
    }

    public boolean isEmpty()
    {
        return m_list.isEmpty();
    }

    public Optional<T> pop()
    {
        var result =  m_list.getItemHead();

        m_list.deleteItemHead();

        return result;
    }

    public void push(T item)
    {
        m_list.addItemHead(item);
    }

    public int size()
    {
        return m_list.size();
    }

    public Optional<T> top()
    {
        return m_list.getItemHead();
    }

    public void walk(Consumer<T> consumer)
    {
        m_list.walk(consumer);
    }
}
