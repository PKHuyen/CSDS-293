import java.util.*;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Landscape {

    public enum Operation { RAISE, DEPRESS, HILL, VALLEY, UNSUPPORT}

    public record Modification(int x1, int x2, Operation operation) {}

    /** Bug 1: Modification -> Landscape */
    private static final Logger logger = Logger.getLogger(Landscape.class.getName());
    private final Map<Integer, Integer> landscape = new HashMap<>();

    private Map<Integer, Integer> get() {
        return landscape;
    }

    /** Bug 2: Switch case have no break. =4 complexity, max complexity so using EnumMap to lower complexity. */
    private final Map<Operation, BiConsumer<Integer, Integer>> opHandler = new EnumMap<>(Operation.class);

    public Landscape() {
        opHandler.put(Operation.RAISE, this::raiseHill);
        opHandler.put(Operation.DEPRESS, this::depressHill);
        opHandler.put(Operation.HILL, this::createHill);
        opHandler.put(Operation.VALLEY, this::createValley);
    }

    public void modify(int x1, int x2, Operation operation) {
        Objects.requireNonNull(x1, "Null found in modify()");
        Objects.requireNonNull(x2, "Null found in modify()");
        Objects.requireNonNull(operation, "Null found in modify()");

        /** Bug 3: !bool => bool */
        Optional.of(x1 > x2).
                filter(bool -> bool).
                ifPresent(bool -> logger.log(Level.WARNING, "x1 cannot be greater than x2; returning early"));

        BiConsumer<Integer, Integer> warning = (num1, num2) -> logger.log(Level.WARNING, "Unsupported Operation");
        opHandler.getOrDefault(operation,warning).accept(x1, x2);
    }

    /** Bug 4: range -> rangeClosed*/
    private void raiseHill(int x1, int x2) {
        IntStream.rangeClosed(x1, x2).
                forEach(height -> landscape.put(height, landscape.getOrDefault(height, 0) + 1));
    }

    /** Bug 5: range -> rangeClosed*/
    private void depressHill(int x1, int x2) {
        IntStream.rangeClosed(x1, x2).
                forEach(height -> landscape.put(height, landscape.getOrDefault(height, 0) - 1));
    }

    private void createHill(int x1, int x2) {
        int numPointsBetween = 0;
        int offset = 0;

        /** Bug 6: Need = to update if even */
        while (x2 - x1 >= numPointsBetween) {
            landscape.put(x1 + offset, offset + 1);
            landscape.put(x2 - offset, offset + 1);
            offset++;
            numPointsBetween += 2;
        }
    }

    private void createValley(int x1, int x2) {
        int numPointsBetween = 0;
        int offset = 0;

        /** Bug 7: Need = to update if even */
        while (x2 - x1 >= numPointsBetween) {
            landscape.put(x1 + offset, -offset - 1);
            landscape.put(x2 - offset, -offset - 1);
            offset++;
            numPointsBetween += 2;
        }
    }

    public boolean verify(Collection<Modification> modifications, List<Integer> heights) {
        try {
            Objects.requireNonNull(modifications);
        }
        catch (NullPointerException e) {
            logger.log(Level.WARNING, "Modification is null");
            return false;
        }

        try {
            Objects.requireNonNull(heights);
        }
        catch (NullPointerException e) {
            logger.log(Level.WARNING, "Heights is null");
            return false;
        }

        /** Bug 8: Previously, x2 was placed before x1 */
        modifications.forEach(modification -> {
            Optional.ofNullable(modification)
                    .ifPresentOrElse(modification1 ->
                            modify(modification1.x1, modification1.x2, modification1.operation),
                            () -> logger.log(Level.WARNING, "modification is null, skipping"));
        });
// Testing not in range. if not initialize, get(i) = 0
        for (int i = 0; i < heights.size(); i++) {
            if (!Objects.equals(get().get(i), heights.get(i)))
                return false;
        }

        return true;
    }

    /**
     * Internal testing class for testing private methods.
     * You can assume there are no errors here.
     */
    class TestHook {
        Map<Integer, Integer> get() {
            return Landscape.this.get();
        }

        /** Created private method raiseHill() */
        void raiseHill(int x1, int x2) {
            Landscape.this.raiseHill(x1, x2);
        }

        /** Created private method depressHill() */
        void depressHill(int x1, int x2) {
            Landscape.this.depressHill(x1, x2);
        }

        void createHill(int x1, int x2) {
            Landscape.this.createHill(x1, x2);
        }

        void createValley(int x1, int x2) {
            Landscape.this.createValley(x1, x2);
        }
    }
}
