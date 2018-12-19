import java.util.*;

public class TheProblemWithTheProblemSetter {
    private final Map<Node, List<Edge>> graph = new HashMap<>();

    static class Node {
        public static final Node SINK = new Node(0, Type.SINK);
        public static final Node SOURCE = new Node(0, Type.SOURCE);
        final int value;
        final Type type;

        enum Type {
            SINK,
            SOURCE,
            PROBLEM,
            CATEGORY
        }

        public Node(int v, Type type) {
            this.value = v;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value &&
                    type == node.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, type);
        }
    }

    static class Edge {
        final Node start;
        final Node end;
        final int capacity;
        int flow;
        int residual;
        Edge reversed;

        public Edge(Node start, Node end, int capacity, int flow, int residual) {
            this.start = start;
            this.end = end;
            this.capacity = capacity;
            this.flow = flow;
            this.residual = residual;
        }
    }

    private void add(Node from, Node to, int capacity) {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.putIfAbsent(to, new ArrayList<>());
        Edge toEdge = new Edge(from, to, capacity, 0, capacity);
        Edge fromEdge = new Edge(to, from, capacity, 0, 0);
        toEdge.reversed = fromEdge;
        fromEdge.reversed = toEdge;
        graph.get(from).add(toEdge);
        graph.get(to).add(fromEdge);
    }

    private int solve() {
        int flow = 0;
        while (true) {
            Deque<Node> q = new ArrayDeque<>();
            q.push(Node.SOURCE);
            Set<Node> visited = new HashSet<>();
            Map<Node, Edge> path = new HashMap<>();
            while (!q.isEmpty() && !path.containsKey(Node.SINK)) {
                Node n = q.pollFirst();
                visited.add(n);
                for (Edge e : graph.get(n)) {
                    if (!visited.contains(e.end) && e.residual > 0 && 
                            !e.end.equals(Node.SOURCE)) {
                        q.addLast(e.end);
                        path.put(e.end, e);
                    }
                }
            }
            if (!path.containsKey(Node.SINK)) {
                return flow;
            }
            Edge edge = path.get(Node.SINK);
            while (edge != null) {
                edge.flow++;
                edge.residual--;
                edge.reversed.flow--;
                edge.reversed.residual++;
                edge = path.get(edge.start);
            }
            flow++;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = 0;
        int m = 0;
        while (true) {
            TheProblemWithTheProblemSetter solver = new TheProblemWithTheProblemSetter();
            m = s.nextInt();
            n = s.nextInt();
            if (m == 0 && n == 0) {
                break;
            }
            int targetFlow = 0;
            for (int i = 1; i <= m; ++i) {
                int capacity = s.nextInt();
                targetFlow += capacity;
                solver.add(new Node(i, Node.Type.CATEGORY), Node.SINK, capacity);
            }
            for (int i = 1; i <= n; ++i) {
                Node node = new Node(i, Node.Type.PROBLEM);
                solver.add(Node.SOURCE, node, 1);
                int neighbors = s.nextInt();
                solver.add(node, Node.SOURCE, 1);
                while (neighbors > 0) {
                    Node toNode = new Node(s.nextInt(), Node.Type.CATEGORY);
                    solver.add(node, toNode, 1);
                    neighbors--;
                }
            }
            if (solver.solve() == targetFlow) {
                System.out.println(1);
                for (int i = 1; i <= m; ++i) {
                    Node category = new Node(i, Node.Type.CATEGORY);
                    List<Edge> edges = solver.graph.get(category);
                    boolean first = true;
                    for (Edge p : edges) {
                        Edge edge = p.reversed;
                        if (edge.end.equals(category) && edge.flow == edge.capacity) {
                            System.out.print((first ? "" : " ") + edge.start.value);
                            first = false;
                        }
                    }
                    System.out.println();
                }
            } else {
                System.out.println(0);
            }
        }
    }
}

