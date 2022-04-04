package org.csystem.util;

import org.csystem.util.string.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class StringUtil_GetRandomText_TestCase {
    private final DataInfo m_dataInfo;
    private final Random m_random;

    private static class DataInfo {
        String sourceText;
        int count;

        public DataInfo(String sourceText, int count)
        {
            this.sourceText = sourceText;
            this.count = count;
        }

        public boolean isValid(String text)
        {
            var len = text.length();

            for (var i = 0; i < len; ++i)
                if (!sourceText.contains(text.charAt(i) + ""))
                    return false;

            return true;
        }
    }

    @Parameterized.Parameters
    public static Collection<DataInfo> createData() throws IOException
    {
        return Files.newBufferedReader(Path.of("getRandomText_test_textsources.txt"))
                .lines()
                .map(line -> line.split("[ \t]+"))
                .map(pi -> new DataInfo(pi[0], Integer.parseInt(pi[1])))
                .collect(Collectors.toList());
    }

    public StringUtil_GetRandomText_TestCase(DataInfo dataInfo)
    {
        m_dataInfo = dataInfo;
        m_random = new Random();
    }

    @Test()
    public void test_getRandomText()
    {
        var text = StringUtil.getRandomText(m_random, m_dataInfo.count, m_dataInfo.sourceText);

        assertEquals(m_dataInfo.count, text.length());
        assertTrue(m_dataInfo.isValid(text));
    }
}
