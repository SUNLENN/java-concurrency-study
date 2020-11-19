package threadlocal;


/**
 * 子线程可以获取父线程的ThreadLocal变量
 */
public class ThreadLocalTest3 {
    public static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();

    public static void main(String[] args) {
        threadLocal.set("hello world");
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("thread:" + threadLocal.get());
            }
        });
        thread.start();
        System.out.println("main thread:" + threadLocal.get());
    }
}
