package effectivejava.chapter2.item3.field;

/**
 * 使用私有构造方法或枚类实现 Singleton 属性
 */
// Singleton with public final field  (Page 17)
public class Elvis { // 猫王
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() { }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}