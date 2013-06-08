package queues;

/**
Implementation which deals with only fixed size queues
 */
public class FixedSizeMessageQueue implements CommitMessageQueue{

    private CommitMessage[] commitMessages;
    private int counter = -1;
    private Object mutex = new Object();

    private FixedSizeMessageQueue(int size) {
        commitMessages = new CommitMessage[size];
    }

    public static FixedSizeMessageQueue ofSize(int size){

        return new FixedSizeMessageQueue(size);
    }

    @Override
    public void add(CommitMessage commitMessage) {
        synchronized (mutex) {
            while (counter >= commitMessages.length){
                System.out.println(String.format("Producer: Queque full, message %s waiting...", commitMessage));
                waitToBeNotified();
            }
            System.out.println("commitMessages.length: " +commitMessages.length);
            commitMessages[++counter] = commitMessage;
            System.out.println(String.format("Producer: Adding message %s and notifying...", commitMessage));

            notify();

        }
    }

    @Override
    public CommitMessage remove() {
        synchronized (mutex) {
            while (counter < 0) {
                System.out.println(String.format("CONSUMER: Queque empty, waiting..."));
                waitToBeNotified();
            }

            CommitMessage commitMessage = commitMessages[counter--];
            System.out.println(String.format("CONSUMER: Removing message %s and notifying...", commitMessage));
            notify();
            return commitMessage;
        }
    }

    @Override
    public void peek() {
        try {
            throw new NoSuchMethodException("");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void poll() {
       try{
           throw new NoSuchMethodException("");
       } catch (NoSuchMethodException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
       }
    }


    private void waitToBeNotified() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
