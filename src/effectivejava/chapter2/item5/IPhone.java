package effectivejava.chapter2.item5;

public class IPhone implements Phone {
    @Override
    public void playGame() {
        System.out.println("用苹果手机玩游戏");
    }

    @Override
    public void pay() {
        System.out.println("用苹果手机支付");
    }

    @Override
    public void call() {
        System.out.println("用苹果手机打电话");
    }

    public static Phone getInstance() {
        return new IPhone();
    }
}
