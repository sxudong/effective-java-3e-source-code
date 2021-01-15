package effectivejava.chapter6.item35;

/**
 * 第35条：使用实例属性替代序数
 */
// Enum with integer data stored in an instance field (Page 168)
// 枚举，将整数数据存储在实例字段中
public enum Ensemble {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians;

    Ensemble(int size) {
        this.numberOfMusicians = size;
    }

    public int numberOfMusicians() {
        return numberOfMusicians;
    }

    public static void main(String[] args) {
        System.out.println(Ensemble.SEXTET.numberOfMusicians()); // 6
    }
}

// bad solution 不好的解决方案
//public enum Ensemble {
//    SOLO, DUET, TRIO, QUARTET, QUINTET,
//    SEXTET, SEPTET, OCTET, NONET, DECTET;
//
//    public int numberOfMusicians() {
//        // 所有的枚举都有一个ordinal方法，它返回每个枚举常量在类型中的数字位置。
//        return ordinal() + 1;
//    }
//}