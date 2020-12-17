package effectivejava.chapter11.item81.example;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.List;

public abstract class WorkQueue {
    
    private final List queue = new LinkedList();
    
    private boolean stopped = false;
    
    StringBuffer sb;
    BufferedInputStream bis;
    
    protected WorkQueue(){
        new WorkerThread2().start();
    }
    
    public final void enqueue(Object workItem){
        synchronized(queue){
            queue.add(workItem);
            queue.notify();
        }
    }
    
    public final void stop(){
        synchronized(queue){
            stopped = true;
            queue.notify();
        }
    }
    
    protected abstract void processItem(Object workItem)throws InterruptedException;
    
    //Broken - invokes alien method from synchronized block
    // 损坏-从同步块调用外来方法
    private class WorkerThread extends Thread{
        public void run(){
            while(true){
                synchronized(WorkQueue.this.queue){
                    try{
                        while(queue.isEmpty() && !stopped){
                            queue.wait();
                        }
                    }catch(InterruptedException ie){
                        ie.printStackTrace();
                        return;
                    }
                    
                    if(stopped)
                        return;
                    Object workItem = queue.remove(0);
                    try{
                        processItem(workItem); //lock held 锁住
                    }catch(InterruptedException ie){
                        System.out.println("ddd"+ie);
                        return;
                    }
                }
            }
        }
    }
    
    
    // Alien method outside synchronized block -"open call"
    // 同步块外的外来方法-“打开呼叫”
    private class WorkerThread2 extends Thread{
        public void run(){
            while(true){
                Object workItem = null;
                synchronized(WorkQueue.this.queue){
                    try{
                        while(queue.isEmpty() && !stopped){
                            queue.wait();
                        }
                    }catch(InterruptedException ie){
                        return;
                    }
                    
                    if(stopped)
                        return;
                    workItem = queue.remove(0);    
                }
                
                try{
                    processItem(workItem); //No lock held 没有锁
                }catch(InterruptedException ie){
                    return;
                }
            }
        }
    }
}