import java.util.*;

public class TouristGuide {
    private final Set<String> cutVertices = new HashSet<>();
    private final Set<String> visited = new HashSet<>();
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> depths = new HashMap<>();
    private final Map<String, Integer> low = new HashMap<>();
    private final Map<String, Set<String>> graph;

    public TouristGuide(Map<String, Set<String>> graph) {
        this.graph = graph;
        Set<String> processed = new HashSet<>();
        Set<String> roots = new HashSet<>(graph.keySet());
        while (processed.size() != graph.keySet().size()) {
            findCutVertices(roots.iterator().next(), 0);
            processed.addAll(visited);
            roots.removeAll(visited);
        }
    }

    public Set<String> getCutVertices() {
        return cutVertices;
    }

    private void findCutVertices(String v, int d) {
        visited.add(v);
        depths.put(v, d);
        low.put(v, d);
        int childCount = 0;
        boolean isCutVertix = false;
        for (String n : graph.get(v)) {
            if (!visited.contains(n)) {
                parent.put(n, v);
                findCutVertices(n, d + 1);
                childCount++;
                if (low.get(n) >= depths.get(v)) {
                    isCutVertix = true;
                }
                low.put(v, Math.min(low.get(v), low.get(n)));
            } else if (!n.equals(parent.get(v))) {
                low.put(v, Math.min(low.get(v), depths.get(n)));
            }
        }
        if ((parent.containsKey(v) && isCutVertix) ||
                (!parent.containsKey(v) && childCount > 1)) {
            cutVertices.add(v);
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = 0;
        int c = 0;
        while ((n = s.nextInt()) != 0) {
            c++;
            if (c > 1) {
                System.out.println();
            }
            s.nextLine();
            for (int i = 0; i < n; ++i) {
                s.nextLine();
            }
            Map<String, Set<String>> graph = new HashMap<>();
            int edgesCount = s.nextInt();
            s.nextLine();
            for (int i = 0; i < edgesCount; ++i) {
                String[] edge = s.nextLine().replaceAll("\\s+", " ").split(" ");
                assert (edge.length == 2);
                graph.putIfAbsent(edge[0], new HashSet<>());
                graph.putIfAbsent(edge[1], new HashSet<>());
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
            TouristGuide t = new TouristGuide(graph);
            List<String> cutPoints = new ArrayList<>(t.getCutVertices());
            cutPoints.sort(String::compareTo);
            System.out.println(String.format("City map #%d: %d camera(s) found", c, cutPoints.size()));
            cutPoints.forEach(System.out::println);
        }
    }
}

