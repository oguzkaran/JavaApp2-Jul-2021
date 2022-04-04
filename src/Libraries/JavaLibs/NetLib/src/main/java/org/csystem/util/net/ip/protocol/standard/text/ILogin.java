/*----------------------------------------------------------------------
	FILE        : ILogin.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 23.03.2022

	ILogin interface for login operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.net.ip.protocol.standard.text;

import java.io.IOException;

public interface ILogin {
    boolean login() throws IOException;
    boolean logout() throws IOException;
}
