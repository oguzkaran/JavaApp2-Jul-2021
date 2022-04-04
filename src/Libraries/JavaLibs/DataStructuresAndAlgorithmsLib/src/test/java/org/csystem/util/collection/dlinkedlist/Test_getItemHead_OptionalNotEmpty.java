package org.csystem.util.collection.dlinkedlist;

import org.csystem.collection.DLinkedList;
import org.csystem.factory.StringDataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_getItemHead_OptionalNotEmpty {
    private final List<String> m_list;
    private DLinkedList<String> m_testList;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getData();
    }

    @Before
    public void setUp()
    {
        m_testList = new DLinkedList<>();

        m_list.forEach(m_testList::addItemTail);
    }

    public Test_getItemHead_OptionalNotEmpty(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_getItemHead()
    {
        Assert.assertEquals(m_list.get(0), m_testList.getItemHead().get());
    }
}
