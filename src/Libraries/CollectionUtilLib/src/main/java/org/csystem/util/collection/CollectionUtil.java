/*----------------------------------------------------------------------
	FILE        : CollectionUtil.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 07.02.2022

	Utility class for collections

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class CollectionUtil {
    private CollectionUtil()
    {
    }

    public static <T> boolean areAllDistinct(Collection<? extends T> collection)
    {
        return collection.size() == new HashSet<T>(collection).size();
    }

    public static <T, R> List<R> toList(Iterable<T> iterable, Function<? super T, R> func)
    {
        return toList(iterable, func, false);
    }

    public static <T, R> Iterable<R> toIterable(Iterable<T> iterable, Function<? super T, R> func)
    {
        return toIterable(iterable, func, false);
    }

    public static <T, R> List<R> toList(Iterable<T> iterable, Function<? super T, R> func, boolean parallel)
    {
        return StreamSupport.stream(iterable.spliterator(), parallel).map(func).collect(Collectors.toList());
    }

    public static <T, R> Iterable<R> toIterable(Iterable<T> iterable, Function<? super T, R> func, boolean parallel)
    {
        return toList(iterable, func, parallel);
    }
    //...
}
