import Polynomial.Polynomial;
import Ring.Ring;
import Ring.IntegerRing;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class InterpolatePolynomialTest {
    private Ring<Integer> integerPolynomial = new IntegerRing();

    @Test
    /** Test non null ring */
    public void ringTest() {
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(), integerPolynomial);
        assertEquals(integerPolynomial, interpolatePolynomial.ring());
    }

    @Test
    /** Test null ring */
    public void ringTestNull() {
        String message = "Null found";
        assertEquals(message, assertThrows(NullPointerException.class, () -> new InterpolatePolynomial(List.of(), null)).getMessage());
    }

    @Test
    /** Test dataPoints*/
    public void dataPointTest() {
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(), integerPolynomial);
        assertEquals(List.of(), interpolatePolynomial.dataPoints());
    }

    @Test
    /** Test basis */
    public void basisTest() {
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(), integerPolynomial);
        assertEquals(List.of(-1,1).toString(), interpolatePolynomial.basis(1).getList().toString());
    }

    @Test
    /** Test basis */
    public void basisTestNull() {
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(), integerPolynomial);
        String message = "Null found";
        assertEquals(message, assertThrows(NullPointerException.class, () -> new InterpolatePolynomial(null, integerPolynomial)).getMessage());
    }

    @Test
    /** Good Data (Legacy): Datapoints is not empty
     * Test Conditions: CC1, BC1, B1 */
    public void interpolateTest2Values() {
        List<Integer> list = new ArrayList<>(List.of(2,4));
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1, -6, 8).toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    /** Good Data (Legacy): Datapoints is not empty
     * * Test Conditions: CC1, BC1, B1 */
    public void interpolateTest3Values() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3));
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1, -6, 11, -6).toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    /** Good Data (Legacy): Datapoints is not empty
     * Test Conditions: CC1, BC1, B1 */
    public void interpolateTest10Values() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,
                4,5,6,7,8,9,10));
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1, -55, 1320, -18150, 157773, -902055, 3416930, -8409500, 12753576, -10628640, 3628800).
                toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    /** Stress Testing with list of 100 coefficients
     * Test Conditions: CC1, BC1, B1*/
    public void stressTestValidList() {
        List<Integer> list = List.of(
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1);

        List<Integer> result = List.of(
                1, -100, 4950, -161700, 3921225, -75287520, 1192052400, 1172308384, 1404300572, 438703728,
                1591253560, 1036909296, 899523980, -802971808, 1308495696, -1489087776, -2021333062, 639893368, -1757572948,
                1707991160, -474913254, 1195626592, 587258256, 1182969568, 1394391452, 2117601584, 664366936, 1360599728, 1054472812,
                -396466656, -2068172688, 375100064, 399150039, 739315300, 58984778, 870478372, -2048915649, 1222466368, 459839456,
                -1612045760, -762855688, -1502508320, -1672995216, 558587872, -1992592808, 1238902720, 105973792, 974830272, 2144850380,
                1931147152, -938977944, 1931147152, 2144850380, 974830272, 105973792, 1238902720, -1992592808, 558587872, -1672995216,
                -1502508320, -762855688, -1612045760, 459839456, 1222466368, -2048915649, 870478372, 58984778, 739315300, 399150039,
                375100064, -2068172688, -396466656, 1054472812, 1360599728, 664366936, 2117601584, 1394391452, 1182969568, 587258256,
                1195626592, -474913254, 1707991160, -1757572948, 639893368, -2021333062, -1489087776, 1308495696, -802971808, 899523980,
                1036909296, 1591253560, 438703728, 1404300572, 1172308384, 1192052400, -75287520, 3921225, -161700, 4950, -100, 1
        );
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(result.toString(), interpolatePolynomial.interpolate(list).toString());

    }

    @Test
    /** Good Data (Legacy): Datapoints is not empty
     * * Test Conditions: CC1, BC1, B1*/
    public void interpolateTest0Values() {
        List<Integer> list = new ArrayList<>(List.of(0));
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1, 0).toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    /** Legacy - Branch Coverage: Datapoints is empty
     * * Test Conditions: CC1, BC1, B2 */
    public void interpolateTestEmptyList() {
        List<Integer> list = new ArrayList<>(Collections.emptyList());
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1).toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    /** Bad data: Datapoints is null
     * * Test Conditions: BD1 */
    public void interpolateTestNull() {
        List<Integer> list = new ArrayList<>();
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        String message = "Null found in interpolate()";
        assertEquals(message, assertThrows(NullPointerException.class, () ->
                interpolatePolynomial.interpolate(null).toString()).getMessage());
    }

    @Test
    /** Good Data (Legacy): Polynomial lists are not empty, polynomial p1 smaller than p2
     * CC1, CC2 */
    public void interpolateMultiplyTest() {
        Polynomial<Integer> p1 = Polynomial.from(List.of(1, 2));
        Polynomial<Integer> p2 = Polynomial.from(List.of(4, 5, 6));
        Polynomial<Integer> res = Polynomial.from(List.of(4, 13, 16, 12));
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(1, 2), integerPolynomial);
        assertEquals(res.getList().toString(), interpolatePolynomial.multiply(p1, p2).getList().toString());
    }

    @Test
    /** Good Data (Legacy): Polynomial this is empty
     * BD3 */
    public void interpolateMultiplyTestThisEmpty() {
        Polynomial<Integer> p1 = Polynomial.from(List.of(1, 2));
        Polynomial<Integer> p2 = Polynomial.from(Collections.emptyList());
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(1, 2), integerPolynomial);
        assertTrue(interpolatePolynomial.multiply(p2,p1).getList() != null);
    }

    @Test
    /** Good Data (Legacy): Polynomial other is empty
     * BD4 */
    public void interpolateMultiplyTestOtherEmpty() {
        Polynomial<Integer> p1 = Polynomial.from(List.of(1, 2));
        Polynomial<Integer> p2 = Polynomial.from(Collections.emptyList());
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(1, 2), integerPolynomial);
        assertTrue(interpolatePolynomial.multiply(p1,p2).getList() != null);
    }

    @Test
    /** Good Data (Legacy): All polynomials are empty
     * BD3, BD4 */
    public void interpolateMultiplyTestAllEmpty() {
        Polynomial<Integer> p1 = Polynomial.from(List.of(1, 2));
        Polynomial<Integer> p2 = Polynomial.from(Collections.emptyList());
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(1, 2), integerPolynomial);
        assertTrue(interpolatePolynomial.multiply(p2,p2).getList() != null);
    }
    @Test
    /** Bad Data: Polynomial other is null
     * BD1 */
    public void intepolateMultiplyThisNull() {
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(1, 2), integerPolynomial);
        Polynomial<Integer> p1 = Polynomial.from(List.of(1, 2));
        String message = "Null found in multiply - InterpolatePolynomial";
        assertEquals(message, assertThrows(NullPointerException.class, () ->
                interpolatePolynomial.multiply(null,p1).toString()).getMessage());
    }
    @Test
    /** Bad Data: Other is null
     * BD2 */
    public void intepolateMultiplyOtherNull() {
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(1, 2), integerPolynomial);
        Polynomial<Integer> p1 = Polynomial.from(List.of(1, 2));
        String message = "Null found in multiply - InterpolatePolynomial";
        assertEquals(message, assertThrows(NullPointerException.class, () ->
                interpolatePolynomial.multiply(p1, null).toString()).getMessage());
    }

    @Test
    /** Bad Data: All is null
     * BD1, BD2 */
    public void intepolateMultiplyAllNull() {
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(List.of(1, 2), integerPolynomial);
        Polynomial<Integer> p1 = Polynomial.from(List.of(1, 2));
        String message = "Null found in multiply - InterpolatePolynomial";
        assertEquals(message, assertThrows(NullPointerException.class, () ->
                interpolatePolynomial.multiply(null, null).toString()).getMessage());
    }

}