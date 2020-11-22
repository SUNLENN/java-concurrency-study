package exercises;

import java.util.function.IntConsumer;

public class Leetcode1116 {
    public static void main(String[] args) {
        // no-op
    }
}
class ZeroEvenOdd {
    private int n;
    private boolean isZero;
    private int cur;
    private Object lock = new Object();
    public ZeroEvenOdd(int n) {
        this.n = n;
        this.isZero = true;
        this.cur = 1;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized(lock) {
                while (!isZero) lock.wait();
                printNumber.accept(0);
                isZero = !isZero;
                lock.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n/2; i++) {
            synchronized(lock) {
                while (isZero || cur % 2 != 0) lock.wait();
                printNumber.accept(cur++);
                isZero = !isZero;
                lock.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n/2 + n%2; i++) {
            synchronized(lock) {
                while (isZero || cur % 2 != 1) lock.wait();
                printNumber.accept(cur++);
                isZero = !isZero;
                lock.notifyAll();
            }
        }
    }
}
