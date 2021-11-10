/*----------------------------------------------------------------------
    FILE        : DLinkedList.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 10.11.2021

    DLinkedList class that is the implementation of doubly linked list

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.collection.util.collection;

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

    public int findFirstItemIndex(T item)
    {
        var curNode = m_head;


        for (int i = 0; curNode != null; curNode = curNode.next, ++i)
            if (Objects.equals(item, curNode.item))
                return i;

        /*

        if (item != null) {
            for (int i = 0; curNode != null; curNode = curNode.next, ++i)
                if (item.equals(curNode.item))
                    return i;
        }
        else
            for (int i = 0; curNode != null; curNode = curNode.next, ++i)
                if (curNode.item == null)
                    return i;

         */

        return -1;
    }

    public int findLastItemIndex(T item)
    {
        var curNode = m_tail;

        for (int i = m_size - 1; curNode != null; curNode = curNode.prev, --i)
            if (Objects.equals(item, curNode.item))
                return i;

        /*

        if (item != null) {
            for (int i = 0; curNode != null; curNode = curNode.next, ++i)
                if (item.equals(curNode.item))
                    return i;
        }
        else
            for (int i = 0; curNode != null; curNode = curNode.next, ++i)
                if (curNode.item == null)
                    return i;

         */

        return -1;
    }

    public T get(int pos)
    {
        if (pos >= m_size || pos < 0)
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
        if (pos > m_size || pos < 0)
            throw new IndexOutOfBoundsException("Index out bounds:" + pos);

        if (m_head != null) {
            if (pos == 0)
                addItemHead(item);
            else if (pos == m_size)
                addItemTail(item);
            else {
                var curNode = m_head;

                for (int i = 0; i < pos; curNode = curNode.next, ++i)
                    ;

                var newNode = new Node<>(item);

                newNode.next = curNode.next;
                newNode.prev = curNode;
                curNode.next = newNode;
                curNode.next.prev = newNode;
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
            Node<T> curNode;

            @Override
            public boolean hasNext()
            {
                curNode = curNode == null ? m_head : curNode.next;

                return curNode != null;
            }

            @Override
            public T next()
            {
                if (curNode == null)
                    throw new NoSuchElementException("No such element in list");

                return curNode.item;
            }
        };
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

    public Iterator<T> reverseIterator()
    {
        return new Iterator<>() {
            Node<T> curNode;

            @Override
            public boolean hasNext()
            {
                curNode = curNode == null ? m_tail : curNode.prev;

                return curNode != null;
            }

            @Override
            public T next()
            {
                if (curNode == null)
                    throw new NoSuchElementException("No such element in list");

                return curNode.item;
            }
        };
    }
}
