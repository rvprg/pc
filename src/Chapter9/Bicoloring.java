import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bicoloring {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static void add(Map<Integer, Set<Integer>> graph, Integer nodeFrom,
            Integer nodeTo) {
        if (!graph.containsKey(nodeFrom)) {
            graph.put(nodeFrom, new HashSet<Integer>());
        }
        if (!graph.containsKey(nodeTo)) {
            graph.put(nodeTo, new HashSet<Integer>());
        }
        graph.get(nodeFrom).add(nodeTo);
        graph.get(nodeTo).add(nodeFrom);
    }

    private static boolean solve(
            Map<Integer, Set<Integer>> graph) {
        if (graph.size() == 0) {
            return true;
        }

        Deque<Integer> next = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Boolean> colors = new HashMap<>();

        boolean color = true;
        Integer start = graph.keySet().iterator().next();
        next.push(start);
        colors.put(start, color);

        while (!next.isEmpty()) {
            Integer node = next.pop();
            color = colors.get(node);
            for (Integer adjNode : graph.get(node)) {
                if (!visited.contains(adjNode)) {
                    visited.add(adjNode);
                    next.addLast(adjNode);
                    colors.put(adjNode, !color);
                } else if (colors.get(adjNode) == color) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            int n = Integer.parseInt(reader.readLine().trim());
            if (n == 0) {
                break;
            }
            n = Integer.parseInt(reader.readLine().trim());
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (int j = 0; j < n; ++j) {
                List<Integer> toFrom = stream(
                        reader.readLine().trim().split(" "))
                                .filter(x -> !x.equals(""))
                                .map(Integer::parseInt)
                                .collect(toList());
                add(graph, toFrom.get(0), toFrom.get(1));
            }
            System.out
                    .println(
                            solve(graph) ? "BICOLORABLE." : "NOT BICOLORABLE.");
        }
    }
}

