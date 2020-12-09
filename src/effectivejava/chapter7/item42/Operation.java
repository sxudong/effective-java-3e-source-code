package effectivejava.chapter7.item42;

import java.util.function.DoubleBinaryOperator;

/**
 * 第42条：Lambda优先于匿名类
 */
// Enum with function object fields & constant-specific behavior (Page 195)
// 具有函数对象字段的枚举 & 特定常数的行为
public enum Operation {
    PLUS  ("+", (x, y) -> x + y),
    MINUS ("-", (x, y) -> x - y),
    TIMES ("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;

    Operation(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    @Override public String toString() { return symbol; }

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }

    // Main method from Item 34 (Page 163)
    public static void main(String[] args) {
//        double x = Double.parseDouble(args[0]);
//        double y = Double.parseDouble(args[1]);
        double x = Double.parseDouble("100");
        double y = Double.parseDouble("100");
        for (Operation op : Operation.values())
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }
}
/* Output:
100.000000 + 100.000000 = 200.000000
100.000000 - 100.000000 = 0.000000
100.000000 * 100.000000 = 10000.000000
100.000000 / 100.000000 = 1.000000
 */