/*----------------------------------------------------------------------------------------------------------------------
    UUIO sınıfı. Cryptograpy konusunda ayrıca incelenektir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.util.UUID;

class App {
    public static void main(String[] args)
    {
        for (int i = 0; i < 10; ++i)
            Console.writeLine(UUID.randomUUID());
    }
}
