package effectivejava.chapter4.item20.skeletal2;

/**
 * 链接：https://dzone.com/articles/favour-skeletal-interface-in-java
 * 链接：https://www.sohu.com/a/303411706_355142
 *
 * 2. 方法二 ———— 通过抽象类弥补接口的不足
 * 这里我为抽象类提供了通用的代码，`CandyVending` 和 `DrinkVending` 都继承了 `AbstractVending`。
 * 这么做虽然消除了重复代码，但引入了一个新问题。
 *
 * `CandyVending` 和 `DrinkVending` 继承了 `AbstractVending`，由于 Java 不支持多重集成因此不能继承其他类。
 *
 * 假如要添加一个 `VendingServicing` 类，负责清洁和检查自动售货机。在这种情况下，由于已经继承了 `AbstractVending`，
 * 因此不能继承 `VendingServicing`。这里可以新建组合（composition），但是必须把 `VendingMachine` 传入该组合，
 * 这会让 `VendingServicing` 和 `VendingMachine` 产生强耦合。
 *
 * 2.2 抽象类的缺点
 * 由于菱形继承问题，Java 不支持多重继承。假如我们能够同时利用接口和抽象类的优点就太好了。
 *
 * 还是有办法的。
 *
 * 译注：菱形继承问题。两个子类继承同一个父类，而又有子类又分别继承这两个子类，产生二义性问题。
 */
public class VendingManager {
    public static void main(String[] args) {
        AbstractVending candy =  new CandyVending();
        AbstractVending drink =  new DrinkVending();
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
Stop Vending machine
 */