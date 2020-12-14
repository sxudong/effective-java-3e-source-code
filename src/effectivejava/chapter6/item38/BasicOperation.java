package effectivejava.chapter6.item38;

/**
 * 第38条：使用接口模拟可扩展的枚举
 */
// Emulated extensible enum using an interface - Basic implementation (Page 176)
// 使用接口模拟可扩展枚举-基本实现
public enum BasicOperation implements Operation {
    PLUS("+") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; }
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x / y; }
    };

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override public String toString() {
        return symbol;
    }
}
