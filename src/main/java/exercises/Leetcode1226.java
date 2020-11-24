package exercises;

import java.util.concurrent.Semaphore;

public class Leetcode1226 {
    public static void main(String[] args) {

    }
}
class DiningPhilosophers {
    private Semaphore[] forks;
    public DiningPhilosophers() {
        this.forks = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        int lo = Math.max(philosopher, (philosopher-1+5)%5);
        int hi = Math.min(philosopher, (philosopher-1+5)%5);

        forks[lo].acquire();
        forks[hi].acquire();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        forks[hi].release();
        forks[lo].release();
    }

}
/*
资源分级解法
*/