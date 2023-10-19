package Ring;

import java.math.BigInteger;
import java.util.Objects;

/** Demonstration of how Ring type BigInteger works */
public class BigIntegerRing<B> implements Ring<BigInteger> {
    /** An override zero() function from Ring that return constant BigInteger Zero as additive identity */
    @Override
    public BigInteger zero() {
        return BigInteger.ZERO;
    }

    /** An override identity() function from Ring that return constant BigInteger One as Multiplicative identity*/
    @Override
    public BigInteger identity() {
        return BigInteger.ONE;
    }

    /** An override sum() function from Ring that return sum of 2 BigInteger x and y
     * Throw NullPointerException if either parameter is null
     * */
    @Override
    public BigInteger sum(BigInteger x, BigInteger y) {
        //Error Handling
        Objects.requireNonNull(x, "Null found in input: sum() - BigIntegerRing");
        Objects.requireNonNull(y, "Null found in input: sum() - BigIntegerRing");

        //Else case: By default, add 2 input
        return x.add(y);
    }

    /** An override product() function from Ring that return product of 2 BigInteger x and y
     * Throw NullPointerException if either parameter is null
     * */
    @Override
    public BigInteger product(BigInteger x, BigInteger y) {
        //Error Handling
        Objects.requireNonNull(x, "Null found in input: product() - BigIntegerRing");
        Objects.requireNonNull(y, "Null found in input: product() - BigIntegerRing");

        //Else case: By default, multiply 2 input
        return x.multiply(y);
    }

    @Override
    public BigInteger inverse(BigInteger x) {
        Objects.requireNonNull(x, "Null found in input: inverse() - BigIntegerRing");
        return x.negate();
    }
}

