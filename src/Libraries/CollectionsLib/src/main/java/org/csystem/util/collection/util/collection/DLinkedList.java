/*----------------------------------------------------------------------
    FILE        : DLinkedList.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 08.11.2021

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

    public void clear()
    {
        for (var node = m_head; node != null; node = node.next)
            node.prev = null;

        /*
        for (var node = m_tail; node != null; node = node.prev)
            node.next = null;

         */

        m_head = m_tail = null;
        m_size = 0;
    }

    public void deleteItem(int pos)
    {
        if (pos >= m_size || pos < 0 || m_head == null)
            throw new IndexOutOfBoundsException("Index out bounds:" + pos);

        if (pos == 0)
            deleteItemHead();
        else if (pos == m_size - 1)
            deleteItemTail();
        else {
            var curNode = m_head;

            for (int i = 0; i < pos; curNode = curNode.next, ++i)
                ;

            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;
            --m_size;
        }
    }

    public void deleteItemHead()
    {
        if (m_head == null)
            return;

        if (m_head != m_tail) {
            m_head = m_head.next;
            m_head.prev = null;
        }
        else
            m_head = m_tail = null;

        --m_size;
    }

    public void deleteItemTail()
    {
        if (m_head == null)
            return;

        if (m_head != m_tail) {
            m_tail = m_tail.prev;
            m_tail.next = null;
        }
        else
            m_head = m_tail = null;

        --m_size;
    }

    public T get(int pos)
    {
        if (m_head == null || pos < 0 || pos >= m_size)
            throw new IndexOutOfBoundsException("Index out bounds:" + pos);

        var curNode = m_head;

        for (int i = 0; i < pos; curNode = curNode.next, ++i)
            ;

        return curNode.item;
    }

    public Optional<T> getItemHead()
    {
        return isEmpty() ? Optional.empty() : Optional.of(m_head.item);
    }

    public Optional<T> getItemTail()
    {
        return isEmpty() ? Optional.empty() : Optional.of(m_tail.item);
    }

    public void insertItem(int pos, T item)
    {
        //TODO:
    }

    public boolean isEmpty()
    {
        return m_head == null;
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
