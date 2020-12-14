package effectivejava.chapter6.item38;

import java.util.*;

/**
 * 第38条：使用接口模拟可扩展的枚举
 */
// Emulated extensible enum 模拟可扩展枚举 (Pages 176-9)
public enum ExtendedOperation implements Operation {

    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    // Using an enum class object to represent a collection of extended enums (page 178)
    // 使用枚举类对象表示扩展枚举的集合
//    public static void main(String[] args) {
//        double x = Double.parseDouble(args[0]);
//        double y = Double.parseDouble(args[1]);
//        test(ExtendedOperation.class, x, y);
//    }
//    private static <T extends Enum<T> & Operation> void test(
//            Class<T> opEnumType, double x, double y) {
//        for (Operation op : opEnumType.getEnumConstants())
//            System.out.printf("%f %s %f = %f%n",
//                    x, op, y, op.apply(x, y));
//    }

    // Using a collection instance to represent a collection of extended enums (page 178)
    // 使用集合实例表示扩展枚举的集合
    public static void main(String[] args) {
//        double x = Double.parseDouble(args[0]);
//        double y = Double.parseDouble(args[1]);
        double x = 100;
        double y = 200;
        // ExtendedOperation.values()返回的是toString()的运算符号
        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    private static void test(Collection<? extends Operation> opSet,
                             double x, double y) {
        for (Operation op : opSet)
            System.out.printf("%f %s %f = %f%n",
                    x, op, y, op.apply(x, y));
    }
}
/* Output:
100.000000 ^ 200.000000 = Infinity
100.000000 % 200.000000 = 100.000000
 */