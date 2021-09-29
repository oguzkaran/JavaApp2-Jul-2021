package org.csystem.application.client.console.general.command;

import org.csystem.application.client.console.general.helper.RandomPasswordHelper;
import org.csystem.application.client.console.general.runner.RandomPasswordClient;
import org.csystem.application.client.console.general.runner.RandomPasswordClientJava;
import org.csystem.application.client.console.general.runner.RandomPasswordClientStayConnected;
import org.csystem.application.client.console.general.runner.RandomPasswordFileClient;
import org.csystem.util.commandprompt.Command;
import org.csystem.util.commandprompt.ErrorCommand;
import org.csystem.util.console.Console;
import org.csystem.util.net.TcpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.Socket;

import static org.csystem.util.exception.ExceptionUtil.subscribeRunnable;

@Component
public class CommandPromptCommand {
    private final RandomPasswordHelper m_passwordHelper;

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
            Console.writeLine(file.getName());
            TcpUtil.sendStringViaLength(socket, file.getName());
            TcpUtil.sendFile(socket, file, 1024);
        }
        catch (Throwable ex) {
            Console.Error.writeLine(ex.getMessage());
        }
    }

    public CommandPromptCommand(RandomPasswordHelper passwordHelper)
    {
        m_passwordHelper = passwordHelper;
    }

    @Command("passwdj")
    public void randomPasswordsJavaProc()
    {
        subscribeRunnable(m_passwordHelper::runClientJava, ex -> Console.Error.writeLine("Exception:%d", ex.getMessage()));
    }

    @Command("passwd")
    public void randomPasswordsProc()
    {
        subscribeRunnable(m_passwordHelper::runClient, ex -> Console.Error.writeLine("Exception:%d", ex.getMessage()));
    }

    @Command("passwdsc")
    public void randomPasswordsStayConnectedProc()
    {
        subscribeRunnable(m_passwordHelper::runClientStayConnected, ex -> Console.Error.writeLine("Exception:%d", ex.getMessage()));
    }

    @Command("passwdf")
    public void randomPasswordsFileProc()
    {
        subscribeRunnable(m_passwordHelper::runClientFile, ex -> Console.Error.writeLine("Exception:%d", ex.getMessage()));
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
