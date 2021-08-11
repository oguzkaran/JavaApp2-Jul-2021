/*----------------------------------------------------------------------------------------------------------------------
    Collections utility sınıfının synchronizedXXX metotları ilgili collection grubuna ilişkin interface referansı
    alarak senkronize edilmiş aynı collection'ı kullananan bir collection referansına geri döner
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

class App {
    public static void main(String[] args)
    {
        var list = new ArrayList<String>();
        var appender = new Appender(list, 10, 1_000_000);

        appender.run();
        StreamSupport.stream(appender.spliterator(), false).limit(100).forEach(Console::writeLine);
        Console.writeLine("Size:%d", appender.size());
        Console.writeLine("Size:%d", list.size());
    }
}

class Appender implements Iterable<String> {
    private final List<String> m_texts;
    private final int m_numberOfThreads;
    private final long m_count;

    private void threadCallback()
    {
        var name = Thread.currentThread().getName();

        LongStream.range(0, m_count).forEach(i -> m_texts.add(String.format("%s-%d", name, i)));
    }

    private void startThreads(ArrayList<Thread> threads)
    {
        IntStream.range(0, m_numberOfThreads).forEach(i -> {
            var thread = new Thread(this::threadCallback, "Appender-" + (i + 1));

            threads.add(thread);
            thread.start();
        });
    }

    private void joinThreads(ArrayList<Thread> threads)
    {
        try {
            for (var thread : threads)
                thread.join();
        }
        catch (InterruptedException ignore) {

        }
    }

    public Appender(ArrayList<String> texts, int numberOfThreads, long count)
    {
        if (numberOfThreads <= 0 || count <= 0)
            throw new IllegalArgumentException("Invalid Arguments");

        m_numberOfThreads = numberOfThreads;
        m_count = count;
        m_texts = Collections.synchronizedList(texts);
    }

    public int size()
    {
        return m_texts.size();
    }

    public void run()
    {
        var threads = new ArrayList<Thread>();
        this.startThreads(threads);
        this.joinThreads(threads);
    }

    @Override
    public Iterator<String> iterator()
    {
        return m_texts.iterator();
    }
}

