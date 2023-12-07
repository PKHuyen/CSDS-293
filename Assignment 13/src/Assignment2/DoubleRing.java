/**
 * @author Harley Phung
 * Email hkp15
 */
package Assignment2;

import java.util.Objects;

/**
 * Demonstration of how Assignment2.Ring type Double works
 * */
public class DoubleRing implements Ring<Double> {
    /**
     * An override zero() function from Assignment2.Ring that return 0.0 as additive identity
     * @return additive identity
     * */
    @Override
    public Double zero() {
        return 0.0;
    }

    /**
     * An override identity() function from Assignment2.Ring that return constant 1.0 as Multiplicative identity
     * @return multiplicative identity
     * */
    @Override
    public Double identity() {
        return 1.0;
    }

    /**
     * An override sum() function from Assignment2.Ring that return sum of 2 Double x and y
     * @param x First value
     * @param y Second value
     * @return Summation of x and y
     * @throws NullPointerException if either parameter is null
     * */
    @Override
    public Double sum(Double x, Double y) {
        //Error Handling
        Objects.requireNonNull(x, "Null found in input: sum() - Assignment2.DoubleRing");
        Objects.requireNonNull(y, "Null found in input: sum() - Assignment2.DoubleRing");

        //Else case: By default, add 2 input
        return x + y;
    }

    /**
     * An override product() function from Assignment2.Ring that return product of 2 Double x and y
     * @param x First value
     * @param y Second value
     * @return Product of x and y
     * @throws NullPointerException if either parameter is null
     * */
    @Override
    public Double product(Double x, Double y) {
        //Error handling
        Objects.requireNonNull(x, "Null found in input: product() - Assignment2.DoubleRing");
        Objects.requireNonNull(y, "Null found in input: product() - Assignment2.DoubleRing");

        //Else case: By default, multiply 2 input
        return x * y;
    }
}
