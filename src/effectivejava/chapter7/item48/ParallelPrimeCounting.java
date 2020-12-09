package effectivejava.chapter7.item48;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * 第48条：谨慎使用Stream并行
 */
public class ParallelPrimeCounting {
    // Prime-counting stream pipeline - parallel version (Page 225)
    // 质数计数流管道 - 并行版本（页225）
    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i -> i.isProbablePrime(50))
                .count();
    }

    public static void main(String[] args) {
        System.out.println(pi(10_000_000));
    }
}
/* Output:
664579
 */