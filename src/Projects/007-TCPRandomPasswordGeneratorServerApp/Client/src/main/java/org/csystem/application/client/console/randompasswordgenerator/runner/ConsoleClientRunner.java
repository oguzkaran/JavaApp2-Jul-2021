package org.csystem.application.client.console.randompasswordgenerator.runner;

import org.csystem.util.console.Console;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

@Component
public class ConsoleClientRunner implements ApplicationRunner {
    @Value("${randomServer.host}")
    private String m_host;

    @Value("${randomServer.port}")
    private int m_port;

    private boolean clientCallback()
    {
        try (var socket = new Socket(m_host, m_port)) {
            var dos = new DataOutputStream(socket.getOutputStream());
            var br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            var count = Console.readLong("Count?");

            if (count <= 0)
                return false;

            var nChars = Console.readInt("Number of Characters?");

            dos.writeLong(count);
            dos.writeInt(nChars);
            dos.flush();

            for (var i = 0L; i < count; ++i) {
                var password = br.readLine();
                Console.writeLine("Length:%d:%s", password.length(), password);
            }
        }
        catch (Throwable ex) {
            Console.Error.writeLine(ex.getMessage());
        }

        return true;
    }

    private void runClient()
    {
        for (;;)
            if (!clientCallback())
                break;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        new Thread(this::runClient).start();
    }
}
