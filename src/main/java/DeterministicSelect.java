import java.util.Arrays;

public class DeterministicSelect {
    public static Metrics select(int[] arr, int k) {
        if (k < 1 || k > arr.length) throw new IllegalArgumentException();
        int[] copy = arr.clone();
        int[] maxDepth = {0};
        long[] comps = {0};
        long start = System.nanoTime();
        int result = select(copy, 0, copy.length - 1, k - 1, 1, maxDepth, comps);
        return new Metrics(System.nanoTime() - start, maxDepth[0], comps[0], result);
    }

    private static int select(int[] arr, int low, int high, int k, int depth, int[] maxDepth, long[] comps) {
        maxDepth[0] = Math.max(maxDepth[0], depth);
        if (low == high) return arr[low];
        int pivotIdx = medianOfMedians(arr, low, high, comps);
        pivotIdx = partition(arr, low, high, pivotIdx, comps);
        int len = pivotIdx - low;
        if (k == len) return arr[pivotIdx];
        return k < len ? select(arr, low, pivotIdx - 1, k, depth + 1, maxDepth, comps)
                : select(arr, pivotIdx + 1, high, k - len - 1, depth + 1, maxDepth, comps);
    }

    private static int medianOfMedians(int[] arr, int low, int high, long[] comps) {
        int n = high - low + 1;
        if (n <= 5) {
            Arrays.sort(arr, low, high + 1);
            for (int i = low; i < high; i++) comps[0]++;
            return low + n / 2;
        }
        int groups = (n + 4) / 5;
        for (int i = 0; i < groups; i++) {
            int gLow = low + i * 5, gHigh = Math.min(gLow + 4, high);
            Arrays.sort(arr, gLow, gHigh + 1);
            for (int j = gLow; j < gHigh; j++) comps[0]++;
            swap(arr, low + i, gLow + (gHigh - gLow) / 2);
        }
        return select(arr, low, low + groups - 1, (groups - 1) / 2, 0, new int[]{0}, comps);
    }

    private static int partition(int[] arr, int low, int high, int pivotIdx, long[] comps) {
        swap(arr, pivotIdx, high);
        int pivot = arr[high], store = low;
        for (int i = low; i < high; i++) {
            comps[0]++;
            if (arr[i] <= pivot) swap(arr, store++, i);
        }
        swap(arr, store, high);
        return store;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}