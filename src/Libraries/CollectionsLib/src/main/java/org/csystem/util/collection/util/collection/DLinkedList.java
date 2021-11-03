/*----------------------------------------------------------------------
    FILE        : DLinkedList.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 03.11.2021

    DLinkedList class that is the implementation of double linked list

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.collection.util.collection;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DLinkedList<T> {
    private Node<T> m_head;
    private Node<T> m_tail;
    private int m_size;

    private static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;
        Node(T item)
        {
            this.item = item;
        }
    }

    public void addItemHead(T item)
    {
        var newNode = new Node<T>(item);

        if (m_head != null)
            m_head.prev = newNode;
        else
            m_tail = newNode;

        newNode.next = m_head;
        m_head = newNode;
        ++m_size;
    }

    public void addItemTail(T item)
    {
        var newNode = new Node<T>(item);

        if (m_head != null)
            m_tail.next = newNode;
        else
            m_head = newNode;

        newNode.prev = m_tail;
        m_tail = newNode;
        ++m_size;
    }

    void clear()
    {
        throw new UnsupportedOperationException();
    }

    public void deleteItemHead()
    {
        throw new UnsupportedOperationException();
    }

    public void deleteItemTail()
    {
        throw new UnsupportedOperationException();
    }

    public int size()
    {
        return m_size;
    }

    public void walkList(Consumer<T> con)
    {
        for (var node = m_head; node != null; node = node.next)
            con.accept(node.item);
    }

    public Optional<T> walkList(Predicate<T> pred)
    {
        for (var node = m_head; node != null; node = node.next)
            if (pred.test(node.item))
                return Optional.of(node.item);

        return Optional.empty();
    }

    public Optional<T> walkListReverse(Predicate<T> pred)
    {
        for (var node = m_tail; node != null; node =  node.prev)
            if (pred.test(node.item))
                return Optional.of(node.item);

        return Optional.empty();
    }
}
