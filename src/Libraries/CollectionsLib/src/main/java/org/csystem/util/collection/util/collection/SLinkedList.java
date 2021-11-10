/*----------------------------------------------------------------------
    FILE        : SLinkedList.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 10.11.2021

    SLinkedList class that is the implementation of singly linked list

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.collection.util.collection;

import java.util.Iterator;
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
        throw new UnsupportedOperationException("addItemHead");
    }

    public void addItemTail(T item)
    {
        throw new UnsupportedOperationException("addItemTail");
    }

    public void clear()
    {
        throw new UnsupportedOperationException("clear");
    }

    public void deleteItem(int pos)
    {
        throw new UnsupportedOperationException("deleteItem");
    }

    public void deleteItemHead()
    {
        throw new UnsupportedOperationException("deleteItemHead");
    }

    public void deleteItemTail()
    {
        throw new UnsupportedOperationException("deleteItemTail");
    }

    public int findFirstItemIndex(T item)
    {
        throw new UnsupportedOperationException("findFirstItemIndex");
    }

    public int findLastItemIndex(T item)
    {
        throw new UnsupportedOperationException("findLastItemIndex");
    }

    public T get(int pos)
    {
        throw new UnsupportedOperationException("get");
    }

    public Optional<T> getItemHead()
    {
        throw new UnsupportedOperationException("getItemHead");
    }

    public Optional<T> getItemTail()
    {
        throw new UnsupportedOperationException("getItemTail");
    }

    public void insertItem(int pos, T item)
    {
        throw new UnsupportedOperationException("insertItem");
    }

    public boolean isEmpty()
    {
        return m_head == null;
    }

    @Override
    public Iterator<T> iterator()
    {
        throw new UnsupportedOperationException("iterator");
    }


    public int size()
    {
        return m_size;
    }

    public void walkList(Consumer<T> con)
    {
        throw new UnsupportedOperationException("walkList");
    }

    public Optional<T> walkList(Predicate<T> pred)
    {
        throw new UnsupportedOperationException("walkList");
    }


}
