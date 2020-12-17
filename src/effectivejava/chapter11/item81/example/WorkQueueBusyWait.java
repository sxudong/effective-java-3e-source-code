package effectivejava.chapter11.item81.example;

import java.util.LinkedList;
import java.util.List;

public abstract class WorkQueueBusyWait {
    
    private final List queue = new LinkedList();
    
    private boolean stopped = false;
    
    protected WorkQueueBusyWait(){
        new WorkThread().start();
    }
    
    public final void enqueue(Object workItem){
        synchronized(queue){
            queue.add(workItem);
        }
    }
    
    public final void stop(){
        synchronized(queue){
            stopped = true;
        }
    }
    
    protected abstract void processItem(Object workitem) throws InterruptedException;
    
    private class WorkThread extends Thread{
        public void run(){
            final Object QUEUE_IS_EMPTY = new Object();
            while(true){
                Object workItem = QUEUE_IS_EMPTY;
                synchronized(queue){
                    if(stopped)
                        return;
                    if(!queue.isEmpty())
                        workItem  = queue.remove(0);
                }
                if(workItem != QUEUE_IS_EMPTY){
                    try{
                        processItem(workItem);
                    }catch(InterruptedException ie){
                        ie.printStackTrace();
                        return;
                    }
                }
            }
        }
    }
}

class PingPongQueue extends WorkQueue{
    volatile int count=0;
    @Override
    protected void processItem(final Object workItem) throws InterruptedException {
        count++;
        WorkQueue recipient = (WorkQueue)workItem;
        recipient.enqueue(this);
    }
    
}