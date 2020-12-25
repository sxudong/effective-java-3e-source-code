package effectivejava.chapter2.item6;
import java.util.regex.Pattern;

/**
 * 第6条：避免创建不必要的对象
 */
// Reusing expensive object for improved performance (Pages 18 and 19)
// 重用昂贵的对象以提高性能
public class RomanNumerals {

    // 性能可以大大提高！
    static boolean isRomanNumeralSlow(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    // 重用昂贵的对象以提高性能（让它成为类初始化的一部分，并将它缓存）
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeralFast(String s) {
        return ROMAN.matcher(s).matches();
    }

    public static void main(String[] args) {
        int numSets = 5;
        int numReps = 5;
        boolean b = false;

        for (int i = 0; i < numSets; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < numReps; j++) {
                b ^= isRomanNumeralFast("MCMLXXVI");
            }
            long end = System.nanoTime();
            System.out.println(((end - start) / (1_000. * numReps)) + " μs.");
        }

        // Prevents VM from optimizing away everything.
        if (!b)
            System.out.println();
    }
}
/*
isRomanNumeralSlow()
138.58 μs.
166.22 μs.
92.36 μs.
59.36 μs.
97.66 μs.

isRomanNumeralFast()
55.4 μs.
17.3 μs.
10.5 μs.
22.4 μs.
30.48 μs.
 */
