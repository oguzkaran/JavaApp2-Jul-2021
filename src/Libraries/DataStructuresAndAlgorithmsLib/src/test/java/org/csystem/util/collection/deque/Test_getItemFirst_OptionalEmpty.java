package org.csystem.util.collection.deque;

import org.csystem.collection.Deque;
import org.csystem.collection.SLinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test_getItemFirst_OptionalEmpty {
    private Deque<String> m_deque;

    @Before
    public void setUp()
    {
        m_deque = new Deque<>();
    }

    @Test
    public void test_getItemFirst()
    {
        Assert.assertTrue(m_deque.getItemFirst().isEmpty());
    }
}
