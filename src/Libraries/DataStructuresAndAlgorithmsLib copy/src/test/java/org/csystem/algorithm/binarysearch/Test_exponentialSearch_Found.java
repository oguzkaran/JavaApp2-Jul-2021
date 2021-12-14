package org.csystem.algorithm.binarysearch;

import org.csystem.algorithm.ArrayAlgorithm;
import org.csystem.factory.StringDataFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class Test_exponentialSearch_Found {
    private static final Comparator<String> ms_comparator = String::compareToIgnoreCase;
    private final String [] m_str;
    private final Random m_random = new Random();

    @Parameterized.Parameters
    public static Collection<List<String>> createData()
    {
        return StringDataFactory.getOrderedData(ms_comparator);
    }

    public Test_exponentialSearch_Found(List<String> list)
    {
        m_str = list.toArray(new String[0]);
    }

    @Test
    public void test_exponentialSearch()
    {
        var s = m_str[m_random.nextInt(m_str.length)];
        var expected = m_str[Arrays.binarySearch(m_str, s, ms_comparator)];
        var actual = ArrayAlgorithm.exponentialSearch(m_str, s, ms_comparator).get();

        Assert.assertEquals(expected, actual);
    }
}
