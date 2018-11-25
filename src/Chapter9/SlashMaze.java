import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SlashMaze {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static int[] find(Map<Integer, List<Integer>> graph) {
        int count = 0;
        int max = 0;
        Set<Integer> visited = new HashSet<>();
        while (true) {
            int[] m = new int[] { 0, 0 };
            Integer src = graph.keySet().stream()
                    .filter(x -> !visited.contains(x))
                    .findFirst().orElse(Integer.MIN_VALUE);
            if (src == Integer.MIN_VALUE) {
                break;
            }
            find(graph, visited, src, m);
            count += m[0];
            max = Math.max(max, m[1]);
        }
        return new int[] { count, max / 2 };
    }

    private static void find(Map<Integer, List<Integer>> graph,
            Set<Integer> visited, int parent, int m[]) {
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> depths = new HashMap<>();
        int src = parent;
        stack.push(src);
        depths.put(src, 0);

        while (!stack.isEmpty()) {
            src = stack.pop();
            int depth = depths.get(src);
            if (!visited.contains(src)) {
                visited.add(src);
                for (int adj : graph.get(src)) {
                    if (adj == parent && depth > 2) {
                        m[0] += 1;
                        m[1] = Math.max(m[1], depth + 1);
                    }
                    stack.push(adj);
                    depths.put(adj, depth + 1);
                }
            }
        }
    }

    private static List<Integer> parseLine(String line) {
        return stream(line.trim().split(" "))
                .filter(x -> !x.equals(""))
                .map(Integer::parseInt)
                .collect(toList());
    }

    public static void add(Map<Integer, List<Integer>> graph, int src,
            int dst) {
        if (!graph.containsKey(src)) {
            graph.put(src, new ArrayList<>());
        }
        if (!graph.containsKey(dst)) {
            graph.put(dst, new ArrayList<>());
        }
        graph.get(src).add(dst);
        graph.get(dst).add(src);
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        int scenario = 1;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            List<Integer> wh = parseLine(currentLine);
            if (wh.get(0) == 0 && wh.get(1) == 0) {
                break;
            }
            int node = 0;
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int i = 0; i < wh.get(1); ++i) {
                currentLine = reader.readLine().trim();
                for (char c : currentLine.toCharArray()) {
                    if (c == '/') {
                        add(graph, node, node + 1);
                        add(graph, node + 2, node + 3);
                    } else if (c == '\\') {
                        add(graph, node, node + 3);
                        add(graph, node + 1, node + 2);
                    }
                    add(graph, node, node - 2);
                    int nodeBelow = node + (wh.get(0) * 4) + 1;
                    add(graph, node + 3, nodeBelow);
                    node += 4;
                }
            }
            int[] result = find(graph);
            System.out.println("Maze #" + scenario + ":");
            if (result[0] > 0) {
                System.out.println(
                        result[0] + " Cycles; the longest has length " +
                                result[1] + ".");
            } else {
                System.out.println("There are no cycles.");
            }
            scenario++;
            System.out.println();
        }
    }
}

