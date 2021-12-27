/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.CommandLineArgsUtil;
import org.csystem.util.console.Console;
import org.csystem.util.thread.ThreadUtil;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class App {
    public static void main(String[] args)
    {
        RandomGeneratorApp.run(args);
    }
}

class RandomGeneratorApp {
    public static void run(String [] args)
    {
        CommandLineArgsUtil.checkForLengthEqual(args, 4, "Wrong number of arguments", 1);
        var t = new Thread(new RandomGeneratorAppRunner(args));

        t.start();
        Console.writeLine("RandomGeneratorApp continues!...");
    }
}

class RandomGeneratorAppRunner implements Runnable {
    private int m_count;
    private int m_min;
    private int m_max;
    private long m_second;

    public RandomGeneratorAppRunner(String [] args)
    {
        try {
            m_count = Integer.parseInt(args[0]);
            m_min = Integer.parseInt(args[1]);
            m_max = Integer.parseInt(args[2]);
            m_second = Long.parseLong(args[3]);

            if (m_count <= 0 || m_min >= m_max || m_second < 0)
                throw new IllegalArgumentException("Illegal Arguments");
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid arguments");
        }
    }

    @Override
    public void run()
    {
        Console.writeLine("count: %d, min:%d, max:%d, second:%d", m_count, m_min, m_max, m_second);
        var rg = new RandomGenerator(m_count, m_min, m_max, m_second, TimeUnit.SECONDS, new Random(), true);

        try {
            Console.writeLine("Total:%d", rg.awaitAndGet());
        }
        catch (InterruptedException ignore) {

        }
    }
}

class RandomGenerator implements Runnable {
    private int m_total;
    private final int m_count;
    private final int m_min;
    private final int m_max;
    private final Random m_random;
    private final long m_ms;
    private Thread m_thread;

    public RandomGenerator(int count, int min, int max, long ms, TimeUnit unit, Random random, boolean start)
    {
        m_count = count;
        m_min = min;
        m_max = max;
        m_random = random;
        m_ms = TimeUnit.MILLISECONDS.convert(ms, unit);
        if (start) {
            m_thread = new Thread(this);
            m_thread.start();
        }
    }

    public RandomGenerator(int count, int min, int max, long ms, TimeUnit unit, Random random)
    {
        this(count, min, max, ms, unit, random, false);
    }

    public int getTotal()
    {
        return m_total;
    }

    @Override
    public void run()
    {
        for (var i = 0; i < m_count; ++i) {
            int val = m_random.nextInt(m_max - m_min + 1) + m_min;

            Console.write("%d ", val);
            m_total += val;
            ThreadUtil.sleep(m_ms);
        }
    }

    public void await() throws InterruptedException
    {
        m_thread.join();
    }

    public int awaitAndGet() throws InterruptedException
    {
        await();

        return m_total;
    }
}


