package thinkingjava.asynqueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: sureshsharma
 * Date: 05/12/2012
 * Time: 19:37
*
 *  */
public class PrintingQueue {
    private int size;

    private Queue<String> queue = new ArrayDeque<String>(size);

    public PrintingQueue(int size) {
        this.size = size;
    }

    public synchronized String remove() {
        while (queue.size() == 0){
            try {
                System.out.println(String.format("Queue is empty, can't remove!!!, pl. wait..."));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        return queue.remove();
    }

    public synchronized void add(String message) {
        while (queue.size() == size){
            try {
                System.out.println(String.format("Queue full, !!!, pl. wait..."));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(message);
        notifyAll();
    }
}
