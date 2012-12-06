package thinkingjava.asynqueue;

import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;
import static org.apache.commons.lang.math.RandomUtils.nextInt;

/**
 * Created by IntelliJ IDEA.
 * User: sureshsharma
 * Date: 05/12/2012
 * Time: 19:26
*   PrintManager is a printer which takes in multiple print requests and sends the requests to the printer asyncronously
 *   using a very simple printing queue. The requests get spooled in the queue and then a different thread picks up the
 *   message from the queue.
 *  */
public class PrintManager {
    private static final int SIZE = 3;
    private PrintingQueue<String> printingQueue = new PrintingQueue<String>(SIZE);


    public void spoolMessage(String message){
        System.out.println(String.format("spooling message %s", message));
        printingQueue.add(message);

    }

    public void printMessage(){
        String message = printingQueue.remove();
        System.out.println(format(">>>>>Printing message %s >>>>>>>>>>", message));
    }

    public static void main(String... args){
        final PrintManager printManager = new PrintManager();


        final Thread consumer = new Thread(new Runnable(){

            public void run() {
                int count = 0;
                while(count++ < 25){
                    try {
                        sleep(nextInt(1000));
                        yield();
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    printManager.printMessage();
                }
            }
        }, "consumerThread");

        Thread producer = new Thread(new Runnable(){

            public void run() {
                int count = 0;
                while(count < 25){
                    try {
                        sleep(nextInt(1000));
                        yield();
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    printManager.spoolMessage(format("printout%s", ++count));
                }
//                System.out.println("Printing finished, interrupting consumers...");
//                consumer.interrupt();

            }
        }, "producerThread");

        consumer.start();
        producer.start();

    }

}
