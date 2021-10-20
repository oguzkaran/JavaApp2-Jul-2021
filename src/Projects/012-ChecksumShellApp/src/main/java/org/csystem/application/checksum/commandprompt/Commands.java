package org.csystem.application.checksum.commandprompt;

import org.csystem.util.commandprompt.Command;
import org.csystem.util.commandprompt.ErrorCommand;
import org.csystem.util.console.Console;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.stream.IntStream;

@Component
public class Commands {
    private final Random m_random;

    @Value("${min:0}")
    private int m_min;

    @Value("${max:10}")
    private int m_max;

    @Value("${count:1}")
    private int m_count;

    private static byte geTotalBytes(RandomAccessFile raf)
    {
        byte sum = 0;

        try {
            for (;;)
                sum += raf.readByte();
        }
        catch (EOFException ignore) {

        }
        catch (IOException ex) {
            sum = -1;
            Console.Error.writeLine();
        }

        return sum;
    }

    private void writeInt(RandomAccessFile raf, int val)
    {
        try {
            raf.writeInt(val);
        }
        catch (IOException ex) {
            Console.Error.writeLine();
        }
    }

    private void generateFile(String path)
    {
        try (var raf = new RandomAccessFile(path, "rw")){
            IntStream.generate(() -> m_random.nextInt(m_max - m_min) + m_min)
                    .limit(m_count)
                    .forEach(val -> writeInt(raf, val));

            raf.seek(0);
            raf.writeByte(-geTotalBytes(raf));
        }
        catch (Throwable ex) {
            Console.Error.writeLine(ex);
        }
    }

    private boolean check(String path)
    {
        try (var raf = new RandomAccessFile(path, "r")){
           return geTotalBytes(raf) == 0;
        }
        catch (Throwable ex) {
            Console.Error.writeLine(ex.getMessage());
        }
        return false;
    }

    public Commands(Random random)
    {
        m_random = random;
    }

    @Command("mkcheck")
    public void makeChecksum(String path)
    {
        generateFile(path);
    }

    @Command("check")
    public void checkFile(String path)
    {
        Console.writeLine(check(path) ? "Ok" : "checksum error");
    }

    @Command
    public void quit()
    {
        Console.writeLine("CSD");
        System.exit(1);
    }

    @ErrorCommand
    public void error()
    {
        Console.Error.writeLine("Invalid command");
    }
}
