package org.csystem.util.collection.dlinkedlist;

import org.csystem.collection.DLinkedList;
import org.csystem.factory.StringDataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_clear_Debug {
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

    public Test_clear_Debug(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_clear() throws IOException
    {
        m_testList.clear();

        Assert.assertEquals(0, m_testList.size());
    }
}
