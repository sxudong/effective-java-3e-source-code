package effectivejava.chapter2.item5;

//public class Person {
//    //Phone phone = IPhone.getInstance();
//    Phone phone = AndroidPhone.getInstance(); // 换安卓手机必须修改Person类的代码
//
//    public void playGame() {
//        phone.playGame();
//    }
//    public void pay() {
//        phone.pay();
//    }
//    public void call() {
//        phone.call();
//    }
//    public static Person getInstance() {
//        return new Person();
//    }
//}

/**
 * 随着安卓手机越做越好，我换了安卓手机。但是如果我要换安卓手机就必须修改
 * Person 类的内容，这显然不符合开闭原则。
 *
 * 这个时候我们就要考虑使用依赖注入（Dependency Injection，一般简称DI。
 * 我相信很多人已经使用过DI了，只是不知道它叫什么）了，它可以让我们灵活
 * 地变更属性的具体类型。
 */
public class Person {
    Phone phone;
    public void playGame() {
        phone.playGame();
    }
    public void pay() {
        phone.pay();
    }
    public void call() {
        phone.call();
    }
    public static Person getInstance(Phone phone) {
        return new Person(phone);
    }
    // 构造方法注入
    private Person(Phone phone) {
        this.phone = phone;
    }
}