package org.csystem.util.thread;

import java.util.concurrent.Semaphore;

public final class ThreadUtil {
    private ThreadUtil()
    {
    }

    public static void sleep(long milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException ignore) {

        }
    }

    public static void notify(Object object)
    {
        object.notify();
    }

    public static void wait(Object object)
    {
        try {
            object.wait();
        }
        catch (InterruptedException ignore) {

        }
    }

    public static void acquire(Semaphore semaphore)
    {
        try {
            semaphore.acquire();
        }
        catch (InterruptedException ignore) {

        }
    }

    public static void release(Semaphore semaphore)
    {
        semaphore.release();
    }

    public static void acquire(Semaphore semaphore, int permits)
    {
        try {
            semaphore.acquire(permits);
        }
        catch (InterruptedException ignore) {

        }
    }

    public static void release(Semaphore semaphore, int permits)
    {
        semaphore.release(permits);
    }
}
