package org.csystem.util.ip.protocol.text;

import java.io.IOException;
import java.util.List;

public interface ITransmission {
    void send(String text) throws IOException;
    List<String> receive() throws IOException;
}
