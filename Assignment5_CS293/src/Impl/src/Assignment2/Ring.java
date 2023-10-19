package Assignment2;

/** Abstract interface to support type T */
public interface Ring <T> {
    /** Return the additive identity */
    T zero();

    /** Return the multiplicative identity */
    T identity();

    /** Return the sum of param x and param y */
    T sum(T x, T y);

    /** Return the product of param x and param y */
    T product(T x, T y);
}
