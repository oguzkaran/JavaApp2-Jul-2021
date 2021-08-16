/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte bekleyen thread'in beklediği thread'in sonlanmasının durumu anlaşılmaktadır. Yani join'in ne
    durumda sonlandığı test edilmiştir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;
import org.csystem.util.thread.ThreadUtil;

import java.util.Random;

class App {
    public static void main(String[] args) throws InterruptedException
    {
        var random = new Random();
        var thread = new Thread(() -> {
            var name = Thread.currentThread().getName();

            for (int k = 0; k < random.nextInt(10) + 5; ++k) {
                Console.writeLine("%s:%d", name, k);
                ThreadUtil.sleep(1000);
            }
        });

        thread.start();
        thread.join(random.nextInt(10000) + 6000);

        if (thread.isAlive())
            Console.writeLine("Thread hala yaşıyor");
        else
            Console.writeLine("Thread sonlandı");

        Console.writeLine("main ends");
    }
}
