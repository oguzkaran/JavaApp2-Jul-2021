package org.csystem.util.collection.slinkedlist;

import org.csystem.collection.SLinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test_getItemHead_OptionalEmpty {
    private SLinkedList<String> m_testList;
    @Before
    public void setUp()
    {
        m_testList = new SLinkedList<>();
    }

    @Test
    public void test_getItemHead()
    {
        Assert.assertTrue(m_testList.getItemHead().isEmpty());
    }
}
