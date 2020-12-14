package effectivejava.chapter6.item34;

/**
 * 第34条： 使用枚举类型替代整型常量
 */
// Switch on an enum to simulate a missing method (Page 167)
// 开启枚举以模拟缺少的方法
public class Inverse {
    public static Operation inverse(Operation op) {
        switch(op) {
            case PLUS:   return Operation.MINUS;
            case MINUS:  return Operation.PLUS;
            case TIMES:  return Operation.DIVIDE;
            case DIVIDE: return Operation.TIMES;

            default:  throw new AssertionError("Unknown op: " + op);
        }
    }

    public static void main(String[] args) {
//        double x = Double.parseDouble(args[0]);
//        double y = Double.parseDouble(args[1]);
        double x = Double.parseDouble("1");
        double y = Double.parseDouble("3");
        for (Operation op : Operation.values()) {
            Operation invOp = inverse(op);
            System.out.printf("%f %s %f %s %f = %f%n",
                    x, op, y, invOp, y, invOp.apply(op.apply(x, y), y));
        }
    }
}
/* Output:
1.000000 + 3.000000 - 3.000000 = 1.000000
1.000000 - 3.000000 + 3.000000 = 1.000000
1.000000 * 3.000000 / 3.000000 = 1.000000
1.000000 / 3.000000 * 3.000000 = 1.000000
 */