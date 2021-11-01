/*----------------------------------------------------------------------------------------------------------------------
    System.nanoTime metodu ile bir işlemin ne kadar zaman aldığı nanosaniye cinsinden hesaplanabilir. Bu metot
    JVM düzeyinde daha yüksek çözünürlüklü bir zamandır. Detaylı zaman için currentTimeMillis yerine kullanılması
    önerilir
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;
import org.csystem.util.number.NumberUtil;

class App {
    public static void main(String[] args)
    {
        var startNano = System.nanoTime();

        var result = NumberUtil.isPrime(6750161072220585911L);

        var endNano = System.nanoTime();
        var elapsed = (endNano - startNano) / 1_000_000_000.;

        Console.writeLine(elapsed);
        Console.writeLine(result);
    }
}

