import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TheNecklace {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static final int MAX_COL = 51;
    private final List<List<Integer>> nodes;
    private final List<List<Boolean>> traversed = new ArrayList<>();
    private final List<int[]> path = new ArrayList<>();

    private TheNecklace(List<List<Integer>> nodes) {
        this.nodes = nodes;
        for (int i = 0; i < MAX_COL; ++i) {
            traversed.add(new ArrayList<>());
        }

        boolean eulerian = true;
        int src = -1;
        for (int i = 0; i < nodes.size(); ++i) {
            List<Integer> node = nodes.get(i);
            if (node.size() % 2 != 0) {
                eulerian = false;
                break;
            }
            for (int j = 0; j < node.size(); ++j) {
                traversed.get(i).add(false);
            }
            if (src == -1 && node.size() > 0) {
                src = i;
            }
        }

        if (eulerian) {
            traverse(src, 0);
        }
    }

    private void traverse(int src, int pos) {
        for (int i = 0; i < nodes.get(src).size(); ++i) {
            if (traversed.get(src).get(i)) {
                continue;
            }
            traversed.get(src).set(i, true);
            int dst = nodes.get(src).get(i);
            for (int j = 0; j < nodes.get(dst).size(); ++j) {
                if (nodes.get(dst).get(j) == src &&
                        !traversed.get(dst).get(j)) {
                    traversed.get(dst).set(j, true);
                    break;
                }
            }
            path.add(pos, new int[] { src, dst });
            traverse(dst, pos + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            int m = Integer.parseInt(reader.readLine().trim());
            List<List<Integer>> nodes = new ArrayList<>();
            for (int j = 0; j < MAX_COL; ++j) {
                nodes.add(new ArrayList<>());
            }
            for (int j = 0; j < m; ++j) {
                List<Integer> node = stream(
                        reader.readLine().trim().split(" "))
                                .filter(x -> !x.equals(""))
                                .map(Integer::parseInt)
                                .collect(toList());
                nodes.get(node.get(0)).add(node.get(1));
                nodes.get(node.get(1)).add(node.get(0));
            }
            TheNecklace necklace = new TheNecklace(nodes);
            System.out.println("Case #" + (i + 1));
            if (necklace.path.size() != 0) {
                necklace.path
                        .forEach(x -> System.out.println(x[0] + " " + x[1]));
            } else {
                System.out.println("some beads may be lost");
            }
            if (i < n - 1) {
                System.out.println();
            }
        }
    }

}

