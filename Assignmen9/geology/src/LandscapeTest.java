import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests Landscape class.
 * You can assume there are no errors here.
 * All the tests are not provided here; you will have to add them yourself.
 */
public class LandscapeTest {
    private final Logger logger = Logger.getLogger(Landscape.class.getName());
    private final LoggerTestingHandler handler = new LoggerTestingHandler();

    @Before
    public void setup() {
        logger.addHandler(handler);
    }

    /** modify() */
    @Test
    public void testModificationRecord() {
        Landscape.Modification record = new Landscape.Modification(0, 4, Landscape.Operation.RAISE);

        assertEquals(0, record.x1());
        assertEquals(4, record.x2());
        assertEquals(Landscape.Operation.RAISE, record.operation());
    }

    @Test
    public void testModifyRaise() {
        Landscape landscape = new Landscape();
        landscape.modify(0, 4, Landscape.Operation.RAISE);
        List<Integer> heights = List.of(1, 1, 1, 1);
        assertTrue(landscape.verify(List.of(), heights));
    }

    @Test
    public void testModifyDepress() {
        Landscape landscape = new Landscape();
        landscape.modify(0, 4, Landscape.Operation.DEPRESS);
        List<Integer> heights = List.of(-1, -1, -1, -1);
        assertTrue(landscape.verify(List.of(), heights));
    }

    @Test
    public void testModifyHill() {
        Landscape landscape = new Landscape();
        Landscape.Modification modification = new Landscape.Modification(0, 4, Landscape.Operation.HILL);
        List<Integer> heights = List.of(1, 2, 3, 2, 1);
        assertTrue(landscape.verify(List.of(modification), heights));
    }

    @Test
    public void testModifyValley() {
        Landscape landscape = new Landscape();
        Landscape.Modification modification = new Landscape.Modification(0, 4, Landscape.Operation.VALLEY);
        List<Integer> heights = List.of(-1, -2, -3, -2, -1);
        assertTrue(landscape.verify(List.of(modification), heights));
    }

    @Test
    public void testModifyInvalidBoundary() {
        handler.clearLogRecords();

        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();
        landscape.modify(0, -1, Landscape.Operation.RAISE);

        assertEquals("x1 cannot be greater than x2; returning early", handler.getLastLog().orElse(""));
        assertEquals(0, testHook.get().size());
    }

    @Test
    public void testModifyUnsupportOperation() {
        Landscape landscape = new Landscape();
        Landscape.Modification modification = new Landscape.Modification(0, 4, Landscape.Operation.UNSUPPORT);
        Landscape.TestHook testHook = landscape.new TestHook();
        List<Integer> heights = List.of(1, 1, 1, 1, 1);
        assertFalse(landscape.verify(List.of(modification), heights));
        assertNull(testHook.get().get(0));
        assertNull(testHook.get().get(4));
        assertEquals("Unsupported Operation", handler.getLastLog().orElse(""));
    }

    /** get() */
    @Test
    public void testGet() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();

