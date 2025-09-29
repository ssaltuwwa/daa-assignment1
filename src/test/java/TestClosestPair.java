import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestClosestPair {
    @Test
    void testTwoPoints() {
        Point[] points = {new Point(0, 0), new Point(1, 1)};
        Metrics m = ClosestPair.findClosestPair(points);
        assertTrue(m.comparisons > 0);
    }

    @Test
    void testThreePoints() {
        Point[] points = {new Point(0, 0), new Point(1, 1), new Point(2, 2)};
        Metrics m = ClosestPair.findClosestPair(points);
        assertTrue(m.comparisons > 0);
    }

    @Test
    void testInvalidInput() {
        Point[] points = {};
        assertThrows(IllegalArgumentException.class, () -> ClosestPair.findClosestPair(points));
    }
}