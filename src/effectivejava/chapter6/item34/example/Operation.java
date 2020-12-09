package effectivejava.chapter6.item34.example;

public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;

    // Do the arithmetic operation represented by this constant
    // 进行此常数表示的算术运算
    public double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        throw new AssertionError("Unknown op: " + this);
    }

    public static void main(String[] args) {
        System.out.println(Operation.PLUS.apply(1,2)); // 3.0
    }
}