        assertEquals(0, testHook.get().size());
    }

    /** raiseHill() */
    @Test
    public void testRaiseHillEven() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();
        landscape.modify(0,3, Landscape.Operation.RAISE);
        Map<Integer, Integer> mapAfterCallingModify = testHook.get();
        landscape = new Landscape();
        testHook = landscape.new TestHook();
        testHook.raiseHill(0,3);
        Map<Integer, Integer> mapAfterCallingRaiseHill = testHook.get();
        assertEquals(mapAfterCallingRaiseHill, mapAfterCallingModify);
    }

    @Test
    public void testRaiseHillOdd() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();
        landscape.modify(0,2, Landscape.Operation.RAISE);
        Map<Integer, Integer> mapAfterCallingModify = testHook.get();
        landscape = new Landscape();
        testHook = landscape.new TestHook();
        testHook.raiseHill(0,2);
        Map<Integer, Integer> mapAfterCallingRaiseHill = testHook.get();
        assertEquals(mapAfterCallingRaiseHill, mapAfterCallingModify);
    }

    /** depressHill() */
    @Test
    public void testDepressHillEven() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();
        landscape.modify(0,3, Landscape.Operation.DEPRESS);
        Map<Integer, Integer> mapAfterCallingModify = testHook.get();
        landscape = new Landscape();
        testHook = landscape.new TestHook();
        testHook.depressHill(0,3);
        Map<Integer, Integer> mapAfterCallingDepressHill = testHook.get();
        assertEquals(mapAfterCallingDepressHill, mapAfterCallingModify);
    }

    @Test
    public void testDepressHillOdd() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();
        landscape.modify(0,2, Landscape.Operation.DEPRESS);
        Map<Integer, Integer> mapAfterCallingModify = testHook.get();
        landscape = new Landscape();
        testHook = landscape.new TestHook();
        testHook.depressHill(0,2);
        Map<Integer, Integer> mapAfterCallingDepressHill = testHook.get();
        assertEquals(mapAfterCallingDepressHill, mapAfterCallingModify);
    }

    /** createHill() */
    @Test
    public void testCreateHillEven() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();
        landscape.modify(0,3, Landscape.Operation.HILL);
        Map<Integer, Integer> mapAfterCallingModify = testHook.get();
        landscape = new Landscape();
        testHook = landscape.new TestHook();
        testHook.createHill(0,3);
        Map<Integer, Integer> mapAfterCallingCreateHill = testHook.get();
        assertEquals(mapAfterCallingCreateHill, mapAfterCallingModify);
    }

    @Test
    public void testCreateHillOdd() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();
        landscape.modify(0,2, Landscape.Operation.HILL);
        Map<Integer, Integer> mapAfterCallingModify = testHook.get();
        landscape = new Landscape();
        testHook = landscape.new TestHook();
        testHook.createHill(0,2);
        Map<Integer, Integer> mapAfterCallingCreateHill = testHook.get();
        assertEquals(mapAfterCallingCreateHill, mapAfterCallingModify);
    }

    /** createValley() */
    @Test
    public void testCreateValleyEven() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();
        landscape.modify(0, 3, Landscape.Operation.VALLEY);

        Map<Integer, Integer> mapAfterCallingModify = testHook.get();

        landscape = new Landscape();
        testHook = landscape.new TestHook();
        testHook.createValley(0, 3);

        Map<Integer, Integer> mapAfterCallingCreateValley = testHook.get();

        assertEquals(mapAfterCallingModify, mapAfterCallingCreateValley);
    }
    @Test
    public void testCreateValleyOdd() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();
        landscape.modify(0, 2, Landscape.Operation.VALLEY);

        Map<Integer, Integer> mapAfterCallingModify = testHook.get();

        landscape = new Landscape();
        testHook = landscape.new TestHook();
        testHook.createValley(0, 2);

        Map<Integer, Integer> mapAfterCallingCreateValley = testHook.get();

        assertEquals(mapAfterCallingModify, mapAfterCallingCreateValley);
    }

    /** verify() */
    @Test
    public void testVerifyNullModifications() {
        Landscape landscape = new Landscape();
        assertFalse(landscape.verify(null, List.of(0, 0, 0, 0, 0)));
    }

    @Test
    public void testVerifyNullHeight() {
        Landscape landscape = new Landscape();
        Landscape.Modification modification = new Landscape.Modification(0, 3, Landscape.Operation.VALLEY);

        assertFalse(landscape.verify(List.of(modification), null));
    }

    @Test
    public void testVerifyEmptyHeights() {
        Landscape landscape = new Landscape();
        Landscape.Modification modification = new Landscape.Modification(0, 3, Landscape.Operation.VALLEY);

        assertTrue(landscape.verify(List.of(modification), List.of()));
    }

    @Test
    public void testVerifyHeightsWithNull() {
        Landscape landscape = new Landscape();
        Landscape.Modification modification = new Landscape.Modification(0, 3, Landscape.Operation.VALLEY);

        assertFalse(landscape.verify(List.of(modification), Arrays.asList(0, 0, null, 0)));
    }

    @Test
    public void testRaiseDepressVerify() {
        Landscape landscape = new Landscape();
        Landscape.TestHook testHook = landscape.new TestHook();

        landscape.modify(0, 4, Landscape.Operation.RAISE);
        landscape.modify(2, 3, Landscape.Operation.DEPRESS);

        assertEquals(1, testHook.get().get(0).intValue());
        assertEquals(1, testHook.get().get(1).intValue());
        assertEquals(0, testHook.get().get(2).intValue());
        assertEquals(0, testHook.get().get(3).intValue());
        assertEquals(1, testHook.get().get(4).intValue());
        List<Integer> heights = List.of(1, 1, 0, 0, 1);
        assertTrue(landscape.verify(List.of(), heights));
    }
}