package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: sureshsharma
 * Date: 08/08/2012
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class FileRendererTask implements Runnable {

    private CountDownLatch startLatch;
    private CountDownLatch endLatch;
    private String filePath;

    public FileRendererTask(CountDownLatch startLatch, CountDownLatch endLatch, String filePath) {
        this.startLatch = startLatch;
        this.endLatch = endLatch;
        this.filePath = filePath;
    }

    public void run() {
        try {
            startLatch.await();
            perform(filePath);
            endLatch.countDown();
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private static void perform(String filePath) {

        File file = new File(filePath);
        try {
            FileReader reader = new FileReader(file);
            int i;
            while ((i = reader.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
