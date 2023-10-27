package Ring;

import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

/** Utilities class that support Assignment2.Ring, implement
 * reduce(): To use BinaryOperator() on List of elements
 * sum(): To apply reduce() on to add values of elements
 * product(): To apply reduce() on to multiply values of elements
 * */
public final class Rings<T> {
    /** reduce() to aggregate elements in collection by combining them using accumulator */
    public static <T> T reduce(List<T> args, T zero, BinaryOperator<T> accumulator) {
        //Error handling
        Objects.requireNonNull(args, "Null found in input: reduce() - Assignment2.Rings");
        Objects.requireNonNull(zero, "Null found in input: reduce() - Assignment2.Rings");
        Objects.requireNonNull(accumulator, "Null found in input: reduce() - Assignment2.Rings");

        //If no errors in input
        return args.stream().reduce(zero, accumulator);
    }

    /** Perform addition on List of elements */
    public static final <T> T sum(List<T> args, Ring<T> ring) {
        //Error Handling
        if (args == null || ring == null)
            throw new NullPointerException("Null found in input: sum() - Assignment2.Rings");

        if (args.size() == 0) return ring.zero();
        /** Else cases: No need to create else clause since they are by default, applied reduce() on */
        return reduce(args, ring.zero(), ring::sum);
    }

    /** Perform multiplication on List of elements */
    public static final <T> T product(List<T> args, Ring<T> ring) {
        //Error Handling
        if(args == null || ring == null)
            throw new NullPointerException("Null found in input: product() - Assignment2.Rings");

        if (args.size() == 0) return ring.identity();
        /** Else cases: No need to create else clause since they are by default, applied reduce() on */
        return reduce(args, ring.identity(), ring::product);
    }
}
