/**
 * @author Harley Phung
 * Email hkp15
 */
package Assignment2;

import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Utilities class that support Assignment2.Ring, implement
 * reduce(): To use BinaryOperator() on List of elements
 * sum(): To apply reduce() on to add values of elements
 * product(): To apply reduce() on to multiply values of elements
 * @param <T> Type of the Ring
 * */
public final class Rings<T> {
    /**
     * reduce() to aggregate elements in collection by combining them using accumulator
     * @param args List of arguments
     * @param zero Zero identity of current type of Rings
     * @param accumulator Accumulator of the rings to support function
     * @param <T> current type of the Rings
     * @return The result of reduce function using accumulator
     * @throws NullPointerException if parameters are null
     * */
    public static <T> T reduce(List<T> args, T zero, BinaryOperator<T> accumulator) {
        //Error handling
        Objects.requireNonNull(args, "Null found in input: reduce() - Assignment2.Rings");
        Objects.requireNonNull(zero, "Null found in input: reduce() - Assignment2.Rings");
        Objects.requireNonNull(accumulator, "Null found in input: reduce() - Assignment2.Rings");

        //If no errors in input
        return args.stream().reduce(zero, accumulator);
    }

    /**
     * Perform addition on List of elements
     * @param args List of arguments
     * @param ring Type of Ring (IntegerRing, DoubleRing, BigIntegerRing)
     * @param <T> Type of the Rings
     * @return summation of values from args using reduce() function
     * @throws NullPointerException if parameters are null
     * */
    public static final <T> T sum(List<T> args, Ring<T> ring) {
        //Error Handling
        if (args == null || ring == null)
            throw new NullPointerException("Null found in input: sum() - Assignment2.Rings");

        if (args.size() == 0) return ring.zero();
        /** Else cases: No need to create else clause since they are by default, applied reduce() on */
        return reduce(args, ring.zero(), ring::sum);
    }

    /**
     * Perform multiplication on List of elements
     * @param args List of arguments
     * @param ring Type of Ring (IntegerRing, DoubleRing, BigIntegerRing)
     * @param <T> Type of rings
     * @return product of arguments using reduce()
     * @throws NullPointerException*/
    public static final <T> T product(List<T> args, Ring<T> ring) {
        //Error Handling
        if(args == null || ring == null)
            throw new NullPointerException("Null found in input: product() - Assignment2.Rings");

        if (args.size() == 0) return ring.identity();
        /** Else cases: No need to create else clause since they are by default, applied reduce() on */
        return reduce(args, ring.identity(), ring::product);
    }
}
