/*----------------------------------------------------------------------
    FILE        : Algorithm.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 08.12.2021

    Utility class for general algorithms

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.algorithm;

import java.util.Comparator;
import java.util.Optional;

public final class ArrayAlgorithm {
    private ArrayAlgorithm()
    {}

    public static <T> int binarySearch(T [] a, int offset, int length, T key, Comparator<? super T> comp)
    {
        throw new UnsupportedOperationException();
    }

    public static <T> Optional<T> binarySearch(T [] a, T key, Comparator<? super T> comp)
    {
        int left = 0;
        int right = a.length - 1;
        int mid;
        Optional<T> result = Optional.empty();

        while (left <= right) {
            mid = (left + right) / 2;

            int compResult = comp.compare(a[mid], key);

            if (compResult == 0) {
                result =  Optional.of(a[mid]);
                break;
            }

            if (compResult < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return result;
    }

    public static <T> Optional<T> exponentialSearch(T [] a, T key, Comparator<? super T> comp)
    {
        int left, right;
        int result;

        right = a.length  - 1;
        while (comp.compare(key, a[right]) < 0)
            right /= 2;

        if (comp.compare(a[right], key) < 0)
            right *= 2;

        left = 1;

        while (comp.compare(a[left], key) <= 0)
            left *= 2;

        left /= 2;

        int index = binarySearch(a, left, right - left + 1, key, comp);

        return index < 0 ? Optional.empty() : Optional.of(a[left + index]);
    }
}
