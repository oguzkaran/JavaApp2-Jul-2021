/*----------------------------------------------------------------------
    FILE        : LinkedListQueue.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 01.12.2021

    LinkedListQueue adapter class that is the implementation of queue (FIFO)
    linked list

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.collection;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LinkedListQueue<T> {
    private final DLinkedList<T> m_list = new DLinkedList<>();

    public void clear()
    {
        m_list.clear();
    }

    public Optional<T> findFirst(Predicate<T> predicate)
    {
        return m_list.findFirst(predicate);
    }

    public Optional<T> findLast(Predicate<T> predicate)
    {
        return m_list.findLast(predicate);
    }

    public Optional<T> getItem()
    {
        var result =  m_list.getItemHead();

        m_list.deleteItemHead();

        return result;
    }

    public void putItem(T item)
    {
        m_list.addItemTail(item);
    }

    public Optional<T> peek()
    {
        return m_list.getItemHead();
    }

    public int size()
    {
        return m_list.size();
    }

    public void walk(Consumer<T> consumer)
    {
        m_list.walk(consumer);
    }
}
