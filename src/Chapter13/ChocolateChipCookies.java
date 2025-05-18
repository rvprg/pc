import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChocolateChipCookies {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final double EPS = 0.00000001;
    private static double R = 2.5;
    private static int X = 0;
    private static int Y = 1;

    private static double[][] centers(double[] a, double b[], double h) {
        double[] m = {a[X] + (b[X] - a[X]) / 2.0, a[Y] + (b[Y] - a[Y]) / 2.0};

        double[] u1 = {-(b[Y] - a[Y]), b[X] - a[X]};
        double[] u2 = {b[Y] - a[Y], -(b[X] - a[X])};

        double n1 = Math.sqrt(u1[X] * u1[X] + u1[Y] * u1[Y]);
        double n2 = Math.sqrt(u2[X] * u2[X] + u2[Y] * u2[Y]);

        double[][] centers = {
                {m[X] + h * (u1[X] / n1), m[Y] + h * (u1[Y] / n1)},
                {m[X] + h * (u2[X] / n2), m[Y] + h * (u2[Y] / n2)},
        };

        return centers;
    }

    private static int[] counts(double[][] c, List<double[]> points) {
        int[] count = new int[]{0, 0};
        for (int i = 0; i < points.size(); i++) {
            double[] p = points.get(i);
            for (int j = 0; j < 2; j++) {
                double len = Math.sqrt((c[j][X] - p[X]) * (c[j][X] - p[X]) + 
                        (c[j][Y] - p[Y]) * (c[j][Y] - p[Y]));
                if (len <= R || Math.abs(len - R) < EPS) count[j] += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        reader.readLine();

        String line;
        for (int i = 0; i < n; i++) {
            int max = 0;

            List<double[]> points = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("")) break;
                String[] l = line.trim().split(" ");
                double x = Double.parseDouble(l[0]);
                double y = Double.parseDouble(l[1]);
                points.add(new double[]{x, y});
            }

            for (int j = 0; j < points.size(); j++) {
                for (int k = j; k < points.size(); k++) {
                    if (i == k) continue;

                    double[] p1 = points.get(j);
                    double[] p2 = points.get(k);

                    double len = Math.sqrt((p1[X] - p2[X]) * (p1[X] - p2[X]) + 
                          (p1[Y] - p2[Y]) * (p1[Y] - p2[Y]));
                    if (len <= 2 * R || (Math.abs(len - 2 * R) < EPS)) {
                        double h = Math.sqrt(R * R - (len / 2) * (len / 2));
                        double[][] centers = centers(p1, p2, h);
                        int[] counts = counts(centers, points);
                        max = Math.max(counts[0], max);
                        max = Math.max(counts[1], max);
                    }
                }
            }

            if (max > 0) {
                System.out.println(max);
            } else {
                System.out.println(1);
            }
            if (i != n - 1) {
                System.out.println();
            }
        }
    }
}

