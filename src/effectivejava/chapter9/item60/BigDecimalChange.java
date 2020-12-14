package effectivejava.chapter9.item60;

import java.math.BigDecimal;

/**
 * 第60条：若需要精确答案就应避免使用 float 和 double 类型
 */
public class BigDecimalChange {
    // 正确方法是 使用 BigDecimal、int 或 long 进行货币计算
    public static void main(String[] args) {
        final BigDecimal TEN_CENTS = new BigDecimal(".10"); // 0.10

        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemsBought++;
        }
        System.out.println(itemsBought + " items bought."); // 购买了4件物品。
        System.out.println("Money left over: $" + funds);
    }
}
/* Output:
4 items bought.
Money left over: $0.00
 */