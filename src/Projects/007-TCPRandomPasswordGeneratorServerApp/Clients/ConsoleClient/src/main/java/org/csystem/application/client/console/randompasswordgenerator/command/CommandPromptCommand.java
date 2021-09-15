package org.csystem.application.client.console.randompasswordgenerator.command;

import org.csystem.application.client.console.randompasswordgenerator.runner.RandomPasswordClient;
import org.csystem.application.client.console.randompasswordgenerator.runner.RandomPasswordClientJava;
import org.csystem.util.commandprompt.Command;
import org.csystem.util.commandprompt.ErrorCommand;
import org.csystem.util.console.Console;
import org.springframework.stereotype.Component;

import static org.csystem.util.exception.ExceptionUtil.*;

@Component
public class CommandPromptCommand {
    private final RandomPasswordClientJava m_clientJava;
    private final RandomPasswordClient m_client;

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

    @Command
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
