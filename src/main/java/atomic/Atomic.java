package atomic;

import java.util.concurrent.atomic.AtomicLong;

public class Atomic {
    private static AtomicLong atomicLong = new AtomicLong();
    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 0, 0, 5, 6};
    private static Integer[] arrayTwo = new Integer[]{0, 0, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                for (int i : arrayOne) {
                    if (i == 0) {
                        atomicLong.getAndIncrement();
                    }
                }
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            public void run() {
                for (int i : arrayTwo) {
                    if (i == 0) {
                        atomicLong.getAndIncrement();
                    }
                }
            }
        });
        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        // expect : 7 zero

        System.out.println("total : " + atomicLong.get());
    }
}
