package Ring;

import java.math.BigInteger;
import java.util.Objects;

/** Demonstration of how Assignment2.Ring type Integer works */
public class IntegerRing implements Ring<Integer> {
    /** An override zero() function from Assignment2.Ring that return 0 as additive identity */
    @Override
    public Integer zero() {
        return 0;
    }

    /** An override identity() function from Assignment2.Ring that return constant 1 as Multiplicative identity*/
    @Override
    public Integer identity() {
        return 1;
    }

    /** An override sum() function from Assignment2.Ring that return sum of 2 Double x and y
     * Throw NullPointerException if either parameter is null
     * */
    @Override
    public Integer sum(Integer x, Integer y) {
        //Error Handling
        Objects.requireNonNull(x, "Null found in input: sum() - Assignment2.IntegerRing");
        Objects.requireNonNull(y, "Null found in input: sum() - Assignment2.IntegerRing");

        //Else case: By default, add 2 input
        return x + y;
    }

    /** An override product() function from Assignment2.Ring that return product of 2 Double x and y
     * Throw NullPointerException if either parameter is null
     * */
    @Override
    public Integer product(Integer x, Integer y) {
        //Error Handling
        Objects.requireNonNull(x, "Null found in input: product() - Assignment2.IntegerRing");
        Objects.requireNonNull(y, "Null found in input: product() - Assignment2.IntegerRing");

        //Else case: By default, multiply 2 input
        return x * y;
    }

    @Override
    public Integer inverse(Integer x) {
        Objects.requireNonNull(x, "Null found in input: inverse() - BigIntegerRing");
        return x * -1;
    }
}

