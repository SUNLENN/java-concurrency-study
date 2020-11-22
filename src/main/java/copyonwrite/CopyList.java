package copyonwrite;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 通过快照(snapshot)数组来保证若一致性
 */

public class CopyList {
    private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<String>();

    public static void main(String[] args) {
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");

        Thread threadA = new Thread(new Runnable() {
            public void run() {
                arrayList.set(0, "C");
                arrayList.remove(1);
                arrayList.remove(2);

            }
        });

        Iterator<String> it = arrayList.iterator();
        threadA.start();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
