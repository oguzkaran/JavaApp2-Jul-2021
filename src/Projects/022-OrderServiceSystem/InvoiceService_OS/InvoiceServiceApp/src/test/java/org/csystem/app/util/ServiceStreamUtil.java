package org.csystem.app.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//Util classes is logical static classes
public final class ServiceStreamUtil {
    //...

    private ServiceStreamUtil() {}

    public static <T> List<T> getIterableToList(Iterable<T> iterable)
    {
        return getIterableToList(iterable, false);
    }

    //Java da core methodlar aşağıya yazılır.
    //Method overloading. Bir method aynı class içinde aynı method name ve farklı method parameter
    //Structure ile yazılırsa bu method overloading tir. access modifierların static olup olmamanın
    //final keyword ile tanımlanıp tanımlanmamanın method overloading üzerinde hiç bir etkisi yoktur.
    //Derleyici overloading yapılan methodları çözümlerken "overloading resolution" kullanır.
    public static <T> List<T> getIterableToList(Iterable<T> iterable, boolean isParallel)
    {
        return StreamSupport.stream(iterable.spliterator(), isParallel).collect(Collectors.toList());
    }
}
