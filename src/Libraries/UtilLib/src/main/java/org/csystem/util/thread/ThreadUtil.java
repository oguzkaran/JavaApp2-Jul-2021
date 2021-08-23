package org.csystem.util.thread;

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
}
