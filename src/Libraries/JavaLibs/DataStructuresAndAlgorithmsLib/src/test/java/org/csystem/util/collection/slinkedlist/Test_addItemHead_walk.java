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
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_addItemHead_walk {
    private static int ms_count;
    private static final String ms_expectedBase = "slist_add_item_head_walk_dir_expected";
    private static final String ms_actualBase = "slist_add_item_head_walk_dir_actual";
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

    public Test_addItemHead_walk(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_addItemHeadWalk() throws IOException
    {
        m_list.forEach(item -> m_testList.addItemHead(item));
        Collections.reverse(m_list);
        ++ms_count;
        saveActual();
        saveExpected();
        Assert.assertTrue(FileUtil.areSame(ms_expectedBase + "-" + ms_count + ".txt", ms_actualBase + "-" + ms_count + ".txt"));
    }
}
