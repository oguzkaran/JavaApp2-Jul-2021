/*----------------------------------------------------------------------------------------------------------------------
    File sınıfının createTempFile metodu
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.CommandLineArgsUtil;
import org.csystem.util.console.Console;
import org.csystem.util.string.StringUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

class App {
    public static void main(String[] args)
    {
        CommandLineArgsUtil.checkForLengthEqual(args, 1, "Wrong number of arguments", 1);

        File file;
        try (var bw = Files.newBufferedWriter(Path.of((file = File.createTempFile("csd", ".txt", new File("."))).getAbsolutePath()),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            Console.writeLine("Path:%s", file.getAbsolutePath());
            var count = Integer.parseInt(args[0]);
            var random = new Random();

            for (int i = 0; i < count; ++i) {
                bw.write(StringUtil.getRandomTextTR(random, 10) + "\r\n");
                bw.flush();
            }

            Console.writeLine("Length:%d", file.length());

            Console.readLine();
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid count value");
        }
        catch (IOException ex) {
            Console.Error.writeLine(ex.getMessage());
        }
        Console.readLine();
    }
}
