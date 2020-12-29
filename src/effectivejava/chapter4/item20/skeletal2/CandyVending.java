package effectivejava.chapter4.item20.skeletal2;

public class CandyVending extends AbstractVending {

    @Override
    public void chooseProduct() {
        System.out.println("Produce diiferent candies");
        System.out.println("Choose a type of candy");
        System.out.println("pay for candy");
        System.out.println("collect candy");
    }
}
