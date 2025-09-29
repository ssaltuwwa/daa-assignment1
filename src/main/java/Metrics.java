public class Metrics {
    public final long time;
    public final int depth;
    public final long comparisons;
    public final int result;

    public Metrics(long time, int depth, long comparisons) {
        this.time = time;
        this.depth = depth;
        this.comparisons = comparisons;
        this.result = -1;
    }

    public Metrics(long time, int depth, long comparisons, int result) {
        this.time = time;
        this.depth = depth;
        this.comparisons = comparisons;
        this.result = result;
    }
}