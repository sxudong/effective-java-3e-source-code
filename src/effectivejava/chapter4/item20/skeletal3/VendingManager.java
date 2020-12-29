package effectivejava.chapter4.item20.skeletal3;

/**
 * 链接：https://dzone.com/articles/favour-skeletal-interface-in-java
 * 链接：https://www.sohu.com/a/303411706_355142
 * 3. 抽象接口或骨架实现
 *
 * 要完成骨架实现：
 * (1)创建接口。
 * (2)创建抽象类来实现该接口，并实现公共方法。
 * (3)在子类中创建一个私有内部类，继承抽象类。现在把外部调用委托给抽象类，
 *    该类可以在使用通用方法同时继承和实现任何接口。
 *
 * 设计中，首先创建了一个接口，然后创建了一个抽象类，在这个类中定义了所有
 * 通用的实现。然后，为每个子类实现一个 delegator 类。通过 delegator 将
 * 调用转给 `AbstractVending`。
 *
 * 3.2 骨架实现的好处
 * (1)子类可继承其他类，比如 `DrinkVending`。
 * (2)通过将调用委托给抽象类消除重复代码。
 * (3)子类可根据需要实现其他的接口。
 *
 * 总结：当接口有公用方法时可以创建抽象类，使用子类作为委派器，建议使用骨架实现。
 */
public class VendingManager {
    public static void main(String[] args) {
        Ivending candy = new CandyVending();
        Ivending drink = new DrinkVending();

        candy.process();
        System.out.println("*********************");
        drink.process();

        if(drink instanceof VendingService) {
            VendingService vs = (VendingService)drink;
            vs.service();
        }
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
Clean the vending machine
*/