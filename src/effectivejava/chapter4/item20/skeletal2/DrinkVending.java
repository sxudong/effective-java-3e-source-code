package effectivejava.chapter4.item20.skeletal2;

public class DrinkVending extends AbstractVending {
    @Override
    public void chooseProduct() {
        System.out.println("Produce diiferent soft drinks");
        System.out.println("Choose a type of soft drinks");
        System.out.println("pay for drinks");
        System.out.println("collect drinks");
    }
}