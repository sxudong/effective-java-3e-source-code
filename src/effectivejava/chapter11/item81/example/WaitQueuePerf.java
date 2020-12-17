package effectivejava.chapter11.item81.example;

public class WaitQueuePerf {

    /**
     * @param args
     */
    public static void main(String[] args) {

        PingPongQueue q1 = new PingPongQueue();
        PingPongQueue q2 = new PingPongQueue();
        q1.enqueue(q2);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        int count = q1.count;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println(q1.count - count);
        q1.stop();
        q2.stop();

    }
}