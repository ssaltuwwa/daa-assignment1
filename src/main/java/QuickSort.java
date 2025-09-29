import java.util.Random;

public class QuickSort {
    private static final Random RANDOM = new Random();

    private static int partition(int[] arr, int low, int high, long[] comps) {
        int pivotIdx = low + RANDOM.nextInt(high - low + 1);
        swap(arr, pivotIdx, high);
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            comps[0]++;
            if (arr[j] < pivot) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Metrics sort(int[] arr) {
        int[] maxDepth = {0};
        long[] comps = {0};
        long start = System.nanoTime();
        sort(arr, 0, arr.length - 1, 1, maxDepth, comps);
        return new Metrics(System.nanoTime() - start, maxDepth[0], comps[0]);
    }

    private static void sort(int[] arr, int low, int high, int depth, int[] maxDepth, long[] comps) {
        maxDepth[0] = Math.max(maxDepth[0], depth);
        if (low < high) {
            int pivot = partition(arr, low, high, comps);
            sort(arr, low, pivot - 1, depth + 1, maxDepth, comps);
            sort(arr, pivot + 1, high, depth + 1, maxDepth, comps);
        }
    }
}