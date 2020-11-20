package synchronize;

/**
 * 内置锁是可重入的
 */

public class SynchronizedTest {
    public static void main(String[] args) {
        new Hello().helloB();
    }
}
class Hello {
    public synchronized void helloA() {
        System.out.println("helloA");
    }
    public synchronized void helloB() {
        System.out.println("helloB");
        helloA();
    }
}
