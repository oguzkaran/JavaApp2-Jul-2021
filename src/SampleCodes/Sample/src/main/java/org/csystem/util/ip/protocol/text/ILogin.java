package org.csystem.util.ip.protocol.text;

import java.io.IOException;

public interface ILogin {
    boolean login() throws IOException;
    boolean logout() throws IOException;
}
