/*----------------------------------------------------------------------------------------------------------------------
    Runnable arayüzünü implemente eden bir sınıf da thread sınıfı gibi kullanabilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;
import org.csystem.util.iterable.generator.RandomIntGenerator;
import org.csystem.util.thread.ThreadUtil;

import java.util.Random;

class App {
    public static void main(String[] args)
    {
        MyApplication.run()
    }
}

class MyApplication {
    //...
    public static void run()
    {

    }
}


class RandomIntGeneratorThread implements Runnable {
    private final int m_count;
    private final int m_min;
    private final int m_max;

    public RandomIntGeneratorThread(int count, int min, int max)
    {
        //...
        m_count = count;
        m_min = min;
        m_max = max;
    }

    @Override
    public void run()
    {
        var r = new Random();
        var self = Thread.currentThread();

        Console.writeLine("Name:%s", self.getName());

        for (int i = 0; i < m_count; ++i) {
            int val = r.nextInt(m_max - m_min + 1) + m_min;

            Console.write("%02d ", val);
            ThreadUtil.sleep(1000);
        }

        Console.writeLine();
    }
}
