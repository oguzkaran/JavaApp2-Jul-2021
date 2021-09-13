package org.csystem.application.client.console.randompasswordgenerator.command;

import org.csystem.application.client.console.randompasswordgenerator.runner.RandomPasswordClientJava;
import org.csystem.util.commandprompt.Command;
import org.csystem.util.commandprompt.ErrorCommand;
import org.csystem.util.console.Console;
import org.springframework.stereotype.Component;

import static org.csystem.util.exception.ExceptionUtil.*;

@Component
public class CommandPromptCommand {
    private final RandomPasswordClientJava m_clientJava;

    public CommandPromptCommand(RandomPasswordClientJava clientJava)
    {
        m_clientJava = clientJava;
    }

    @Command("passwdj")
    public void randomPasswordsJavaProc()
    {
        subscribeRunnable(m_clientJava::run, ex -> Console.Error.writeLine("Exception:%d", ex.getMessage()));
    }

    @ErrorCommand
    public void errorCommandProc()
    {
        Console.writeLine("Invalid command");
    }
}
