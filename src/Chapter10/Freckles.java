import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Freckles {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    static class Edge {
        final double[] src;
        final double[] dst;
        final double distance;

        public Edge(double[] src, double[] dst) {
            super();
            this.src = src;
            this.dst = dst;
            double xx = Math.abs(src[0] - dst[0]);
            double yy = Math.abs(src[1] - dst[1]);
            this.distance = Math.sqrt(xx * xx + yy * yy);
        }
    }

    private static Edge min(List<double[]> tree, List<double[]> nodes) {
        Edge min = null;
        for (double[] n : nodes) {
            for (double[] t : tree) {
                Edge edge = new Edge(t, n);
                if (min != null && edge.distance < min.distance ||
                        min == null) {
                    min = edge;
                }
            }
        }
        return min;
    }

    private static double mst(List<double[]> nodes) {
        double total = 0;
        List<double[]> tree = new ArrayList<>();
        tree.add(nodes.remove(0));
        while (!nodes.isEmpty()) {
            Edge edge = min(tree, nodes);
            total += edge.distance;
            tree.add(edge.dst);
            nodes.remove(edge.dst);
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        DecimalFormat format = new DecimalFormat("#0.00");
        reader.readLine();
        for (int i = 0; i < n; ++i) {
            int m = Integer.parseInt(reader.readLine().trim());
            List<double[]> nodes = new ArrayList<>();
            for (int j = 0; j < m; ++j) {
                List<Double> tuple = stream(reader.readLine().trim().split(" "))
                        .filter(x -> !x.equals(""))
                        .map(Double::parseDouble)
                        .collect(toList());
                nodes.add(new double[] { tuple.get(0), tuple.get(1) });
            }
            System.out.println(format.format(mst(nodes)));
            if (i < n - 1) {
                System.out.println();
            }
            reader.readLine();
        }
    }

}

