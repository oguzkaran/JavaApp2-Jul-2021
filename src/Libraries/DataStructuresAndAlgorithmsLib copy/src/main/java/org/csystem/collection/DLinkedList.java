/*----------------------------------------------------------------------
    FILE        : DLinkedList.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 01.12.2021

    DLinkedList class that is the implementation of doubly linked list

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

public class DLinkedList<T> implements Iterable<T> {
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
        if (pos >= m_size || pos < 0)
            throw new IndexOutOfBoundsException("Index out bounds:" + pos);

        if (pos == 0)
            deleteItemHead();
        else if (pos == m_size - 1)
            deleteItemTail();
        else {
            var node = m_head;

            for (int i = 0; i < pos; node = node.next, ++i)
                ;

            node.prev.next = node.next;
            node.next.prev = node.prev;
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

    public Optional<T> findFirst(Predicate<T> pred)
    {
        for (var node = m_head; node != null; node = node.next)
            if (pred.test(node.item))
                return Optional.of(node.item);

        return Optional.empty();
    }

    public Optional<T> findLast(Predicate<T> pred)
    {
        for (var node = m_tail; node != null; node =  node.prev)
            if (pred.test(node.item))
                return Optional.of(node.item);

        return Optional.empty();
    }

    public int findFirstItemIndex(T item)
    {
        var node = m_head;

        for (int i = 0; node != null; node = node.next, ++i)
            if (Objects.equals(item, node.item))
                return i;

        return -1;
    }

    public int findLastItemIndex(T item)
    {
        var node = m_tail;

        for (int i = m_size - 1; node != null; node = node.prev, --i)
            if (Objects.equals(item, node.item))
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
            if (pos == 0)
                addItemHead(item);
            else if (pos == m_size)
                addItemTail(item);
            else {
                var node = m_head;

                for (int i = 0; i < pos; node = node.next, ++i)
                    ;

                var newNode = new Node<>(item);

                newNode.next = node.next;
                newNode.prev = node;
                node.next = newNode;
                node.next.prev = newNode;
                ++m_size;
            }
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

    public Iterator<T> reverseIterator()
    {
        return new Iterator<>() {
            Node<T> node;

            @Override
            public boolean hasNext()
            {
                node = node == null ? m_tail : node.prev;

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
}
