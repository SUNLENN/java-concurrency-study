package exercises;

import java.util.LinkedList;

/**
 * Question : https://leetcode-cn.com/problems/design-bounded-blocking-queue/
 */

public class Leetcode1188 {
    static BoundedBlockingQueue queue = new BoundedBlockingQueue(5);
    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 7; i++) {
                        queue.enqueue(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        producer.join();
        System.out.println("finish");
    }
}
class BoundedBlockingQueue {
    private Object lock;
    private int capacity;
    private LinkedList<Integer> que;
    public BoundedBlockingQueue(int capacity) {
        this.lock = new Object();
        this.capacity = capacity;
        this.que = new LinkedList<>();
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized(lock) {
            while (que.size() == capacity) lock.wait();
            que.addLast(element);
            lock.notifyAll();
        }
    }

    public int dequeue() throws InterruptedException {
        synchronized(lock) {
            while (que.size() == 0) lock.wait();
            int ans = que.pollFirst();
            lock.notifyAll();
            return ans;
        }
    }

    public int size() {
        return que.size();
    }
}