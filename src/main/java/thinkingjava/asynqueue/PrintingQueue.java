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
public class PrintingQueue <T>{
    private int size;

    private Queue<T> queue = new ArrayDeque<T>(size);

    public PrintingQueue(int size) {
        this.size = size;
    }

    public synchronized T remove() {
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

    public synchronized void add(T message) {
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
