package org.csystem.util.collection.dlinkedlist;

import org.csystem.util.collection.util.collection.DLinkedList;
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
public class Test_getItemHead_Positive {
    private final List<String> m_list;
    private DLinkedList<String> m_testList;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        var list = new ArrayList<List<String>>();

        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("ayşe");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("ayşe"); add("fatma");}});

        return list;
    }

    @Before
    public void setUp()
    {
        m_testList = new DLinkedList<>();

        for (var str : m_list)
            m_testList.addItemTail(str);
    }

    public Test_getItemHead_Positive(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_getItemHead()
    {
        Assert.assertEquals(m_list.get(0), m_testList.getItemHead().get());
    }
}
