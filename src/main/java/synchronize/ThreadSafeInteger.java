package synchronize;



public class ThreadSafeInteger {

    private int value;

    public synchronized int get() {
        return this.value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }

}
