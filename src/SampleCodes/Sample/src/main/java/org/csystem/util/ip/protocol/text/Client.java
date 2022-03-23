package org.csystem.util.ip.protocol.text;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public abstract class Client implements ITransmission, Closeable {
    protected Socket socket;

    @Override
    public void close() throws IOException
    {
        socket.close();
    }
}
