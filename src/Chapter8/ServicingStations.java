import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ServicingStations {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    static class Bits {
        long v = 0L;

        boolean get(int b) {
            return (this.v & (1L << b)) != 0;
        }

        void set(int b) {
            this.v |= (1L << b);
        }
    }

    private final Bits[] cities;
    private final Bits[] stack;
    private int count;
    private Bits currentGraph;


    public ServicingStations(Bits[] cities) throws Exception {
        count = 0;
        this.stack = new Bits[40];
        for (int i = 0; i < this.stack.length; ++i) {
            this.stack[i] = new Bits();
        }
        this.cities = cities;
        for (Bits graph : graphs(this.cities)) {
            currentGraph = graph;
            for (int i = 0; i < cities.length; ++i) {
                if (backtrack(i, 0, 0)) {
                    count += i;
                    break;
                }
            }
        }
    }

    private List<Bits> graphs(Bits[] cities) {
        List<Bits> graphs = new ArrayList<>();
        Bits all = new Bits();
        boolean finished = false;
        while (!finished) {
            finished = true;
            for (int i = 0; i < cities.length; ++i) {
                if (!all.get(i)) {
                    finished = false;
                    Bits graph = bfs(i, cities);
                    graphs.add(graph);
                    all.v |= graph.v;
                    break;
                }
            }
        }
        return graphs;
    }

    private Bits bfs(int s, Bits[] cities) {
        Deque<Integer> next = new ArrayDeque<>();
        Bits visited = new Bits();
        next.add(s);
        while (!next.isEmpty()) {
            int n = next.pop();
            for (int i = 0; i < cities.length; ++i) {
                if (cities[n].get(i) && !visited.get(i)) {
                    next.add(i);
                }
            }
            visited.set(n);
        }
        return visited;
    }

    private boolean backtrack(int count, int pos, int depth) throws Exception {
        if (count == 0) {
            return ((stack[depth].v &
                    currentGraph.v) == currentGraph.v);
        }
        for (int i = pos; i < cities.length; ++i) {
            if (!currentGraph.get(i) && !stack[depth].get(i)) {
                continue;
            }

            stack[depth + 1].v = stack[depth].v | cities[i].v;
            if (stack[depth + 1].v == stack[depth].v) {
                continue;
            }

            if (backtrack(count - 1, i + 1, depth + 1)) {
                return true;
            }
        }
        return false;
    }


    private static List<Integer> parseLine(String line) {
        return stream(line.split(" "))
                .filter(x -> !x.equals(""))
                .map(Integer::parseInt)
                .collect(toList());
    }

    public static void main(String[] args) throws Exception {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.trim().isEmpty()) {
                continue;
            }
            List<Integer> nm = parseLine(currentLine);
            int n = nm.get(0);
            int m = nm.get(1);
            if (n == 0 && m == 0) {
                break;
            }
            Bits[] cities = new Bits[n];
            for (int i = 0; i < cities.length; ++i) {
                cities[i] = new Bits();
                cities[i].set(i);
            }
            while (m > 0) {
                if ((currentLine = reader.readLine()).trim().isEmpty()) {
                    continue;
                }
                List<Integer> fromTo = parseLine(currentLine.trim());
                cities[fromTo.get(0) - 1].set(fromTo.get(1) - 1);
                cities[fromTo.get(1) - 1].set(fromTo.get(0) - 1);
                m--;
            }
            System.out.println(new ServicingStations(cities).count);
        }
    }

}

