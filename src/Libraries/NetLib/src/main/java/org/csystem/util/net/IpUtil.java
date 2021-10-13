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
}
