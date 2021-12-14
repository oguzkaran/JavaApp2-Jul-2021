package org.csystem.algorithm.binarysearch;

import org.csystem.algorithm.ArrayAlgorithm;
import org.csystem.factory.StringDataFactory;
import org.csystem.util.string.StringUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class Test_binarySearch {
    private static final Comparator<String> ms_comparator = String::compareToIgnoreCase;
    private final String [] m_str;
    private final Random m_random = new Random();

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getOrderedData(ms_comparator);
    }

    public Test_binarySearch(List<String> list)
    {
        m_str = list.toArray(new String[0]);
    }

    @Test
    public void test_binarySearch()
    {
        var s = StringUtil.getRandomTextTR(m_random, m_random.nextInt(20) + 10);
        var index = Arrays.binarySearch(m_str, s, ms_comparator);
        var actualOpt = ArrayAlgorithm.binarySearch(m_str, s, ms_comparator);

        if (index >= 0 && actualOpt.isPresent()) {
            var expected = m_str[index];
            var actual = actualOpt.get();

            Assert.assertEquals(expected, actual);
        }
        else
            Assert.assertTrue(actualOpt.isEmpty());
    }
}
