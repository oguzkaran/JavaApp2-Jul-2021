package org.csystem.util.collection.circularqueue;

import org.csystem.collection.CircularQueue;
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
public class Test_getItem {
    private static int ms_count;
    private static final String ms_expectedBase = "circularqueue_get_item_expected";
    private static final String ms_actualBase = "circularqueue_get_item_actual";
    private final List<String> m_list;
    private CircularQueue<String> m_testQueue;

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
            for (int i = 0; i < m_list.size(); ++i) {
                try {
                    bw.write(m_testQueue.getItem().get() + "\r\n");
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
        m_testQueue = new CircularQueue<>();
    }

    public Test_getItem(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_getItem() throws IOException
    {
        for (var s : m_list)
            m_testQueue.putItem(s);

        ++ms_count;
        saveActual();
        saveExpected();

        Assert.assertTrue(FileUtil.areSame(ms_expectedBase + "-" + ms_count + ".txt", ms_actualBase + "-" + ms_count + ".txt"));

    }
}
