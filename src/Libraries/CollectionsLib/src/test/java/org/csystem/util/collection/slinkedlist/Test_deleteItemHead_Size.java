package org.csystem.util.collection.slinkedlist;

import org.csystem.collection.DLinkedList;
import org.csystem.collection.SLinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_deleteItemHead_Size {
    private final List<String> m_list;
    private SLinkedList<String> m_testList;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        var list = new ArrayList<List<String>>();

        list.add(new ArrayList<>());
        list.add(new ArrayList<>(){{add("ali");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("ayşe");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("ayşe"); add("fatma");}});

        return list;
    }

    @Before
    public void setUp()
    {
        m_testList = new SLinkedList<>();

        m_list.forEach(m_testList::addItemHead);
    }

    public Test_deleteItemHead_Size(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_deleteItemHeadSize()
    {
        var size = m_testList.size();

        for (int i = 0; i < size; ++i) {
            m_testList.deleteItemHead();
            Assert.assertEquals("Not true", size - 1 - i , m_testList.size());
        }
    }
}
