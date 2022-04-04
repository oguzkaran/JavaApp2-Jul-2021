package org.csystem.util.collection.linkedliststack;

import org.csystem.collection.LinkedListStack;
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
public class Test_walk {
    private static int ms_count;
    private static final String ms_expectedBase = "llist_stack_walk_expected";
    private static final String ms_actualBase = "llist_stack_walk_actual";
    private final List<String> m_list;
    private LinkedListStack<String> m_testStack;

    private void saveExpected()
    {
        try (var bw = Files.newBufferedWriter(Path.of(ms_expectedBase + "-" + ms_count + ".txt"))) {
            Collections.reverse(m_list);
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
            m_testStack.walk(str -> {
                try {
                    bw.write(str + "\r\n");
                    bw.flush();
                }
                catch (IOException e) {
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
        m_testStack = new LinkedListStack<>();
    }

    public Test_walk(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_walk() throws IOException
    {
        for (var s : m_list)
            m_testStack.push(s);

        ++ms_count;
        saveActual();
        saveExpected();

        Assert.assertTrue(FileUtil.areSame(ms_expectedBase + "-" + ms_count + ".txt", ms_actualBase + "-" + ms_count + ".txt"));
    }
}
