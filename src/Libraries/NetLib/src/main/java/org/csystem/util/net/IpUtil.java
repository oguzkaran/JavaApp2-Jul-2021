/*----------------------------------------------------------------------
FILE        : IpUtil.java
AUTHOR      : Oğuz Karan
LAST UPDATE : 13.10.2021

Utility class for IP family

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;
import java.util.OptionalInt;

public final class IpUtil {
    private IpUtil() {}

    public static boolean isAvailableV4(int port)
    {
        var result = false;

        try (var socket = new ServerSocket(port)) {
            result = true;
        }
        catch (IOException ignore) {

        }

        return result;
    }

    public static OptionalInt getFirstAvailablePort(int...ports)
    {
        var result = OptionalInt.empty();

        for (var port : ports)
            try (var serverSocket = new ServerSocket(port)) {
                result = OptionalInt.of(port);
            }
            catch (IOException ignore) {
            }

        return result;
    }

    public static Optional<ServerSocket> getFirstAvailableSocket(int backlog, int...ports)
    {
        Optional<ServerSocket> result = Optional.empty();

        for (var port : ports)
            try {
                result = Optional.of(new ServerSocket(backlog, port));
            }
            catch (IOException ignore) {
            }

        return result;
    }
}
