import java.util.Arrays;

public class MergeSort {
    private static final int CUTOFF = 16;

    private static void insertionSort(int[] arr, int left, int right, long[] comps) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                comps[0]++;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right, long[] comps) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            comps[0]++;
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, left, arr, left, right - left + 1);
    }

    public static Metrics sort(int[] arr) {
        int[] temp = new int[arr.length];
        int[] maxDepth = {0};
        long[] comps = {0};
        long start = System.nanoTime();
        sort(arr, temp, 0, arr.length - 1, 1, maxDepth, comps);
        return new Metrics(System.nanoTime() - start, maxDepth[0], comps[0]);
    }

    private static void sort(int[] arr, int[] temp, int left, int right, int depth, int[] maxDepth, long[] comps) {
        maxDepth[0] = Math.max(maxDepth[0], depth);
        if (right - left < CUTOFF) {
            insertionSort(arr, left, right, comps);
            return;
        }
        int mid = left + (right - left) / 2;
        sort(arr, temp, left, mid, depth + 1, maxDepth, comps);
        sort(arr, temp, mid + 1, right, depth + 1, maxDepth, comps);
        if (arr[mid] <= arr[mid + 1]) return;
        merge(arr, temp, left, mid, right, comps);
    }
}