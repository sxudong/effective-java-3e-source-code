package effectivejava.chapter2.item5;

/**
 * 后来随着安卓手机越做越好，我换了安卓手机。但是如果我要换
 * 安卓手机就必须修改 Person 类的内容，这显然不符合开闭原则。
 */
public class AndroidPhone implements Phone {
    @Override
    public void playGame() {
        System.out.println("用安卓手机玩游戏");
    }

    @Override
    public void pay() {
        System.out.println("用安卓手机支付");
    }

    @Override
    public void call() {
        System.out.println("用安卓手机打电话");
    }

    public static Phone getInstance() {
        return new AndroidPhone();
    }
}