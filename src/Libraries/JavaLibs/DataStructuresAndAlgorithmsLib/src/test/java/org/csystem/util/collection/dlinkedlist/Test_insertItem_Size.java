package org.csystem.util.collection.dlinkedlist;

import org.csystem.collection.DLinkedList;
import org.csystem.factory.StringDataFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_insertItem_Size {
    private final List<String> m_list;

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getData();
    }

    public Test_insertItem_Size(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_insertItem_Pos_Begin_Size()
    {
        var list = new DLinkedList<String>();

        m_list.forEach(s -> list.insertItem(0, s));

        Assert.assertEquals(m_list.size(), list.size());
    }

    @Test
    public void test_insertItem_Pos_End_Size()
    {
        var list = new DLinkedList<String>();

        m_list.forEach(s -> list.insertItem(list.size(), s));

        Assert.assertEquals(m_list.size(), list.size());
    }
}
