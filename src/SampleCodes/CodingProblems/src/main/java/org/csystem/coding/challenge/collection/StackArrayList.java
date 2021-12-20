package org.csystem.coding.challenge.collection;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class StackArrayList<E> {
    private final int m_stackSize;
    private final ArrayList<Stack<E>> m_stackList;

    private void removeStack(Stack<E> stack)
    {
        if (stack.isEmpty())
            m_stackList.remove(stack);
    }

    private Stack<E> shift(int stackIndex)
    {
        Stack<E> nextStack = null;

        for (int i = stackIndex; i < m_stackList.size() - 1; ++i) {
            var curStack = m_stackList.get(i);
            nextStack = m_stackList.get(i + 1);

            curStack.push(nextStack.remove(0));
        }

        return nextStack;
    }

    public StackArrayList(int stackSize)
    {
        m_stackSize = stackSize;
        m_stackList = new ArrayList<>();
    }

    public void push(E item) //~O(1)
    {
        Stack<E> stack;

        if (m_stackList.isEmpty() || (stack = m_stackList.get(m_stackList.size() - 1)).size() >= m_stackSize) {
            stack = new Stack<>();
            stack.push(item);
            m_stackList.add(stack);
        }
        else
            stack.push(item);
    }

    public E pop() //O(n)
    {
        if (m_stackList.isEmpty())
            throw new EmptyStackException();

        var stack = m_stackList.get(m_stackList.size() - 1);

        E item = stack.pop();

        removeStack(stack);

        return item;
    }

    public E popAt(int stackIndex) //O(n)
    {
        E item = m_stackList.get(stackIndex).pop();

        removeStack(shift(stackIndex));

        return item;
    }

    public int size()
    {
        return m_stackList.size();
    }
}
