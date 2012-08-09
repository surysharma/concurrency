import org.apache.commons.lang.math.RandomUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: sureshsharma
 * Date: 29/07/2012
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class CopyOnWriteArrayListExample {

//    List<String> unsafeList = new ArrayList<String>();
    List<String> safeList = new CopyOnWriteArrayList<String>();

    public void displayContents() {
        for (String item : safeList) {
            block(200);
            System.out.println(Thread.currentThread().getName() + " displaying:" +item);
        }
    }


    public void addContent(String item) {
        System.out.println(Thread.currentThread().getName() + " adding:" +item);
        safeList.add(item);
        block(10);

    }

    private void block(int duration) {
        try {
            Thread.sleep(RandomUtils.nextInt(duration));
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final CopyOnWriteArrayListExample example = new CopyOnWriteArrayListExample();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    example.addContent("item:" + i);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                example.displayContents();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

}
