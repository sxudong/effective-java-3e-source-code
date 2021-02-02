package effectivejava.chapter7.item48;

import java.math.BigInteger;
import java.util.stream.Stream;

import static java.math.BigInteger.*;

/**
 * 第48条：谨慎使用Stream并行
 */
// Parallel stream-based program to generate the first 20 Mersenne primes - HANGS!!! (Page 222)
// 基于并行流的程序，以生成前20个梅森素数-HAN !!!！
public class ParallelMersennePrimes {
    public static void main(String[] args) {
        primes().map(p -> TWO.pow(p.intValueExact()).subtract(ONE))
                .parallel() // 并行
                .filter(mersenne -> mersenne.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
                //.forEachOrdered(System.out::println);
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(TWO, BigInteger::nextProbablePrime);
    }
}
/* 无输出，电脑卡死 */