package effectivejava.chapter7.item45;// Java program to demonstrate the example
// of BigInteger pow(int exp) method of BigInteger
 
import java.math.*;

/**
 * BigInteger类pow()方法
 * https://blog.csdn.net/cumubi7552/article/details/107796428
 */
public class PowOfBI {
    public static void main(String args[]) {
        // Initialize two variables str, exp 
        String str = "10";
        int exp = 2;
 
        // Initialize a BigInteger object  
        BigInteger b_int = new BigInteger(str);
 
        // Display b_int , exp
        System.out.println("b_int: " + b_int);
        System.out.println("exp: " + exp);
 
        // Here, we are calculating pow of this
        // BigInteger value and it is calculated 
        // by using[(this BigInteger (b_int) ^ (exp))]
        // like 10^2 = 100
        BigInteger pow_val = b_int.pow(exp);
        // 10 * 10 = 100
        System.out.println("b_int.pow(exp): " + pow_val); //100
    }
}

