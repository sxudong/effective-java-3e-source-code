package effectivejava.chapter2.item3.enumtype;

/**
 * 使用私有构造方法或枚类实现 Singleton 属性
 */
// Enum singleton - the preferred approach (Page 18)
// 枚举单例-首选方法
public enum Elvis { // 猫王
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    // 该代码通常会出现在课程之外！
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
/* Output:
Whoa baby, I'm outta here!
 */