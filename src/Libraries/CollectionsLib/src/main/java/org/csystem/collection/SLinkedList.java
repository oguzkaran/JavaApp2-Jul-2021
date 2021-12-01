/*----------------------------------------------------------------------
    FILE        : SLinkedList.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 01.12.2021

    SLinkedList class that is the implementation of singly linked list

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SLinkedList<T> implements Iterable<T> {
    private Node<T> m_head;
    private Node<T> m_tail;
    private int m_size;

    private static class Node<T> {
        T item;
        Node<T> next;
        Node(T item)
        {
            this.item = item;
        }
    }

    public void addItemHead(T item)
    {
        var newNode = new Node<T>(item);

        if (m_head != null) {
            newNode.next = m_head;
            m_head = newNode;
        }
        else
            m_head = m_tail = newNode;

        ++m_size;
    }

    public void addItemTail(T item)
    {
        var newNode = new Node<T>(item);

        if (m_head != null) {
            m_tail.next = newNode;
            m_tail = newNode;
        }
        else
            m_head = m_tail = newNode;

        ++m_size;
    }

    public void clear()
    {
        m_head = m_tail = null;
        m_size = 0;
    }

    public void deleteItem(int pos)
    {
        if (pos >= m_size || pos < 0)
            throw new IndexOutOfBoundsException("Index out bounds:" + pos);

        if (pos == 0)
            deleteItemHead();
        else {
            var node = m_head;

            for (int i = 0; i < pos - 1; node = node.next, ++i)
                ;

            node.next = node.next.next;
            --m_size;
        }
    }

    public void deleteItemHead()
    {
        if (m_head == null)
            return;

        if (m_head != m_tail)
            m_head = m_head.next;
        else
            m_head = m_tail = null;

        --m_size;
    }

    public void deleteItemTail()
    {
        if (m_head == null)
            return;

        if (m_head != m_tail) {
            Node<T> node = m_head;

            for (; node.next != m_tail; node = node.next)
                ;

            node.next = null;
            m_tail = node;
        }
        else
            m_head = m_tail = null;

        --m_size;
    }

    public Optional<T> findFirst(Predicate<T> pred)
    {
        for (var node = m_head; node != null; node = node.next)
            if (pred.test(node.item))
                return Optional.of(node.item);

        return Optional.empty();
    }

    public int findFirstItemIndex(T item)
    {
        var curNode = m_head;

        for (int i = 0; curNode != null; curNode = curNode.next, ++i)
            if (Objects.equals(item, curNode.item))
                return i;

        return -1;
    }

    public T get(int pos)
    {
        if (pos >= m_size || pos < 0)
            throw new IndexOutOfBoundsException("Index out bounds:" + pos);

        var node = m_head;

        for (int i = 0; i < pos; node = node.next, ++i)
            ;

        return node.item;
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
        if (pos < 0 || m_size < pos)
            throw new IndexOutOfBoundsException("Index out of bounds:" + pos);

        if (m_head != null) {
            if (pos != 0) {
                var node = m_head;

                for (int i = 1; i <= pos - 1; ++i, node = node.next)
                    ;
                var newNode = new Node<T>(item);

                newNode.next = node.next;
                node.next = newNode;
                ++m_size;
            }
            else
                addItemHead(item);
        }
        else
            addItemTail(item);

    }

    public boolean isEmpty()
    {
        return m_head == null;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new Iterator<>() {
            Node<T> node;

            @Override
            public boolean hasNext()
            {
                node = node == null ? m_head : node.next;

                return node != null;
            }

            @Override
            public T next()
            {
                if (node == null)
                    throw new NoSuchElementException("No such element in list");

                return node.item;
            }
        };
    }

    public int size()
    {
        return m_size;
    }

    public void walk(Consumer<T> con)
    {
        for (var node = m_head; node != null; node = node.next)
            con.accept(node.item);
    }
}
