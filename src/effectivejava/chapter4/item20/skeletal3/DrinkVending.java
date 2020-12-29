package effectivejava.chapter4.item20.skeletal3;

// 继承了一个类，实现了一个接口
public class DrinkVending extends VendingService  implements Ivending {
    // 内部私有类继承骨架实现类
    private class AbstractVendingDelegator extends AbstractVending {
        @Override
        public void chooseProduct() {
            System.out.println("Produce diiferent soft drinks");
            System.out.println("Choose a type of soft drinks");
            System.out.println("pay for drinks");
            System.out.println("collect drinks");
        }
    }

    AbstractVendingDelegator delegator = new AbstractVendingDelegator();

    @Override
    public void start() {
        delegator.start();
    }

    @Override
    public void chooseProduct() {
        delegator.chooseProduct();
    }

    @Override
    public void stop() {
        delegator.stop();
    }

    @Override
    public void process() {
        delegator.process();
    }
}