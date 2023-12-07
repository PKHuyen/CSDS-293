/**
 * @author Harley Phung
 * Email hkp15
 */
package Assignment2;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Demonstration of how Assignment2.Ring type BigInteger works
 * @param <B>  Type of the BigIntegerRing
 * */
public class BigIntegerRing<B> implements Ring<BigInteger> {
    /**
     * An override zero() function from Assignment2.Ring that return constant BigInteger Zero as additive identity
     * @return additive identity
     * */
    @Override
    public BigInteger zero() {
        return BigInteger.ZERO;
    }

    /** An override identity() function from Assignment2.Ring that return constant BigInteger One as Multiplicative identity
     * @return multiplicative identity
     * */
    @Override
    public BigInteger identity() {
        return BigInteger.ONE;
    }

    /** An override sum() function from Assignment2.Ring that return sum of 2 BigInteger x and y
     * @param x First Value
     * @param y Second Value
     * @return Summation of x and y
     * @throws NullPointerException if either parameter is null
     * */
    @Override
    public BigInteger sum(BigInteger x, BigInteger y) {
        //Error Handling
        Objects.requireNonNull(x, "Null found in input: sum() - Assignment2.BigIntegerRing");
        Objects.requireNonNull(y, "Null found in input: sum() - Assignment2.BigIntegerRing");

        //Else case: By default, add 2 input
        return x.add(y);
    }

    /** An override product() function from Assignment2.Ring that return product of 2 BigInteger x and y
     * @param x First Value
     * @param y Second Value
     * @return Product of x and y
     * @throws NullPointerException if either parameter is null
     * */
    @Override
    public BigInteger product(BigInteger x, BigInteger y) {
        //Error Handling
        Objects.requireNonNull(x, "Null found in input: product() - Assignment2.BigIntegerRing");
        Objects.requireNonNull(y, "Null found in input: product() - Assignment2.BigIntegerRing");

        //Else case: By default, multiply 2 input
        return x.multiply(y);
    }
}
