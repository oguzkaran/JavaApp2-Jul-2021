package org.csystem.app.randomgeneratorthread;

import org.csystem.util.console.CommandLineArgsUtil;
import org.csystem.util.console.Console;
import org.csystem.util.string.StringUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class RandomPasswordGeneratorPersistenceApp {
    private static void doWorkForFile(Path path, long count, int max)
    {
        var random = new Random();

        try (var bw  = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                StandardOpenOption.APPEND, StandardOpenOption.CREATE)) {

            for (long k = 0; k < count; ++k)
                bw.write(StringUtil.getRandomTextTR(random.nextInt(max) + 1) + "\r\n");

            bw.flush();
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO Problem:%s", ex.getMessage());
        }
    }

    private static void runnableCallback(long count, int max, String filePrefix, int suffix, String ext)
    {
        var self = Thread.currentThread();
        var fullName = String.format("%s_%s.%s", filePrefix, suffix, ext);

        doWorkForFile(Path.of(fullName), count, max);
    }

    private static void doGenerate(String [] args)
    {
        try {
            var count = Long.parseUnsignedLong(args[0]);
            var max = Integer.parseUnsignedInt(args[1]);
            var numberOfFiles = Integer.parseUnsignedInt(args[2]);
            var filePrefix = args[3];
            var ext = args[4];

            for (int i = 0; i < numberOfFiles; ++i) {
                var suffix = i + 1;

                new Thread(() -> runnableCallback(count, max, filePrefix, suffix, ext)).start();
            }
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid arguments");
        }
    }

    private RandomPasswordGeneratorPersistenceApp()
    {
    }

    public static void run(String [] args)
    {
        var errMessage = "usage: java -jar RandomPasswordGeneratorPersistenceApp <count> <max> <number of files> <file prefix> <ext>";

        CommandLineArgsUtil.checkForLengthEqual(args, 5, errMessage, 1);
        doGenerate(args);
    }
}
