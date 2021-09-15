/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte m_stop flag değeri volatile yapılmazsa bazı sistemlerde runThread metoduna ilişkin thread
    sonlanmayabilir. Şüphesiz aşağıdaki kod interrupt kullanılarak çok daha kolay ve etkin biçimde yapılabilir. Ayrıca
    şüphesiz aşağıdaki örnekte flag değerinin değişikliği senkronize edilmemiştir. Senkronize edilirse zaten volatile'ın
    kullanımına gerek olmaz. Kod tamamen bu durumu göstermek için yazılmıştır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.nio.charset.StandardCharsets;

class App {
    public static void main(String[] args)
    {
        String str = "oğuz";

        byte [] data = str.getBytes(StandardCharsets.UTF_8);

        Console.writeLine(data.length);
    }
}

