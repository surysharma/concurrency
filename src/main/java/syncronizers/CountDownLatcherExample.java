package syncronizers;

import util.FileRendererTask;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: sureshsharma
 * Date: 07/08/2012
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
public class CountDownLatcherExample {
    public static void main(String[] args) {
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(5);
        ///Users/sureshsharma/Movies/English/HD/Sherlock.Holmes.720p.Bluray.x264-CBGB/cbgb-sherlockholmes720.mkv
        FileRendererTask task1 = new FileRendererTask(startLatch, endLatch, "/projects/concurrency/catalina1.log");
        FileRendererTask task2 = new FileRendererTask(startLatch, endLatch, "/projects/concurrency/catalina2.log");
        FileRendererTask task3 = new FileRendererTask(startLatch, endLatch, "/projects/concurrency/catalina3.log");
        FileRendererTask task4 = new FileRendererTask(startLatch, endLatch, "/projects/concurrency/catalina4.log");
        FileRendererTask task5 = new FileRendererTask(startLatch, endLatch, "/projects/concurrency/catalina5.log");

        Callable<Object> callable = Executors.callable(task1);
        


        new Thread(task1).start();
        new Thread(task2).start();
        new Thread(task3).start();
        new Thread(task4).start();
        new Thread(task5).start();

        startLatch.countDown(); // ready, set, and Go!!!
        long startTime = System.currentTimeMillis();
        while (endLatch.getCount() > 0) {

        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time in milliseconds: " +  (endTime - startTime));
    }
}
