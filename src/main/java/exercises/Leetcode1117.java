package exercises;

/**
 * Question : https://leetcode-cn.com/problems/building-h2o/
 */
public class Leetcode1117 {
    private static H2O h2O = new H2O();
    public static void main(String[] args) throws InterruptedException {
        Runnable releaseHydrogen = new Runnable() {
            @Override
            public void run() {
                System.out.print("H");
            }
        };
        Runnable releaseOxygen = new Runnable() {
            @Override
            public void run() {
                System.out.print("O");
            }
        };

        int n = 5;

        Thread threadH = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2 * n; i++) {
                    try {
                        h2O.hydrogen(releaseHydrogen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        Thread threadO = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < n; i++) {
                    try {
                        h2O.oxygen(releaseOxygen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        threadO.start();
        threadH.start();

        threadH.join();
        threadO.join();
        System.out.println();
        System.out.println("finish");
    }
}
class H2O {
    private int H;
    private Object lock;
    public H2O() {
        this.H = 0;
        this.lock = new Object();
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized(lock) {
            while (H == 2) lock.wait();
            releaseHydrogen.run();
            H++;
            lock.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized(lock) {
            while (H != 2) lock.wait();
            releaseOxygen.run();
            H = 0;
            lock.notifyAll();
        }
    }
}