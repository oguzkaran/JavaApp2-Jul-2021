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

@RunWith(Parameterized.class)
public class Test_size {
    private final List<String> m_list;
    private CircularQueue<String> m_testQueue;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getData();
    }

    @Before
    public void setUp()
    {
        m_testQueue = new CircularQueue<>();
    }

    public Test_size(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_size() throws IOException
    {
        m_list.forEach(s -> m_testQueue.putItem(s));

        Assert.assertEquals(m_list.size(), m_testQueue.size());
    }
}
