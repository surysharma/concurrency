package queues;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

import static queues.FixedSizeMessageQueue.ofSize;

/**
Simple test client to see message being produced and consumed simultaneously
 */
public class MessageClient {
    public static void main(String... args){
        new MessageClient().execute();
    }

    private CommitMessageQueue commitMessageQueue = ofSize(3);

    private void execute() {
        Thread msgProducer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    sleep();
                    commitMessageQueue.add(new CommitMessage("head"+i, "payload"+i));
                }


            }
        }, "Producer-Thread");

        Thread msgConsumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    sleep();
                    commitMessageQueue.remove();
                }

            }
        }, "Consumer-Thread");

        msgConsumer.start();
//        msgProducer.start();

    }

    private void sleep() {
        try {
            Thread.sleep(RandomUtils.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
