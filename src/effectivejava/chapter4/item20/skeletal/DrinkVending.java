package effectivejava.chapter4.item20.skeletal;

public class DrinkVending implements Ivending {
    @Override
    public void start() {
        System.out.println("Start Vending machine");

    }

    @Override
    public void chooseProduct() {
        System.out.println("Produce diiferent soft drinks");
        System.out.println("Choose a type of soft drinks");
        System.out.println("pay for drinks");
        System.out.println("collect drinks");
    }

    @Override
    public void stop() {
        System.out.println("stop Vending machine");
    }

    @Override
    public void process() {
        start();
        chooseProduct();
        stop();
    }

}