/*----------------------------------------------------------------------
	FILE        : Client.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 23.03.2022

	abstract super Client class for text based standard ip protocols

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.net.ip.protocol.standard.text;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public abstract class Client implements Closeable {
    protected Socket socket;

    @Override
    public void close() throws IOException
    {
        socket.close();
    }
}
