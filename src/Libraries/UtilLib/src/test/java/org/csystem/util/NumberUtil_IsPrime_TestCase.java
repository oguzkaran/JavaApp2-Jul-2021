package org.csystem.util;

import org.csystem.util.number.NumberUtil;
import org.csystem.util.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;


import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class NumberUtil_IsPrime_TestCase {
    private final Pair<Long, Boolean> m_dataInfo;

    @Parameterized.Parameters
    public static Collection<Pair<Long, Boolean>> createData() throws IOException
    {
        return Files.newBufferedReader(Path.of("isPrimeTest_primes.txt"))
                .lines()
                .map(line -> line.split("[ \t]+"))
                .map(di -> Pair.create(Long.parseLong(di[0]), Boolean.parseBoolean(di[1])))
                .collect(Collectors.toList());
    }

    public NumberUtil_IsPrime_TestCase(Pair<Long, Boolean> dataInfo)
    {
        m_dataInfo = dataInfo;
    }

    @Test
    public void test_isPrime()
    {
        assertEquals(m_dataInfo.getSecond(), NumberUtil.isPrime(m_dataInfo.getFirst()));
    }
}
