/*----------------------------------------------------------------------
    FILE        : IComparator.java
    AUTHOR      : JavaApp2-Jul-2021 group
    LAST UPDATE : 06.12.2021

    Functional interface that is used for comparison

    Copyleft (c) 1993 by C and System Programmers Association (CSD)
    All Rights Free
-----------------------------------------------------------------------*/

package org.csystem.function;

@FunctionalInterface
public interface IComparator<T> {
    int compare(T left, T right);
}
