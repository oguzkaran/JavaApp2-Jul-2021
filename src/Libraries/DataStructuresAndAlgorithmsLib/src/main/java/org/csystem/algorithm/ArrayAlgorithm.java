/*----------------------------------------------------------------------
    FILE        : Algorithm.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 06.12.2021

    Utility class for general algorithms

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.algorithm;

import org.csystem.function.IComparator;

import java.util.Optional;

public final class ArrayAlgorithm {
    private ArrayAlgorithm()
    {}

    public static <T> Optional<T> binarySearch(T [] a, T key, IComparator<? super T> comp)
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
}
