/*----------------------------------------------------------------------
	FILE        : ISender.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 23.03.2022

	ISender interface

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.net;

import java.io.IOException;

public interface ISender<T> {
    void send(T t) throws IOException;
}
