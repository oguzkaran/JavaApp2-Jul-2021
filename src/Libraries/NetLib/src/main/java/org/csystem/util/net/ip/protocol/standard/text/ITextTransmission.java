/*----------------------------------------------------------------------
	FILE        : ITextReceiver.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 23.03.2022

	ITextTransmission interface for login operations

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/

package org.csystem.util.net.ip.protocol.standard.text;

import org.csystem.util.net.IReceiver;
import org.csystem.util.net.ISender;

public interface ITextTransmission extends ISender<String>, IReceiver<String> {

}
