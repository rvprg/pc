import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlayingWithWheels {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    static class Node {
        int[] node;

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(node);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (!Arrays.equals(node, other.node))
                return false;
            return true;
        }

        public Node(List<Integer> s) {
            this.node = new int[s.size()];
            for (int i = 0; i < s.size(); ++i) {
                this.node[i] = s.get(i).intValue();
            }
        }

        public Node(Node node, int i, int p) {
            this.node = Arrays.copyOf(node.node, node.node.length);
            this.node[i] += p;
            if (this.node[i] < 0) {
                this.node[i] = 9;
            } else {
                this.node[i] %= 10;
            }
        }
    }

    private static Set<Node> adjacent(Node node) {
        Set<Node> adjacent = new HashSet<>();
        for (int i = 0; i < node.node.length; ++i) {
            adjacent.add(new Node(node, i, 1));
            adjacent.add(new Node(node, i, -1));
        }
        return adjacent;
    }

    private static int solve(Node start, Node end, Set<Node> forbidden) {
        if (start.equals(end)) {
            return 0;
        }

        Deque<Node> next = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Integer> depths = new HashMap<>();

        int depth = 0;
        next.push(start);
        depths.put(start, depth);

        while (!next.isEmpty()) {
            Node node = next.pop();
            depth = depths.get(node);
            for (Node adjNode : adjacent(node)) {
                if (adjNode.equals(end)) {
                    return forbidden.contains(end) ? -1 : depth + 1;
                }
                if (!forbidden.contains(adjNode) &&
                        !visited.contains(adjNode)) {
                    visited.add(adjNode);
                    next.addLast(adjNode);
                    depths.put(adjNode, depth + 1);
                }
            }
        }

        return -1;
    }

    private static List<Integer> parseLine(String line) {
        return stream(line.trim().split(" "))
                .filter(x -> !x.equals(""))
                .map(Integer::parseInt)
                .collect(toList());
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            String currentLine = "";
            while ((currentLine = reader.readLine()).trim().equals(""))
                ;
            Node start = new Node(parseLine(currentLine));
            Node end = new Node(parseLine(reader.readLine()));
            int m = Integer.parseInt(reader.readLine().trim());
            Set<Node> forbidden = new HashSet<>();
            for (int j = 0; j < m; ++j) {
                forbidden.add(new Node(parseLine(reader.readLine())));
            }
            System.out.println(solve(start, end, forbidden));
        }
    }
}

