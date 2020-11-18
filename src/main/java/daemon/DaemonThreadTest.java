package daemon;

/**
 * 守护线程Demo
 * 当JVM中有任何用户线程（thread.setDaemon(false) 在运行时，JVM不会退出
 */
public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for(;;){}
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("Main thread is over");
    }
}
