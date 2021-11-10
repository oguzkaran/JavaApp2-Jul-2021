package org.csystem.util.collection.dlinkedlist;

import org.csystem.util.collection.util.collection.DLinkedList;
import org.csystem.util.io.file.FileUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_deleteItemHead {
    private final List<String> m_list;
    private DLinkedList<String> m_testList;

    private void saveExpected()
    {
        try (var bw = Files.newBufferedWriter(Path.of("expecteds.txt"))) {
            m_list.remove(0);
            for (var str : m_list)
                bw.write(str + "\r\n");

            bw.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveActual(DLinkedList<String> list)
    {
        try (var bw = Files.newBufferedWriter(Path.of("actuals.txt"))) {
            list.walkList(str -> {
                try {
                    bw.write(str + "\r\n");
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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

        Assert.assertEquals(m_list.size(), m_testList.size());
    }

    public Test_deleteItemHead(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_deleteItemHead() throws IOException
    {
        m_testList.deleteItemHead();
        saveActual(m_testList);
        saveExpected();

        Assert.assertTrue(FileUtil.areSame("expecteds.txt", "actuals.txt"));
    }
}