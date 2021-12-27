/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte rpg1'e ilişkin işlem bittikten sonra rpg2'ye ilişkin işlem yapılır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.concurrent.random.RandomPasswordGenerator;
import org.csystem.util.console.CommandLineArgsUtil;
import org.csystem.util.console.Console;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

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
        var threadPool = Executors.newCachedThreadPool();

        try {
            var rpg1 = new RandomPasswordGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]), Short.parseShort(args[3]), new Random(), threadPool);

            var rpg2 = new RandomPasswordGenerator(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]), Short.parseShort(args[3]), new Random(), threadPool);

            rpg1.run();
            rpg2.run();

            Console.writeLine("Size:%d", rpg1.awaitAndGet().size());
            Console.writeLine("Size:%d", rpg2.awaitAndGet().size());
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
        finally {
            threadPool.shutdown();
        }
    }
}

