package effectivejava.chapter9.item60;

/**
 * 第60条：若需要精确答案就应避免使用 float 和 double 类型
 */
public class IntChange {
    public static void main(String[] args) {
        int itemsBought = 0;
        int funds = 100;
        for (int price = 10; funds >= price; price += 10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought + " items bought.");
        System.out.println("Cash left over: " + funds + " cents");
    }
}
/* Output:
4 items bought.
Cash left over: 0 cents
 */