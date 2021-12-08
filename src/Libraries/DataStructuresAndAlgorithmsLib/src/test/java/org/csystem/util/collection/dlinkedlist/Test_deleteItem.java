package org.csystem.util.collection.dlinkedlist;

import org.csystem.collection.DLinkedList;
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
import java.util.Random;

@RunWith(Parameterized.class)
public class Test_deleteItem {
    private static int ms_count;
    private static final String ms_expectedBase = "dlist_delete_item_expected";
    private static final String ms_actualBase = "dlist_delete_item_actual";
    private final List<String> m_list;
    private DLinkedList<String> m_testList;

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
        return StringDataFactory.getDataWithEmptyList();
    }

    @Before
    public void setUp()
    {
        m_testList = new DLinkedList<>();

        m_list.forEach(m_testList::addItemTail);

        --ms_count;
    }

    public Test_deleteItem(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_deleteItem() throws IOException
    {
        var random = new Random();

        int pos = 0;

        if (!m_list.isEmpty()) {
            pos = random.nextInt(m_list.size());
            m_list.remove(pos);
            m_testList.deleteItem(pos);
            saveActual();
            saveExpected();
            Assert.assertTrue(FileUtil.areSame(ms_expectedBase + "-" + ms_count + ".txt", ms_actualBase + "-" + ms_count + ".txt"));
        }
        else
            Assert.assertThrows(IndexOutOfBoundsException.class, () -> m_testList.deleteItem(0));
    }
}
