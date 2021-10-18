/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte m_stop flag değeri volatile yapılmazsa bazı sistemlerde runThread metoduna ilişkin thread
    sonlanmayabilir. Şüphesiz aşağıdaki kod interrupt kullanılarak çok daha kolay ve etkin biçimde yapılabilir. Ayrıca
    şüphesiz aşağıdaki örnekte flag değerinin değişikliği senkronize edilmemiştir. Senkronize edilirse zaten volatile'ın
    kullanımına gerek olmaz. Kod tamamen bu durumu göstermek için yazılmıştır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

class App {
    public static void main(String[] args)
    {
        if (args.length != 1) {
            System.out.println("Wrong usage");
            System.exit(-1);
        }

        byte [] data = new byte[4];

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "r")) {
            randomAccessFile.seek(18);
            randomAccessFile.read(data);
            int width = ByteBuffer.wrap(data)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .getInt();
            randomAccessFile.read(data);
            int height = ByteBuffer.wrap(data)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .getInt();

            System.out.printf("%d x %d%n", width, height);
        }
        catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }
    }
}

