package exercises;

import java.util.function.IntConsumer;

public class Leetcode1195 {
    public void main(String[] args) {
    }
}

class FizzBuzz {
    private int n;
    private int cur;
    private Object lock;
    public FizzBuzz(int n) {
        this.n = n;
        this.cur = 1;
        this.lock = new Object();
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 0; i < n/3-n/15; i++) {
            synchronized(lock) {
                while (cur % 3 != 0 || cur % 15 == 0) lock.wait();
                printFizz.run();
                cur += 1;
                lock.notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 0; i < n/5-n/15; i++) {
            synchronized(lock) {
                if (cur > n) return;
                while (cur % 5 != 0 || cur % 15 == 0) lock.wait();
                printBuzz.run();
                cur += 1;
                lock.notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 0; i < n/15; i++) {
            synchronized(lock) {
                if (cur > n) return;
                while (cur % 15 != 0) lock.wait();
                printFizzBuzz.run();
                cur += 1;
                lock.notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n-n/3-n/5+n/15; i++) {
            synchronized(lock) {
                while (cur % 3 == 0 || cur % 5 == 0 || cur % 15 == 0) lock.wait();
                printNumber.accept(cur);
                cur += 1;
                lock.notifyAll();
            }
        }
    }
}