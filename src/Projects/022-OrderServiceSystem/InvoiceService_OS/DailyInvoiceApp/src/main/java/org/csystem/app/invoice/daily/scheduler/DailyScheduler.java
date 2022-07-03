package org.csystem.app.invoice.daily.scheduler;

import org.csystem.util.console.Console;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DailyScheduler {
    //...
    @Scheduled(cron = "00 45 23 * * *")
    public void schedulerCallback()
    {
        Console.writeLine("Daily");
    }
}
