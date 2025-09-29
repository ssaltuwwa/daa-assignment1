import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkMain {
    private static final Random RANDOM = new Random();
    private static final int[] SIZES = {1000, 10000, 100000};
    private static final int TRIALS = 5;

    public static void main(String[] args) throws IOException {
        try (FileWriter csv = new FileWriter("bench.csv")) {
            csv.write("algo,n,time_ns,depth,comps\n");
            for (int n : SIZES) {
                benchmark("MergeSort", MergeSort::sort, csv, n);
                benchmark("QuickSort", QuickSort::sort, csv, n);
                benchmarkSelect(csv, n);
                benchmarkClosest(csv, n);
            }
        }
    }

    private static void benchmark(String algo, java.util.function.Function<int[], Metrics> sorter, FileWriter csv, int n) throws IOException {
        long totalTime = 0, totalComps = 0;
        int totalDepth = 0;
        for (int t = 0; t < TRIALS; t++) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = RANDOM.nextInt();
            Metrics m = sorter.apply(arr.clone());
            totalTime += m.time;
            totalDepth += m.depth;
            totalComps += m.comparisons;
        }
        csv.write(String.format("%s,%d,%d,%d,%d\n", algo, n, totalTime / TRIALS, totalDepth / TRIALS, totalComps / TRIALS));
    }

    private static void benchmarkSelect(FileWriter csv, int n) throws IOException {
        long totalTime = 0, totalComps = 0;
        int totalDepth = 0;
        for (int t = 0; t < TRIALS; t++) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = RANDOM.nextInt();
            Metrics m = DeterministicSelect.select(arr.clone(), n / 2 + 1);
            totalTime += m.time;
            totalDepth += m.depth;
            totalComps += m.comparisons;
        }
        csv.write(String.format("Select,%d,%d,%d,%d\n", n, totalTime / TRIALS, totalDepth / TRIALS, totalComps / TRIALS));
    }

    private static void benchmarkClosest(FileWriter csv, int n) throws IOException {
        long totalTime = 0, totalComps = 0;
        int totalDepth = 0;
        for (int t = 0; t < TRIALS; t++) {
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) points[i] = new Point(RANDOM.nextDouble() * 1000, RANDOM.nextDouble() * 1000);
            Metrics m = ClosestPair.findClosestPair(points.clone());
            totalTime += m.time;
            totalDepth += m.depth;
            totalComps += m.comparisons;
        }
        c.write(String.format("ClosestPair,%d,%d,%d,%d\n", n, totalTime / TRIALS, totalDepth / TRIALS, totalComps / TRIALS));
    }
}