/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki açıklanana "POP3 (Post Office Protocol 3)"'e ilişkin komutların çalıştırılabildiği genel olarak
    kullanılabilecek bir kütüphane tasarlayınız.

    POP3 Protokolü:
    POP3 protokolü e postaları server'dan almak için kullanılan bir protoköldür. Client'ın server'a gönderdiği ve server'ın
    client'a gönderdiği yanıt mesajları text tabanlıdır. Komutlar 3 ya da 4 karakterden uzunluğundadır ve büyük küçük
    harf duyarlılığı yoktur. Her komutun sonunda CR/LF çifti vardır. Server'ın client'a gönderdiği yanıtlar genellikle
    tek satırdır. Onlar da CR/LF ile biter. Ancak birden fazla satırlı yanıtların her biri CR/LF ile bitmektedir. Ancak
    mesajın sonunun anlaşılabilmesi için yalnız '.' karakteri ve CR/LF'den oluşan bir satır gönderilir. Eğer tesadüfen
    bir satır '.' içeriyorsa bu durumda CRLF '.' CR/LF karakterleri gönderilmektedir. Client POP3 için mailer server
    host bilgisi ile birlikte 110 numaralı TCP portundan bağlantı kurar. Bağlantı başarılı ise artık komut gönderilebilir.

    Client'ın komutları şunlardır:

    1. USER <kullanıcı ismi>\r\n: Burada kullanıcı ismi herhangi bir isim olabilse de genel olarak e-posta adresi kullanılır.
    Server eğer bu işlem başarılı ise "+OK\r\n" yazısını gönderir.

    2. PASS <parola>\r\n: Kullanıcı parolasıdır. Maaalesef buradaki parola "encrypted" değildir. IMAP bu anlamda daha güvenlidir.
    Eğer parola doğru ise "+OK Logged\r\n", yanlış ise "-ERR [AUTH] Authentication Failed\r\n" yazısını gönderir.

    3. LIST\r\n: Server'daki kullanıcının posta kutusunda bulunan bilgileri elde eder. Bu komuta karşın server birden
    fazla satırdan oluşan bir bilgi verir. Son satır olarak da '.' karakterini CR/LF ile birlikte gönderir.

    4. Client herhangi bir postayı "RETR <no>\r\n" ile elde edebilir. Buna karşın server e-postanın içeriğini birden fazla
    satır olarak yollar. E posta düz text ya da genellikle HTML olarak gönderilmiş olabilir. E posta içerisinde MIME Type
    belirtilir. Bu konuda detaylar vardır. Burada ele alınmayacaktır.

    Bu protokolde server'ın olumlu yanıtlarında "+OK ..." olumsuz yanıtlarında ise "-ERR ..." biçiminde satırlar gönderilmektedir
    5. Client işini bitirdikten sonra server'a "QUIT\r\n" komutunu gönderir. Server da soketi kapatır. Bu durumda client'da
    gerekli işlemleri yapar
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

class App {
    public static void main(String[] args)
    {
        try (var socket = new Socket("mail.csystem.org", 110)) {
            Console.writeLine("Connected to server");
            var br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            var user = "test2@csystem.org";
            var passwd = "Csystem-1993";

            bw.write(String.format("USER %s\r\n", user));
            bw.flush();

            Console.writeLine(br.readLine());

            Console.writeLine("------------------------");

            bw.write(String.format("PASS %s\r\n", passwd));
            bw.flush();
            Console.writeLine(br.readLine());

            Console.writeLine("----------------------------");
            bw.write("LIST\r\n");
            bw.flush();
            String result;

            while (!(result = br.readLine()).equals("."))
                Console.writeLine(result);
            Console.writeLine("----------------------------");

            bw.write(String.format("RETR %d\r\n", 1));
            bw.flush();
            while (!(result = br.readLine()).equals("."))
                Console.writeLine(result);
            Console.writeLine("----------------------------");
        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
