package effectivejava.chapter9.item60;

/**
 * 第60条：若需要精确答案就应避免使用 float 和 double 类型
 */
public class Change {
    // Broken - uses floating point for monetary calculation!
    // 损坏-使用浮点数进行货币计算！
    public static void main(String[] args) {
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = 0.10; funds >= price; price += 0.10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought + " items bought."); // 购买了3件物品。
        System.out.println("Change: $" + funds);
    }
}
/* Output:
3 items bought.
Change: $0.399999 99999 99999
 */