/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte m_val değerinin beklenen değerinde olmadığına dikkat ediniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.ArrayList;

class App {
    public static void main(String[] args)
    {
        var inc = new Incrementer(3, 10_000_000);

        inc.run();

        Console.writeLine("val:%d", inc.getVal());
    }
}

class Incrementer {
    private int m_val;
    private final int m_numberOfThreads;
    private final long m_count;

    private void threadCallback()
    {
        for (long i = 0; i < m_count; ++i)
            ++m_val;
    }

    public Incrementer(int numberOfThreads, long count)
    {
        m_numberOfThreads = numberOfThreads;
        m_count = count;
    }

    public int getVal()
    {
        return m_val;
    }
    public void run()
    {
        var threads = new ArrayList<Thread>();

        for (int i = 0; i < m_numberOfThreads; ++i) {
            var t = new Thread(this::threadCallback);
            t.start();
            threads.add(t);
        }

        try {
            for (var thread : threads)
                thread.join();
        }
        catch (InterruptedException ignore) {

        }
    }
}