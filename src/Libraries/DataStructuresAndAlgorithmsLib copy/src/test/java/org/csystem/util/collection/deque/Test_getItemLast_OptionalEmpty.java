package org.csystem.util.collection.deque;

import org.csystem.collection.Deque;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test_getItemLast_OptionalEmpty {
    private Deque<String> m_deque;

    @Before
    public void setUp()
    {
        m_deque = new Deque<>();
    }

    @Test
    public void test_getItemLast()
    {
        Assert.assertTrue(m_deque.getItemLast().isEmpty());
    }
}
