package org.csystem.util.collection.slinkedlist;

import org.csystem.collection.SLinkedList;
import org.csystem.factory.StringDataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_getItemTail_OptionalNotEmpty {
    private final List<String> m_list;
    private SLinkedList<String> m_testList;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getData();
    }

    @Before
    public void setUp()
    {
        m_testList = new SLinkedList<>();
        m_list.forEach(m_testList::addItemTail);
    }

    public Test_getItemTail_OptionalNotEmpty(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_getItemTail()
    {
        Assert.assertEquals(m_list.get(m_list.size() - 1), m_testList.getItemTail().get());
    }
}
