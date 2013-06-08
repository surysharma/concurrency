package queues;

/**
Implementation which deals with only fixed size queues
 */
public class FixedSizeMessageQueue implements CommitMessageQueue{

    private int size;
    private CommitMessage[] commitMessages = new CommitMessage[size];

    private FixedSizeMessageQueue(int size) {
        this.size = size;

    }

    public static FixedSizeMessageQueue ofSize(int size){
        return new FixedSizeMessageQueue(size);
    }

    @Override
    public void add(CommitMessage commitMessage) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void remove(CommitMessage commitMessage) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void peek() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void poll() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
