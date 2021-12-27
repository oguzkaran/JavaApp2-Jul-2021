package org.csystem.util.thread;

import java.util.concurrent.Semaphore;

public final class ThreadUtil {
    private ThreadUtil()
    {
    }

    public static void join(Thread thread)
    {
        try {
            thread.join();
        }
        catch (InterruptedException ignore) {

        }
    }

    public static void join(Thread thread, long milliseconds)
    {
        try {
            thread.join(milliseconds);
        }
        catch (InterruptedException ignore) {

        }
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

    public static void synchronize(IActionCallback actionCallback, Object object)
    {
        synchronized (object) {
            try {
                actionCallback.run();
            }
            catch (Exception ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }
}
