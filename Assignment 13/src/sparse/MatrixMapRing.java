/**
 * @author Harley Phung
 * Email hkp15
 */
package sparse;

import Assignment2.Ring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * Demonstration of how Square MatrixMap can create a ring
 * @param <T> Type of the MatrixMapRing
 * */
public class MatrixMapRing<T> implements Ring<MatrixMap<T>> {
    /** Type of ring of MatrixMap*/
    private Ring<T> ring;

    /**
     * Constructor
     * @param ring Type of ring of MatrixMap
     * */
    public MatrixMapRing(Ring<T> ring) {
        this.ring = ring;
    }

    /**
     * Return all zero matrixMap
     * @return additive identity MatrixMap
     * */
    @Override
    public MatrixMap<T> zero() {
        T[][] zeroArray = (T[][]) new Object[1][1];
        return MatrixMap.from(zeroArray);
    }

    /**
     * Return identity Matrix
     * @return multiplicative identity MatrixMap
     * */
    @Override
    public MatrixMap<T> identity() {
        return MatrixMap.identity(1, ring.zero(), ring.identity());
    }

    /**
     * Calculate sum between 2 matrices
     * @param x First MatrixMap
     * @param y Second MatrixMap
     * @return summation of x and y
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public MatrixMap<T> sum(MatrixMap<T> x, MatrixMap<T> y) {
        Objects.requireNonNull(x, "Null found in sum() - MatrixMapRing");
        Objects.requireNonNull(y, "Null found in sum() - MatrixMapRing");
        return x.plus(y, ring::sum);
    }

    /**
     * Calculate product between 2 matrices
     * @param x First MatrixMap
     * @param y Second MatrixMap
     * @return product of x and y
     * @throws NullPointerException if parameters are null
     * */
    @Override
    public MatrixMap product(MatrixMap x, MatrixMap y) {
        Objects.requireNonNull(x, "Null found in product() - MatrixMapRing");
        Objects.requireNonNull(y, "Null found in product() - MatrixMapRing");
        return x.times(y,new MatrixMapRing(ring));
    }
}
