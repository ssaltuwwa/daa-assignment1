import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDeterministicSelect {
    @Test
    void testSmallArray() {
        int[] arr = {3, 1, 4, 2, 5};
        Metrics m = DeterministicSelect.select(arr, 3);
        assertEquals(3, m.result);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        Metrics m = DeterministicSelect.select(arr, 2);
        assertEquals(2, m.result);
    }

    @Test
    void testInvalidK() {
        int[] arr = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> DeterministicSelect.select(arr, 0));
    }
}