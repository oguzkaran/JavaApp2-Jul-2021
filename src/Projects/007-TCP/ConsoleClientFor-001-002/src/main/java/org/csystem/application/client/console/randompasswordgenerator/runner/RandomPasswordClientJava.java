package org.csystem.application.client.console.randompasswordgenerator.runner;

import org.csystem.util.console.Console;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

@Component
public class RandomPasswordClientJava {
    @Value("${randomServer.host}")
    private String m_host;

    @Value("${randomServerJava.port}")
    private int m_port;

    private boolean clientCallback()
    {
        try (var socket = new Socket(m_host, m_port)) {
            var dos = new DataOutputStream(socket.getOutputStream());
            var dis = new DataInputStream(socket.getInputStream());
            var br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            var count = Console.readInt("Count?");

            if (count <= 0)
                return false;

            var length = Console.readInt("Number of Characters?");

            dos.writeInt(count);
            dos.writeInt(length);
            dos.flush();

            if (dis.readBoolean())
                for (var i = 0L; i < count; ++i) {
                    var password = br.readLine();
                    Console.writeLine("%s", password);
                }
            else
                Console.writeLine("Invalid parameters for server!...");
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

    public void run() throws Exception
    {
        this.runClient();
    }
}
