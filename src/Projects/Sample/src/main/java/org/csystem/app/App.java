/*----------------------------------------------------------------------------------------------------------------------
    ExecutorService arayüzünün shutDown metodu "executor service (pool)"'in içerisindeki tüm thread'ler artık boşta
    kaldığında, yani havuz içerisindeki thread'leri kullanan akışlar "alive" durumdan çıktıklarında, yani
    tüm thread akışları sonlandığında "pool"'a ilişkin thread'i de sonlandırır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;
import org.csystem.util.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class App {
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        var myRunnable1 = new MyRunnable(10, "Ali");
        var myRunnable2 = new MyRunnable(5, "Veli");
        var myRunnable3 = new MyRunnable(5, "Selami");

        executorService.execute(myRunnable1);
        executorService.execute(myRunnable2);
        executorService.execute(myRunnable3);
        Console.writeLine("main ends");

        Console.writeLine("main ends");
    }
}

class MyRunnable implements Runnable {
    private final int m_n;
    private final String m_myName;

    public MyRunnable(int n, String myName)
    {
        m_n = n;
        m_myName = myName;
    }

    @Override
    public void run()
    {
        var name = Thread.currentThread().getName();

        Console.writeLine("My name is %s", m_myName);

        for (int i = 0; i < m_n; ++i) {
            Console.writeLine("%s:%d", name, i);
            ThreadUtil.sleep(1000);
        }
    }
}



