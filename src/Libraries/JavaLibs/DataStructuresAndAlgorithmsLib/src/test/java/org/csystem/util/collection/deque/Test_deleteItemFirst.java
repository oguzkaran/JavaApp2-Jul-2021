package org.csystem.util.collection.deque;

import org.csystem.collection.Deque;
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
public class Test_deleteItemFirst {
    private static final String ms_expectedBase = "deque_delete_item_first_expected";
    private static final String ms_actualBase = "deque_delete_item_first_actual";
    private static int ms_count;
    private final List<String> m_list;
    private Deque<String> m_testList;

    private void saveExpected()
    {
        try (var bw = Files.newBufferedWriter(Path.of(ms_expectedBase + "-" + ms_count + ".txt"))) {
            m_list.remove(m_list.size() - 1);
            for (var str : m_list)
                bw.write(str + "\r\n");

            bw.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveActual(Deque<String> list)
    {
        try (var bw = Files.newBufferedWriter(Path.of(ms_actualBase + "-" + ms_count + ".txt"))) {
            list.walk(str -> {
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
        m_testList = new Deque<>();

        m_list.forEach(m_testList::addItemLast);

        Assert.assertEquals(m_list.size(), m_testList.size());

        --ms_count;
    }

    public Test_deleteItemFirst(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_deleteItemLast() throws IOException
    {
        m_testList.deleteItemFirst();
        saveActual(m_testList);
        saveExpected();

        Assert.assertTrue(FileUtil.areSame(ms_expectedBase + "-" + ms_count + ".txt", ms_actualBase + "-" + ms_count + ".txt"));
    }
}
