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
public class Test_get {
    private final List<String> m_list;
    private DLinkedList<String> m_testList;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getDataWithEmptyList();
    }

    @Before
    public void setUp()
    {
        m_testList = new DLinkedList<>();
        m_list.forEach(m_testList::addItemTail);
    }

    public Test_get(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_get()
    {
        if (!m_list.isEmpty()) {
            var random = new Random();
            var pos = random.nextInt(m_list.size());
            Assert.assertEquals(m_list.get(pos), m_testList.get(pos));
        }
        else
            Assert.assertThrows(IndexOutOfBoundsException.class, () -> m_testList.get(0));
    }
}
