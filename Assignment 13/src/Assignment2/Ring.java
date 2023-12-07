/**
 * @author Harley Phung
 * Email hkp15
 */
package Assignment2;

/**
 * Abstract interface to support type T
 * @param <T> Type of Ring*/
public interface Ring <T> {
    /**
     * Return the additive identity
     * @return additive identity
     * */
    T zero();

    /**
     * Return the multiplicative identity
     * @return multiplicative identity
     * */
    T identity();

    /**
     * Return the sum of param x and param y
     * @param x First value
     * @param y Second value
     * @return summation of x and y
     * */
    T sum(T x, T y);

    /**
     * Return the product of param x and param y
     * @param x First value
     * @param y Second value
     * @return product of x and y
     * */
    T product(T x, T y);
}
