/*----------------------------------------------------------------------
	FILE        : IReceiver.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 23.03.2022

	IReceiver interface

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.net;

import java.io.IOException;
import java.util.List;

public interface IReceiver<T> {
    List<T> receive() throws IOException;
}
