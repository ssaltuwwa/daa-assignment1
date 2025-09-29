import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestQuickSort {
    @Test
    void testEmptyArray() {
        int[] arr = {};
        Metrics m = QuickSort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {1};
        Metrics m = QuickSort.sort(arr);
        assertArrayEquals(new int[]{1}, arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = arr.clone();
        Metrics m = QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        int[] arr = {5, 2, 8, 1, 9};
        int[] expected = {1, 2, 5, 8, 9};
        Metrics m = QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }
}