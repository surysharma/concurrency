package thinkingjava;

import org.joda.time.LocalDateTime;

import static java.lang.String.format;

/**
 * Created by IntelliJ IDEA.
 * User: sureshsharma
 * Date: 30/11/2012
 * Time: 16:09
 * This program creates a SecondsTimer which prints the seconds elapsed, also it issues
 * notification when three seconds elapsed and another one when seven seconds elapsed
 */
public class SecondsTimer {

    private volatile boolean elapsedThreeSecondsCondition;
    private volatile boolean elapsedSevenSecondsCondition;
    private volatile long initialSecond;

    private synchronized void setInitialSecond(long initialSecond) {
        this.initialSecond = initialSecond;
    }

    private synchronized void setElapsedThreeSecondsCondition(boolean elapsedFifteenCondition) {
        this.elapsedThreeSecondsCondition = elapsedFifteenCondition;
    }

    public synchronized void setElapsedSevenSecondsCondition(boolean elapsedSevenSecondsCondition) {
        this.elapsedSevenSecondsCondition = elapsedSevenSecondsCondition;
    }

    private synchronized void secondsPrinter() {
        // Get the current second
        int currentSecond = getNextSecond();

        // print the current second
        System.out.println(format("%s seconds passed", currentSecond));

        // if say, 3 seconds elapsed, then notify the other thread to print the message
        if (secondsElapsed(3, currentSecond)) {
            System.out.println("NOTIFICATION 1 sent....");
            setElapsedThreeSecondsCondition(true);
            notifyAll();
        // if say, 7 seconds elapsed, then notify the other thread to print the message
        } else if (secondsElapsed(7, currentSecond)) {
            System.out.println("NOTIFICATION 2 sent....");
            setElapsedSevenSecondsCondition(true);
            notifyAll();
        }
    }

    private synchronized void printElapsedSecondsMessage() throws InterruptedException {
        while (!elapsedThreeSecondsCondition) {
            wait();
        }
        System.out.println(format("NOTIFICATION 1 Message launched..."));
        setElapsedThreeSecondsCondition(false);
    }

    private synchronized void printSevenSecondsMessage() throws InterruptedException {
        while (!elapsedSevenSecondsCondition) {
            wait();
        }
        System.out.println(format("NOTIFICATION 2 Message launched..."));
        setElapsedSevenSecondsCondition(false);
    }

    private int getNextSecond() {
        long nextSecond = getCurrentSecond();
        long currentSecond = getCurrentSecond();
        while (nextSecond - currentSecond < 1) {
            nextSecond = getCurrentSecond();
        }
        return (int) (nextSecond - initialSecond);
    }

    private static long getCurrentSecond() {
        return new LocalDateTime().getMillisOfDay() / 1000;
    }

    private boolean secondsElapsed(int secondsElapsed, int currentSecond) {
        return (currentSecond >= secondsElapsed) && (currentSecond % secondsElapsed) == 0;
    }

    public static void main(String... args) {

        final SecondsTimer timer = new SecondsTimer();

        Thread secondsPrinterWorker = new Thread(new Runnable() {
            public void run() {
                long currentSecond = getCurrentSecond();
                timer.setInitialSecond(currentSecond);
                while (true) {
                    timer.secondsPrinter();

                }
            }
        });
        Thread fifteenSecondsMessagePrinterWorker = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        timer.printElapsedSecondsMessage();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace(); 
                }
            }
        });
        Thread sevenSecondsMessagePrinterWorker = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        timer.printSevenSecondsMessage();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        fifteenSecondsMessagePrinterWorker.start();
        secondsPrinterWorker.start();
        sevenSecondsMessagePrinterWorker.start();
    }

}
