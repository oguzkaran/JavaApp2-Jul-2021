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
import java.util.Random;

@RunWith(Parameterized.class)
public class Test_findLastItemIndex {
    private static final Random ms_random = new Random();
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

        for (var str : m_list)
            m_testList.addItemTail(str);
    }

    public Test_findLastItemIndex(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_findLastItemIndex()
    {
        var random = new Random();
        var pos = random.nextInt(m_list.size());
        var str = m_list.get(pos);

        Assert.assertEquals(m_list.lastIndexOf(str), m_testList.findLastItemIndex(str));
    }

    @Test
    public void test_findLastItemIndexNotFound()
    {
        var str = "Muhammet";

        Assert.assertEquals(-1, m_testList.findLastItemIndex(str));
    }
}
