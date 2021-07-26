package org.csystem.app.processstarter;

import org.csystem.util.console.Console;

import java.io.*;

import static org.csystem.util.console.CommandLineArgsUtil.checkForLengthEqual;

public final class ProcessStarterApp {
    private ProcessStarterApp()
    {
    }

    public static void run(String [] args)
    {
        try {
            checkForLengthEqual(args, 2, "Wrong number of arguments", 1);
            var processBuilder = new ProcessBuilder(args[0]);

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            var bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            var bufferedWriter = new BufferedWriter(new FileWriter(args[1], true));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line + "\r\n");
                Console.writeLine(line);
            }

            bufferedWriter.flush();
        }
        catch (IOException ignore) {
            Console.Error.writeLine("Problem occurs while starting process");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("%s", ex.getMessage());
        }
    }
}
