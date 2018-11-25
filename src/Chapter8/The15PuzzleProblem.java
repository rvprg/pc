import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class The15PuzzleProblem {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    static class Node {
        final int[] node;
        final String path;
        final int d;
        final boolean isSolution;

        public Node(Node node, int i, int j, String p) {
            this.node = Arrays.copyOf(node.node, node.node.length);
            int tmp = this.node[i];
            this.node[i] = this.node[j];
            this.node[j] = tmp;
            this.path = node.path + p;
            this.d = distance(this.node);
            this.isSolution = d == 0;
        }

        private boolean isSolvable() {
            int sum = 0;
            int f = indexOfZero(this.node) / 4 + 1;
            for (int i = 0; i < this.node.length; ++i) {
                int c = 0;
                for (int j = i; j < this.node.length; ++j) {
                    if (this.node[i] > 0 && this.node[j] > 0 &&
                            this.node[i] > this.node[j]) {
                        c++;
                    }
                }
                sum += c;
            }
            return (sum + f) % 2 == 0;
        }

        private int distance(int[] node) {
            int d = 0;
            for (int k = 0; k < node.length; ++k) {
                if (node[k] == 0) {
                    continue;
                }
                int r0 = (node[k] - 1) / 4;
                int c0 = (node[k] - 1) % 4;
                int r1 = (k) / 4;
                int c1 = (k) % 4;
                d += Math.abs(r0 - r1) + Math.abs(c0 - c1);
            }
            return d;
        }

        public Node(int[] puzzle) {
            this.node = puzzle;
            this.path = "";
            this.d = distance(this.node);
            this.isSolution = d == 0;
        }

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
    }

    private static final int FOUND = Integer.MIN_VALUE;

    public static int indexOfZero(int[] puzzle) {
        for (int i = 0; i < puzzle.length; ++i) {
            if (puzzle[i] == 0) {
                return i;
            }
        }
        return 0;
    }

    public static List<Node> adjacent(Node puzzle) {
        List<Node> nodes = new ArrayList<>();
        int p = indexOfZero(puzzle.node);

        if (p + 1 <= 15 && p != 3 && p != 7 && p != 11 && p != 15) {
            nodes.add(new Node(puzzle, p, p + 1, "R"));
        }

        if (p - 4 >= 0) {
            nodes.add(new Node(puzzle, p, p - 4, "U"));
        }

        if (p - 1 >= 0 && p % 4 != 0) {
            nodes.add(new Node(puzzle, p, p - 1, "L"));
        }

        if (p + 4 <= 15) {
            nodes.add(new Node(puzzle, p, p + 4, "D"));
        }

        return nodes;
    }

    private static String solve(Node root) {
        int limit = root.d;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (true) {
            int newBound = search(stack, 0, limit);
            if (newBound == FOUND) {
                return stack.peek().path;
            }
            if (newBound == Integer.MAX_VALUE) {
                return "This puzzle is not solvable.";
            }
            limit = newBound;
        }
    }

    private static int search(Deque<Node> stack, int g, int limit) {
        Node node = stack.peek();
        int f = g + node.d;

        if (f > limit) {
            return f;
        }
        if (node.isSolution) {
            return FOUND;
        }
        int min = Integer.MAX_VALUE;
        for (Node adjNode : adjacent(node)) {
            if (!stack.contains(adjNode)) {
                stack.push(adjNode);
                int newBound = search(stack, g + 1, limit);
                if (newBound == FOUND) {
                    return FOUND;
                }
                if (newBound < min) {
                    min = newBound;
                }
                stack.pop();
            }
        }
        return min;
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
            int[] puzzle = new int[16];
            int l = 0;
            for (int j = 0; j < 4; ++j) {
                List<Integer> line = parseLine(reader.readLine().trim());
                for (int k = 0; k < 4; ++k) {
                    puzzle[l] = line.get(k);
                    l += 1;
                }
            }
            Node node = new Node(puzzle);
            if (!node.isSolvable()) {
                System.out.println("This puzzle is not solvable.");
            } else {
                System.out.println(solve(node));
            }
        }
    }
}

