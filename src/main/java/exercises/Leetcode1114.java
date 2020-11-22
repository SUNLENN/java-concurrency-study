package exercises;

public class Leetcode1114 {
    static Foo foo = new Foo();

    public static void main(String[] args) throws Exception {


        final Runnable printFirst = new Runnable() {
            public void run() {
                System.out.println("first");
            }
        };
        final Runnable printSecond = new Runnable() {
            public void run() {
                System.out.println("second");
            }
        };
        final Runnable printThird = new Runnable() {
            public void run() {
                System.out.println("third");
            }
        };
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                try {
                    foo.first(printFirst);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                try {
                    foo.second(printSecond);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread threadC = new Thread(new Runnable() {
            public void run() {
                try {
                    foo.third(printThird);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        threadC.start();
        Thread.sleep(2000);
        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();
        threadC.join();

    }

}
class Foo {
    private Object lock;
    private int rnd;
    public Foo() {
        this.lock = new Object();
        this.rnd = 1;
    }

    public void first(Runnable printFirst) throws InterruptedException {

        synchronized(lock) {
            while (rnd != 1) lock.wait();
            printFirst.run();
            rnd++;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        synchronized(lock) {
            while (rnd != 2) lock.wait();
            printSecond.run();
            rnd++;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        synchronized(lock) {
            while (rnd != 3) lock.wait();
            printThird.run();
            rnd++;
            lock.notifyAll();
        }
    }
}
