import java.math.BigInteger;

public class Fibonacci {
    public static BigInteger nthNumFibonacci(long n) {

        BigInteger fibonacci;
        BigInteger num1 = new BigInteger("0");
        BigInteger num2 = new BigInteger("1");

        for(long loop = 0; loop < n; loop++) {
            fibonacci = num1.add(num2);
            num1 = num2;
            num2 = fibonacci;
        }

        return num1;
    }
}