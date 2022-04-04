/*----------------------------------------------------------------------
FILE        : IActionCallback.java
AUTHOR      : Oğuz Karan
LAST UPDATE : 13.09.2021

IActionCallback functional interface

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.thread;

@FunctionalInterface
public interface IActionCallback {
    void run() throws Exception;
}
