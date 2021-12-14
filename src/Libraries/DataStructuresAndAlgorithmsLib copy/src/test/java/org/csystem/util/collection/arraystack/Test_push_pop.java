package org.csystem.util.collection.arraystack;

import org.csystem.collection.ArrayStack;
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
import java.util.Optional;

@RunWith(Parameterized.class)
public class Test_push_pop {
    private static int ms_count;
    private static final String ms_expectedBase = "arraystack_push_expected";
    private static final String ms_actualBase = "arraystack_push_actual";
    private final List<String> m_list;
    private ArrayStack<String> m_testStack;

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
            Optional<String> optStr;

            while ((optStr = m_testStack.pop()).isPresent())
                bw.write(optStr.get() + "\r\n");

            bw.flush();
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
        m_testStack = new ArrayStack<>(m_list.size());
    }

    public Test_push_pop(List<String> list)
    {
        m_list = list;
    }

    @Test
    public void test_push_pop() throws IOException
    {
        m_list.forEach(m_testStack::push);

        ++ms_count;
        saveActual();
        saveExpected();

        Assert.assertTrue(FileUtil.areSame(ms_expectedBase + "-" + ms_count + ".txt", ms_actualBase + "-" + ms_count + ".txt"));
    }
}
