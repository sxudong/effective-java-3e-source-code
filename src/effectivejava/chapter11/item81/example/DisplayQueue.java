package effectivejava.chapter11.item81.example;

public class DisplayQueue extends WorkQueue {

    @Override
    protected void processItem(Object workItem) throws InterruptedException {
        System.out.println(workItem);
        System.out.println("模拟此线程做耗时工作");
        Thread.sleep(1000);
    }
    
    public static void main(String[] args){
        WorkQueue wq = new DisplayQueue();
        for(int i=0;i<10;i++){
            String s = new String("object_"+i);
            System.out.println("main thread add " + s+" to queue");
            wq.enqueue(s);
            try{
                Thread.sleep(500);
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        //wq.stop();
    }

}



class DeadLockQueue extends WorkQueue{

    @Override
    protected void processItem(final Object workItem) throws InterruptedException {
        
        Thread child = new Thread(){
            public void run(){
                //DeadLockQueue.this.enqueue(workItem);
                System.out.println("在将对象入队列 "+workItem);
                enqueue(workItem);
            }
        };
        child.start();
        child.join(); //dead lock 死锁
        
    }
    
    
}