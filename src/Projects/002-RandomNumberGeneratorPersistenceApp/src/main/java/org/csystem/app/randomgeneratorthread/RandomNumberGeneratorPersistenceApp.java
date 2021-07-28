package org.csystem.app.randomgeneratorthread;

import org.csystem.util.console.CommandLineArgsUtil;
import org.csystem.util.console.Console;
import org.csystem.util.iterable.generator.RandomIntGenerator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class RandomNumberGeneratorPersistenceApp {
    private static void doWorkForFile(Path path, long count, int min, int max)
    {
        var random = new Random();

        try (var bw  = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {

            for (long k = 0; k < count; ++k)
                bw.write((random.nextInt(max - min) + min) + "\r\n");

            bw.flush();
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO Problem:%s", ex.getMessage());
        }
    }

    private static void runnableCallback(long count, int min, int max, String filePrefix, int suffix, String ext)
    {
        var self = Thread.currentThread();
        var fullName = String.format("%s_%s_%s.%s", filePrefix, self.getName(), suffix, ext);

        doWorkForFile(Path.of(fullName), count, min, max);
    }

    private static void doGenerate(String [] args)
    {
        try {
            var count = Long.parseLong(args[0]);
            var min = Integer.parseInt(args[1]);
            var max = Integer.parseInt(args[2]);
            var numberOfFiles = Integer.parseInt(args[3]);
            var filePrefix = args[4];
            var ext = args[5];

            for (int i = 0; i < numberOfFiles; ++i) {
                var suffix = i + 1;
                new Thread(() -> runnableCallback(count, min, max, filePrefix, suffix, ext)).start();
            }
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid arguments");
        }
    }

    private RandomNumberGeneratorPersistenceApp()
    {
    }

    public static void run(String [] args)
    {
        var errMessage = "usage: java -jar RandomGeneratorPersistenceApp <count> <min> <max> <number of files> <file prefix> <ext>";

        CommandLineArgsUtil.checkForLengthEqual(args, 6, errMessage, 1);
        doGenerate(args);
    }
}
