package org.csystem.util.collection;

import org.csystem.util.collection.util.collection.DLinkedList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_addItemHead_Size {
    private final List<String> m_list;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        var list = new ArrayList<List<String>>();

        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("ayşe");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami");}});
        list.add(new ArrayList<>(){{add("ali"); add("veli"); add("selami"); add("ayşe"); add("fatma");}});

        return list;
    }

    public Test_addItemHead_Size(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_AddItemHeadSize()
    {
        var list = new DLinkedList<String>();

        for (var str : m_list)
            list.addItemHead(str);

        Assert.assertEquals(m_list.size(), list.size());
    }
}
