/*----------------------------------------------------------------------------------------------------------------------
    Bir thread aşağıdaki durumlardan biri ile sonlanır:
    - Thread akışına ilişkin metot normal olarak sonlanır
    - Thread akışı içerisinde oluşan bir exception yakalanamaz ise thread sonlanır
    - Thread'in ait olduğu process sonlandığında tüm thread'ler sonlanır. Örneğin process içerisinde herhangi bir
    yerde System.exit çağrıldığında bu durum oluşur
    - Thread daemon bir thread ise ve ait olduğu process içerisinde tüm non-daemon thread'ler sonlanmışsa daemon olan
    thread de sonlanır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;
import org.csystem.util.thread.ThreadUtil;

class App {
    public static void main(String[] args)
    {
        for (int c = 0; c < 10; ++c) {
            var thread = new Thread(() -> {
                var self = Thread.currentThread();
                int i = 0;

                for (;;) {
                    Console.writeLine("%s:%d", self.getName(), i++);
                    ThreadUtil.sleep(1000);
                }
            });

            thread.setDaemon(true);
            thread.start();
        }

        ThreadUtil.sleep(3000);

        new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                ThreadUtil.sleep(1000);
            }

            Console.writeLine("Last non-daemon thread ends");
        }).start();

        Console.writeLine("main ends");
    }
}