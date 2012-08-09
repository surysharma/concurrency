public class StaleValueHolder {

    private int i;
    public StaleValueHolder(int i){
        try {
            Thread.sleep(2000);
            Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        this.i = i;
    }

    public void isItPossible(int i) {
        if (this.i != i){
            throw new RuntimeException("Stale value of i ");
        }
    }

    public static void main(String[] args) {
        final StaleValueHolder staleValueHolder = new StaleValueHolder(20);

        new Thread(new Runnable() {
            public void run() {
                System.out.println("Executing " + Thread.currentThread().getName());
                staleValueHolder.isItPossible(20);
            }
        }).start();
        System.out.println("Executing " + Thread.currentThread().getName());
    }

}
