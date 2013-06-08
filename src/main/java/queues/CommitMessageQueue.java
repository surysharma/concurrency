package queues;

/**
    This Queue acts as a Message Queque which producers and consumers can use
 */
public interface CommitMessageQueue {
    public void add(CommitMessage commitMessage);
    public void remove(CommitMessage commitMessage);
    public void peek();
    public void poll();
}
