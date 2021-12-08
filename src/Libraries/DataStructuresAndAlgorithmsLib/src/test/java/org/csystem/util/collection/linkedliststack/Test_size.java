package org.csystem.util.collection.linkedliststack;

import org.csystem.collection.LinkedListStack;
import org.csystem.factory.StringDataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_size {
    private final List<String> m_list;
    private LinkedListStack<String> m_testStack;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getData();
    }

    @Before
    public void setUp()
    {
        m_testStack = new LinkedListStack<>();
        m_list.forEach(m_testStack::push);
    }

    public Test_size(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_size()
    {
        Assert.assertEquals(m_list.size(), m_testStack.size());
    }
}
