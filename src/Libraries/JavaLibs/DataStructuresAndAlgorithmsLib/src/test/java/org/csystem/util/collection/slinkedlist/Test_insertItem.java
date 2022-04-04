package org.csystem.util.collection.slinkedlist;

import org.csystem.collection.SLinkedList;
import org.csystem.factory.StringDataFactory;
import org.csystem.util.io.file.FileUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_insertItem {
    private static int ms_count;
    private static final String ms_expectedBase = "slist_insert_item_expected";
    private static final String ms_actualBase = "slist_insert_item_actual";
    private final List<String> m_list;
    private SLinkedList<String> m_testList;

    private void saveExpected()
    {
        try (var bw = Files.newBufferedWriter(Path.of(ms_expectedBase + "-" + ms_count + ".txt"))) {
            for (var str : m_list)
                bw.write(str + "\r\n");

            bw.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveActual()
    {
        try (var bw = Files.newBufferedWriter(Path.of(ms_actualBase + "-" + ms_count + ".txt"))) {
            m_testList.walk(str -> {
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
        return StringDataFactory.getData();
    }

    @Before
    public void setUp()
    {
        m_testList = new SLinkedList<>();
    }

    public Test_insertItem(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_insertItem() throws IOException
    {
        for (int i = 0; i < m_list.size(); ++i)
            m_testList.insertItem(i, m_list.get(i));

        ++ms_count;
        saveActual();
        saveExpected();

        Assert.assertTrue(FileUtil.areSame(ms_expectedBase + "-" + ms_count + ".txt", ms_actualBase + "-" + ms_count + ".txt"));
    }
}
