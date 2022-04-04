package org.csystem.util.collection.circularqueue;

import org.csystem.collection.CircularQueue;
import org.csystem.factory.StringDataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RunWith(Parameterized.class)
public class Test_findLast {
    private final List<String> m_list;
    private CircularQueue<String> m_testQueue;
    private final Random m_random = new Random();

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getData();
    }

    @Before
    public void setUp()
    {
        m_testQueue = new CircularQueue<>();

        for (var s : m_list)
            m_testQueue.putItem(s);
    }

    public Test_findLast(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_findLast() throws IOException
    {
        var count = m_random.nextInt(20) + 10;

        for (int i = 0; i < count; ++i) {
            var index = m_random.nextInt(m_list.size());
            var s = m_list.get(index);

            Assert.assertEquals(m_testQueue.findLast(str -> str.equals(s)), m_list.stream().filter(str->str.equals(s)).reduce((r, str) -> str));
        }
    }
}
