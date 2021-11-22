package org.csystem.util.collection.arraystack;

import org.csystem.collection.ArrayStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@RunWith(Parameterized.class)
public class Test_findLast {
    private final List<String> m_list;
    private ArrayStack<String> m_testStack;
    private final Random m_random = new Random();

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        var list = new ArrayList<List<String>>();

        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("ayşe"); add("selami");}});
        list.add(new ArrayList<>(){{add("ali"); add("selami"); add("veli"); add("selami");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("selami"); add("ayşe"); add("fatma");}});

        return list;
    }

    @Before
    public void setUp()
    {
        m_testStack = new ArrayStack<>(m_list.size());

        for (var s : m_list)
            m_testStack.push(s);
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

            Assert.assertEquals(m_testStack.findLast(str -> str.equals(s)), m_list.stream().filter(str->str.equals(s)).reduce((r, str) -> str));
        }
    }
}
