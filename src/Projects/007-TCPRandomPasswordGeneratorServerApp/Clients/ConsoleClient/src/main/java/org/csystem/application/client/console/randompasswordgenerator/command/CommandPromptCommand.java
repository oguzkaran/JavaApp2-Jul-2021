package org.csystem.application.client.console.randompasswordgenerator.command;

import org.csystem.application.client.console.randompasswordgenerator.runner.RandomPasswordClient;
import org.csystem.application.client.console.randompasswordgenerator.runner.RandomPasswordClientJava;
import org.csystem.util.commandprompt.Command;
import org.csystem.util.commandprompt.ErrorCommand;
import org.csystem.util.console.Console;
import org.csystem.util.net.NetworkException;
import org.csystem.util.net.TcpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;

import static org.csystem.util.exception.ExceptionUtil.*;

@Component
public class CommandPromptCommand {
    private final RandomPasswordClientJava m_clientJava;
    private final RandomPasswordClient m_client;

    @Value("${sendFileServer.host}")
    private String m_sendFileHost;

    @Value("${sendFileServer.port}")
    private int m_sendFilePort;

    private void sendFileCallback(String path)
    {
        var file = new File(path);

        if (!file.exists()) {
            Console.Error.writeLine("file not exists");
            return;
        }

        try (var socket = new Socket(m_sendFileHost, m_sendFilePort)) {
            TcpUtil.sendFile(socket, file, 1024);
        }
        catch (Throwable ex) {
            Console.Error.writeLine(ex.getMessage());
        }
    }


    public CommandPromptCommand(RandomPasswordClientJava clientJava, RandomPasswordClient client)
    {
        m_clientJava = clientJava;
        m_client = client;
    }

    @Command("passwdj")
    public void randomPasswordsJavaProc()
    {
        subscribeRunnable(m_clientJava::run, ex -> Console.Error.writeLine("Exception:%d", ex.getMessage()));
    }

    @Command("passwd")
    public void randomPasswordsProc()
    {
        subscribeRunnable(m_client::run, ex -> Console.Error.writeLine("Exception:%d", ex.getMessage()));
    }

    @Command("sendf")
    public void sendFileProc(String path)
    {
        subscribeRunnable(() -> sendFileCallback(path),  ex -> Console.Error.writeLine("Exception:%d", ex.getMessage()));
    }

    @Command
    @Command("exit")
    public void quit()
    {
        System.exit(0);
    }


    @ErrorCommand
    public void errorCommandProc()
    {
        Console.writeLine("Invalid command");
    }
}
