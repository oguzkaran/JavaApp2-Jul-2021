package org.csystem.util.collection.deque;

import org.csystem.collection.Deque;
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
    private Deque<String> m_testDeque;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getDataWithEmptyList();
    }

    @Before
    public void setUp()
    {
        m_testDeque = new Deque<>();
        m_list.forEach(m_testDeque::addItemLast);
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
            Assert.assertEquals(m_list.get(pos), m_testDeque.get(pos));
        }
        else
            Assert.assertThrows(IndexOutOfBoundsException.class, () -> m_testDeque.get(0));
    }
}
