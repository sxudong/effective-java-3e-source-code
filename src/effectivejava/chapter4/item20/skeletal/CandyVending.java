package effectivejava.chapter4.item20.skeletal;

public class CandyVending implements Ivending {
    @Override
    public void start() {
        System.out.println("Start Vending machine");
    }

    @Override
    public void chooseProduct() {
        System.out.println("Produce diiferent candies");
        System.out.println("Choose a type of candy");
        System.out.println("pay for candy");
        System.out.println("collect candy");
    }

    @Override
    public void stop() {
        System.out.println("Stop Vending machine");
    }

    @Override
    public void process() {
        start();
        chooseProduct();
        stop();
    }
}