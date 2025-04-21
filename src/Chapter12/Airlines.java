import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

public class Airlines {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static final double R = 6378.0;
    private static final double PI = 3.141592653589793;
    private static final double K = PI / 180.0;

    private static long distance(double phi1, double lam1, double phi2, double lam2) {
        double phi1r = phi1 * K;
        double phi2r = phi2 * K;
        double phid = phi2r - phi1r;
        double lamd = (lam2 - lam1) * K;
        double d = 2.0 * R * asin(sqrt((1 - cos(phid) + cos(phi1r) * cos(phi2r) * (1 - cos(lamd))) / 2));
        return Math.round(d);
    }

    private static class City {
        String city;
        long total;

        public City(String city, long total) {
            this.city = city;
            this.total = total;
        }
    }

    private static long shortest(String from, String to, Map<String, Map<String, Long>> graph) {
        if (!graph.containsKey(from)) {
            return -1;
        }
        PriorityQueue<City> queue = new PriorityQueue<>(Comparator.comparingLong(a -> a.total));
        City start = new City(from, 0);
        queue.add(start);
        Set<String> visited = new HashSet<>();
        Map<String, City> min = new HashMap<>();
        min.put(start.city, start);

        while (!queue.isEmpty()) {
            City v = queue.poll();
            visited.add(v.city);

            if (v.city.equalsIgnoreCase(to)) {
                return v.total;
            }

            if (!graph.containsKey(v.city)) continue;
            Set<String> neighbours = graph.get(v.city).keySet();

            for (String connected : neighbours) {
                if (visited.contains(connected)) continue;
                long distance = graph.get(v.city).get(connected);
                long totalDistance = v.total + distance;
                if (min.containsKey(connected)) {
                    City connectedCity = min.get(connected);
                    if (connectedCity.total > totalDistance) {
                        connectedCity.total = totalDistance;
                        queue.remove(connected);
                        queue.add(connectedCity);
                    }
                } else {
                    City value = new City(connected, totalDistance);
                    min.put(connected, value);
                    queue.add(value);
                }
            }
        }

        if (!min.containsKey(to)) {
            return -1;
        }
        return min.get(to).total;
    }

    public static void main(String[] args) throws IOException {
        int k = 0;
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            Map<String, double[]> cities = new HashMap<>();
            Map<String, Map<String, Long>> graph = new HashMap<>();
            String[] nmq = currentLine.split(" ");
            int n = Integer.parseInt(nmq[0]);
            int m = Integer.parseInt(nmq[1]);
            int q = Integer.parseInt(nmq[2]);
            if (n == 0) break;
            if (k > 0) System.out.println();
            System.out.println("Case #" + (++k));
            for (int i = 0; i < n; i++) {
                String[] cityLtLn = reader.readLine().split(" ");
                String city = cityLtLn[0];
                double lt = Double.parseDouble(cityLtLn[1]);
                double ln = Double.parseDouble(cityLtLn[2]);
                cities.put(city, new double[]{lt, ln});
            }
            for (int i = 0; i < m; i++) {
                String[] fromTo = reader.readLine().split(" ");
                String from = fromTo[0];
                String to = fromTo[1];
                graph.putIfAbsent(from, new HashMap<>());
                graph.get(from).putIfAbsent(to,
                        distance(
                                cities.get(from)[0],
                                cities.get(from)[1],
                                cities.get(to)[0],
                                cities.get(to)[1]
                        ));
            }
            for (int i = 0; i < q; i++) {
                String[] fromTo = reader.readLine().split(" ");
                String from = fromTo[0];
                String to = fromTo[1];
                long shortest = shortest(from, to, graph);
                if (shortest == -1) {
                    System.out.println("no route exists");
                } else {
                    System.out.println(shortest + " km");
                }
            }
        }
    }
}

