/*----------------------------------------------------------------------------------------------------------------------
    RandomIntReducerAsync sınıfı Callable<V> interface parametreli submit metodu ile de yapılabilir. Bu durumda Callable
    arayüzünün call metodunun geri dönüş değerini future ile bekleyen ayrı bir thread (task) yaratılmıştır. Yine ileride
    daha iyileri yazılacaktır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;

class App {
    public static void main(String[] args)
    {
        RandomInReducerApp.run();
    }
}

final class RandomInReducerApp {
    private RandomInReducerApp()
    {
    }

    public static void callback(long result)
    {
        Console.writeLine("%nResult:%d", result);
    }

    public static void run()
    {
        RandomIntReducerAsync.of()
                .setCount(100000)
                .setMax(99)
                .setReduceCallback(Long::sum)
                .setValueCallback(a -> Console.write("%d ", a))
                .subscribe(RandomInReducerApp::callback, () -> Console.writeLine("Problem occurs"));
    }
}

class RandomIntReducerAsync {
    private final ExecutorService m_threadPool;
    private Random m_random;
    private int m_min;
    private int m_max;
    private long m_count;
    private LongBinaryOperator m_longBinaryOperator;
    private IntConsumer m_intConsumer;

    private long operationCallback()
    {
        var result = 0L;

        for (long i = 0; i < m_count; ++i) {
            int val = m_random.nextInt(m_max - m_min + 1) + m_min;

            if (m_intConsumer != null)
                m_intConsumer.accept(val);

            result = m_longBinaryOperator.applyAsLong(result, val);
        }

        return result;
    }

    private void runCallBack(LongConsumer consumer)
    {
        var future = m_threadPool.submit(this::operationCallback);

        try {
            consumer.accept(future.get());
            this.close();
        }
        catch (Throwable ignore) {
            //...
        }
    }

    private void close()
    {
        if (!m_threadPool.isShutdown())
            m_threadPool.shutdown();
    }

    private RandomIntReducerAsync()
    {
        m_random = new Random();
        m_max = 1;
        m_count = 10;
        m_threadPool = Executors.newFixedThreadPool(2);
    }

    public static RandomIntReducerAsync of()
    {
        return new RandomIntReducerAsync();
    }

    public RandomIntReducerAsync setRandom(Random random)
    {
        m_random = random;

        return this;
    }

    public RandomIntReducerAsync setMin(int min)
    {
        m_min = min;

        return this;
    }

    public RandomIntReducerAsync setMax(int max)
    {
        m_max = max;

        return this;
    }

    public RandomIntReducerAsync setCount(long count)
    {
        m_count = count;

        return this;
    }

    public RandomIntReducerAsync setReduceCallback(LongBinaryOperator binaryOperator)
    {
        m_longBinaryOperator = binaryOperator;

        return this;
    }

    public RandomIntReducerAsync setValueCallback(IntConsumer intConsumer)
    {
        m_intConsumer = intConsumer;

        return this;
    }

    public void subscribe(LongConsumer consumer, Runnable failRunnable)
    {
        if (m_longBinaryOperator != null)
            m_threadPool.execute(() -> runCallBack(consumer));
        else
            failRunnable.run();
    }
}
