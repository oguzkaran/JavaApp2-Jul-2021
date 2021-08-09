/*----------------------------------------------------------------------------------------------------------------------
    Static bir metot synchronized olarak bildirildiğinde çağrı atomic yapılır. Aşağıdaki örnekte this.increment
    çağrısının (increment synchronized değilse) yaklaşık eşdeğeri şu şekildedir:
        synchronized (IncrementUtility.class) {
            increment();
        }
    Benzer şekilde this.decrement çağrısının (decrement synchronized değilse) yaklaşık eşdeğeri şu şekildedir:
        synchronized (IncrementUtility.class) {
            decrement();
        }
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.ArrayList;

class App {
    public static void main(String[] args)
    {
        IncrementerUtility.run(10, 10_000_000);

        Console.writeLine("val:%d", IncrementerUtility.getValue());
    }
}

class IncrementerUtility {
    private static int m_val;

    private static void createThreads(ArrayList<Thread> threads, int numberOfThreads, Runnable runnable)
    {
        for (int i = 0; i < numberOfThreads; ++i) {
            var t = new Thread(runnable);
            t.start();
            threads.add(t);
        }
    }

    private static void joinThreads(ArrayList<Thread> threads)
    {
        try {
            for (var thread : threads)
                thread.join();
        }
        catch (InterruptedException ignore) {

        }
    }

    private static synchronized void increment()
    {
        ++m_val;
    }

    private static synchronized void decrement()
    {
        --m_val;
    }

    private static void threadCallbackIncrement(long count)
    {
        for (long i = 0; i < count; ++i)
            increment();
    }

    private static void threadCallbackDecrement(long count)
    {
        for (long i = 0; i < count; ++i)
            decrement();
    }

    public static int getValue()
    {
        return m_val;
    }
    public static void run(int numberOfThreads, long count)
    {
        var threads = new ArrayList<Thread>();

        createThreads(threads, numberOfThreads, () -> threadCallbackIncrement(count));
        createThreads(threads, numberOfThreads, () -> threadCallbackDecrement(count));
        joinThreads(threads);
    }
}
