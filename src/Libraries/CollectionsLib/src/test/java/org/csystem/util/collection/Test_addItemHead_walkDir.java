package org.csystem.util.collection;

import org.csystem.util.collection.util.collection.DLinkedList;
import org.csystem.util.io.file.FileUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class Test_addItemHead_walkDir {
    private final List<String> m_list;

    private void saveExpected()
    {
        try (var bw = Files.newBufferedWriter(Path.of("expecteds.txt"))) {
            for (var i = m_list.size() - 1; i >= 0; --i)
                bw.write(m_list.get(i) + "\r\n");

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

    public Test_addItemHead_walkDir(List<String> list)
    {
        m_list = list;
        saveExpected();
    }

    @Test
    public void test_AddItemHeadSize() throws IOException
    {
        var list = new DLinkedList<String>();

        for (var str : m_list)
            list.addItemHead(str);

        saveActual(list);

        Assert.assertTrue(FileUtil.areSame("expecteds.txt", "actuals.txt"));
    }
}
