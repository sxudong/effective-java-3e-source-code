package effectivejava.chapter4.item20.skeletal;

/**
 * 链接：https://dzone.com/articles/favour-skeletal-interface-in-java
 * 链接：https://www.sohu.com/a/303411706_355142
 *
 * 简单起见，我没有将每个步骤定义一个单独方法，在 `chooseProduct()` 中合并了这些步骤。
 *
 * 虽然看起来很好，但是上面的代码”有一些问题“。如果我们仔细检查一下，就会发现其中有很多重复代码。
 * `start()`、 `stop()` 和 `process()` 方法在每个实现类中做了相同的事情。
 *
 * 当新增具体实现时，系统的代码会复制三次。
 *
 * 这时我们可以新建工具类，将公共代码放到工具类里。然而这么做会破坏”单一责任原则“，产生 Shotgun surgery 问题代码。
 *
 * 译注：[Shotgun surgery][1] 是软件开发中的一种反模式，它发生在开发人员向应用程序代码库添加特性的地方，这些代码库会在一次更改中跨越多个实现。
 *
 * [1]:https://en.wikipedia.org/wiki/Shotgun_surgery
 *
 * 1.2 接口的缺点
 * 由于接口是一种约定且不包含方法体，因此每个实现都必须按照约定
 * 实现接口中的所有方法。在具体的实现中一些方法可能会重复。
 */
public class VendingManager {
    public static void main(String[] args) {
        Ivending candy = new CandyVending();
        Ivending drink = new DrinkVending();
        candy.process();
        System.out.println("*********************");
        drink.process();
    }
}
/* Output:
Start Vending machine
Produce diiferent candies
Choose a type of candy
pay for candy
collect candy
Stop Vending machine
*********************
Start Vending machine
Produce diiferent soft drinks
Choose a type of soft drinks
pay for drinks
collect drinks
stop Vending machine
 */