package deadlock;

/**
 * 死锁Demo
 * 线程A持有资源A等待资源B
 * 线程B持有资源B等待资源A
 *
 */

public class DeadLockTest1 {
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get resourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + " get resourceB");
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " get resourceB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resourceA) {
                        System.out.println(Thread.currentThread() + " get resourceA");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
