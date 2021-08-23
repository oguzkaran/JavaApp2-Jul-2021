/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte m_stop flag değeri volatile yapılmazsa bazı sistemlerde runThread metoduna ilişkin thread
    sonlanmayabilir. Şüphesiz aşağıdaki kod interrupt kullanılarak çok daha kolay ve etkin biçimde yapılabilir. Ayrıca
    şüphesiz aşağıdaki örnekte flag değerinin değişikliği senkronize edilmemiştir. Senkronize edilirse zaten volatile'ın
    kullanımına gerek olmaz. Kod tamamen bu durumu göstermek için yazılmıştır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;
import org.csystem.util.thread.ThreadUtil;

import java.util.concurrent.atomic.AtomicBoolean;

class App {
    public static void main(String[] args)
    {
        var stopThreadWithFlag = new StopThreadWithFlag();

        stopThreadWithFlag.run();
    }
}

class StopThreadWithFlag {
    private final AtomicBoolean m_stop = new AtomicBoolean();

    private void runThread()
    {
        while (!m_stop.get())
            Console.writeLine("I am running!...");
    }

    private void stopperThread()
    {
        ThreadUtil.sleep(5000);
        m_stop.set(true);
    }

    public void run()
    {
        new Thread(this::runThread).start();
        new Thread(this::stopperThread).start();
    }
}

