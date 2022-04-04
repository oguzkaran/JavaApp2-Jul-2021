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
import java.util.Random;

@RunWith(Parameterized.class)
public class Test_findFirst {
    private static final Random ms_random = new Random();
    private final List<String> m_list;
    private SLinkedList<String> m_testList;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getDataWithEmptyList();
    }

    @Before
    public void setUp()
    {
        m_testList = new SLinkedList<>();
        m_list.forEach(m_testList::addItemTail);
    }

    public Test_findFirst(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_findFirst()
    {
        if (!m_list.isEmpty()) {
            var random = new Random();
            var pos = random.nextInt(m_list.size());

            var str = m_list.get(pos);
            Assert.assertEquals(m_list.get(m_list.indexOf(str)), m_testList.findFirst(s -> s.equals(str)).get());
        }
        else
            Assert.assertTrue(m_testList.findFirst(s -> s.equals("Muhammet")).isEmpty());
    }

}
