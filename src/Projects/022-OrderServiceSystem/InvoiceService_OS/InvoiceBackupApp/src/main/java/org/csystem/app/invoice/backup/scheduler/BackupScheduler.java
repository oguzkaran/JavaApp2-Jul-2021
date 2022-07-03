package org.csystem.app.invoice.backup.scheduler;

import org.csystem.util.console.Console;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class BackupScheduler {
    //...

    @Scheduled(cron = "0 59 23 L * *")
    public void schedulerCallback()
    {
        Console.writeLine("Backup");
    }
}
