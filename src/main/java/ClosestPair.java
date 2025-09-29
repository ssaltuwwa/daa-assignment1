import java.util.Arrays;

class Point {
    double x, y;
    Point(double x, double y) { this.x = x; this.y = y; }
    double dist(Point p) { double dx = x - p.x, dy = y - p.y; return Math.sqrt(dx * dx + dy * dy); }
}

public class ClosestPair {
    public static Metrics findClosestPair(Point[] points) {
        if (points.length < 2) throw new IllegalArgumentException();
        Point[] px = points.clone();
        Arrays.sort(px, (a, b) -> Double.compare(a.x, b.x));
        Point[] py = points.clone();
        Arrays.sort(py, (a, b) -> Double.compare(a.y, b.y));
        int[] maxDepth = {0};
        long[] comps = {0};
        long start = System.nanoTime();
        double d = closest(px, py, 0, points.length - 1, 1, maxDepth, comps);
        return new Metrics(System.nanoTime() - start, maxDepth[0], comps[0]);
    }

    private static double closest(Point[] px, Point[] py, int low, int high, int depth, int[] maxDepth, long[] comps) {
        maxDepth[0] = Math.max(maxDepth[0], depth);
        if (high - low + 1 <= 3) return bruteForce(px, low, high, comps);
        int mid = low + (high - low) / 2;
        Point midP = px[mid];
        Point[] leftPy = Arrays.copyOfRange(py, 0, mid - low + 1);
        Point[] rightPy = Arrays.copyOfRange(py, mid - low + 1, py.length);
        double dl = closest(px, leftPy, low, mid, depth + 1, maxDepth, comps);
        double dr = closest(px, rightPy, mid + 1, high, depth + 1, maxDepth, comps);
        double d = Math.min(dl, dr);
        java.util.List<Point> strip = new java.util.ArrayList<>();
        for (Point p : py) if (Math.abs(p.x - midP.x) < d) strip.add(p);
        for (int i = 0; i < strip.size(); i++)
            for (int j = i + 1; j < i + 8 && j < strip.size(); j++) {
                comps[0]++;
                d = Math.min(d, strip.get(i).dist(strip.get(j)));
            }
        return d;
    }

    private static double bruteForce(Point[] points, int low, int high, long[] comps) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = low; i <= high; i++)
            for (int j = i + 1; j <= high; j++) {
                comps[0]++;
                min = Math.min(min, points[i].dist(points[j]));
            }
        return min;
    }
}