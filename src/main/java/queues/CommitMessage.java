package queues;

/**
    MessageQueue containing header and payload
 */
public class CommitMessage {
    private String header;
    private String payload;

    public CommitMessage(String header, String payload) {
        this.header = header;
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public String getPayload() {
        return payload;
    }
}
