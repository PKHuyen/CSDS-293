package Interpolation.Test;

import Interpolation.InterpolatePolynomial;

import Polynomial.Polynomial;
import Ring.Ring;
import Ring.IntegerRing;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class InterpolatePolynomialTest {
    private Ring<Integer> integerPolynomial = new IntegerRing();
    @Test
    public void interpolateTest2Values() {
        List<Integer> list = new ArrayList<>(List.of(2,4));
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1, -6, 8).toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    public void interpolateTest3Values() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3));
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1, -6, 11, -6).toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    public void interpolateTest10Values() {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,
                4,5,6,7,8,9,10));
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1, -55, 1320, -18150, 157773, -902055, 3416930, -8409500, 12753576, -10628640, 3628800).
                toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    public void interpolateTest0Values() {
        List<Integer> list = new ArrayList<>(List.of(0));
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1, 0).toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    public void interpolateTestEmptyList() {
        List<Integer> list = new ArrayList<>(Collections.emptyList());
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        assertEquals(List.of(1).toString(), interpolatePolynomial.interpolate(list).toString());
    }

    @Test
    public void interpolateTestNull() {
        List<Integer> list = new ArrayList<>();
        InterpolatePolynomial interpolatePolynomial = new InterpolatePolynomial(list, integerPolynomial);
        String message = "Null found in interpolate()";
        assertEquals(message, assertThrows(NullPointerException.class, () ->
                interpolatePolynomial.interpolate(null).toString()).getMessage());
    }
}
