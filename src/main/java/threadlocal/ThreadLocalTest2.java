package threadlocal;


/**
 * 子线程无法获取父线程的ThreadLocal变量
 */
public class ThreadLocalTest2 {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

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
