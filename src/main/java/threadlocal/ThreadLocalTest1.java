package threadlocal;

/**
 * 简单地说：
 * 每一个Thread实例内部都有一个 ThreadLocal.ThreadLocalMap 类型的成员变量
 * 这个ThreadLocalMap使用我们定义的ThreadLocal对象的引用作为Key，来set/get对应的value
 * 从而做到对每个线程来说都有一份对立的副本，对ThreadLocal中定义的变量进行操作时可以互不影响
 */
public class ThreadLocalTest1 {

    static ThreadLocal<String> localVariable = new ThreadLocal<String>();

    static void print(String str) {
        System.out.println(str + ":" + localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            public void run() {
                localVariable.set("threadOne local variable");
                print("threadOne");
                System.out.println("threadOne remove after:" + localVariable.get());
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            public void run() {
                localVariable.set("threadTwo local variable");
                print("threadTwo");
                System.out.println("threadTwo remove after:" + localVariable.get());
            }
        });
        threadOne.start();
        threadTwo.start();
    }
}
