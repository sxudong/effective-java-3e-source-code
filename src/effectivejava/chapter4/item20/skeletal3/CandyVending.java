package effectivejava.chapter4.item20.skeletal3;

// 模拟多重继承
public class CandyVending  implements Ivending {
    // 内部私有类继承骨架实现类（AbstractVending implements Ivending）
    private class AbstractVendingDelegator extends AbstractVending {
        @Override
        public void chooseProduct() {
            System.out.println("Produce diiferent candies");
            System.out.println("Choose a type of candy");
            System.out.println("pay for candy");
            System.out.println("collect candy");
        }
    }
    // 接口方法的调用转发到一个内部私有类的实例上（委托）
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