/*----------------------------------------------------------------------------------------------------------------------
    Thread sınıfının isAlive metodu bir thread akışının devam edip etmediği bilgisini döndürür
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;
import org.csystem.util.thread.ThreadUtil;

import java.util.Random;

class App {
    public static void main(String[] args) throws InterruptedException
    {
        var random = new Random();
        var n = random.nextInt(10) + 5;

        var thread = new Thread(() -> {
            var name = Thread.currentThread().getName();
            
            Console.writeLine("n = %d", n);
            for (int k = 0; k < n; ++k) {
                Console.writeLine("%s:%d", name, k);
                ThreadUtil.sleep(1000);
            }
        });

        var millis = random.nextInt(10000) + 4000;

        Console.writeLine("millis = %d", millis);
        thread.start();

        thread.join(random.nextInt(10000) + 6000);
        Console.writeLine(thread.isAlive() ? "Thread devam ediyor" : "Thread sonlandı");
    }
}



