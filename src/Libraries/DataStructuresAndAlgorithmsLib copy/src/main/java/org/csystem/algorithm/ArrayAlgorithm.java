/*----------------------------------------------------------------------
    FILE        : Algorithm.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 08.12.2021

    Utility class for general algorithms

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
    [3, 5
    2, 3
    2, 3
    ankara
-----------------------------------------------------------------------*/
package org.csystem.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public final class ArrayAlgorithm {
    private ArrayAlgorithm()
    {}

    public static <T> int binarySearch(T [] a, int offset, int length, T key, Comparator<? super T> comp)
    {
        int left = offset;
        int right = length - 1;
        int mid;
        int result = -1;

        while (left <= right) {
            mid = (left - offset + right) / 2;

            int compResult = comp.compare(a[mid], key);

            if (compResult == 0) {
                result = mid;
                break;
            }

            if (compResult < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return result;
    }

    public static <T> Optional<T> binarySearch(T [] a, T key, Comparator<? super T> comp)
    {
        var idx = binarySearch(a, 0, a.length, key, comp);

        return idx < 0 ? Optional.empty() : Optional.of(a[idx]);
    }

    public static <T> Optional<T> exponentialSearch(T [] a, T key, Comparator<? super T> comp)
    {
        int i, left;

        if (comp.compare(key, a[0]) < 0)
            return Optional.empty();

        i = 1;
        while (i < a.length && comp.compare(a[i], key) < 0)
            i *= 2;

        left = i / 2;

        //var idx = Arrays.binarySearch(a, left, i, key, comp); //binarySearch(a, left, i - left + 1, key, comp);

        var idx =  binarySearch(a, left, i - left, key, comp);
        return idx < 0 ? Optional.empty() : Optional.of(a[idx]);
    }
}
