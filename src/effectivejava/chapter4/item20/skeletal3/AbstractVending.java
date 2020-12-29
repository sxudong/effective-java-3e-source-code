package effectivejava.chapter4.item20.skeletal3;

// 骨架实现类
public abstract class AbstractVending implements Ivending {
    public void start() {
        System.out.println("Start Vending machine");
    }
    public void stop() {
        System.out.println("Stop Vending machine");
    }
    public void process() {
        start();
        chooseProduct();
        stop();
    }
}