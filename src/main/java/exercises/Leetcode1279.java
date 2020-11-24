package exercises;

public class Leetcode1279 {
    public static void main(String[] args) {

    }
}
class TrafficLight {
    private int green;
    private Object lock;
    public TrafficLight() {
        this.green = 1;
        this.lock = new Object();
    }

    public void carArrived(
            int carId,           // ID of the car
            int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
            int direction,       // Direction of the car
            Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
            Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
        synchronized(lock) {
            if (green != roadId) {
                turnGreen.run();
                green = roadId;
            }
            crossCar.run();
            lock.notifyAll();
        }
    }
}