package exercises;

public class Leetcode1115 {
    static FooBar fooBar = new FooBar(10);
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                try {
                    fooBar.foo(getPrintTask("foo"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                try {
                    fooBar.bar(getPrintTask("bar"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("finish");


    }
    private static Runnable getPrintTask(final String content) {
        return new Runnable() {
            public void run() {
                System.out.println(content);
            }
        };
    }
}

class FooBar {
    private int n;
    private boolean isFooTurn;
    private Object lock;
    public FooBar(int n) {
        this.n = n;
        this.isFooTurn = true;
        this.lock = new Object();
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized(lock) {
                while (!isFooTurn) lock.wait();
                printFoo.run();
                lock.notifyAll();
                isFooTurn = !isFooTurn;
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized(lock) {
                while (isFooTurn) lock.wait();
                printBar.run();
                lock.notifyAll();
                isFooTurn = !isFooTurn;
            }
        }
    }
}