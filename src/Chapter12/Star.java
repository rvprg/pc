import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static int E = -2;
    private static int X = -1;
    private static int[][] STAR = {
            {X, X, X, X, X, E, X, X, X, X, X, },
            {X, X, X, X, E, E, E, X, X, X, X, },
            {E, E, E, E, E, E, E, E, E, E, E, },
            {X, E, E, E, E, E, E, E, E, E, X, },
            {X, E, E, E, E, E, E, E, E, E, X, },
            {E, E, E, E, E, E, E, E, E, E, E, },
            {X, X, X, X, E, E, E, X, X, X, X, },
            {X, X, X, X, X, E, X, X, X, X, X, },
    };

    private static int[][] START = {
            {2, 0}, {3, 1}, {4, 1}, {5, 0}, {5, 0},
            {5, 2}, {6, 4}, {7, 5}, {7, 5}, {6, 6},
            {5, 8}, {5, 10}
    };

    private static int[][][] TRAVERSE = {
            {{0, 1}}, {{0, 1}}, {{0, 1}}, {{0, 1}}, {{0, 1}, {-1, 0}},
            {{0, 1}, {-1, 0}}, {{-1, 0}, {0, 1}},
            {{-1, 0}, {0, 1}}, {{-1, 0}, {0, -1}},
            {{-1, 0}, {0, -1}}, {{0, -1}, {-1, 0}},
            {{0, -1}, {-1, 0}},
    };

    private static int sum(int[][] star) {
        int sum = 0;
        for (int i = 0; i < star.length; i++) {
            for (int j = 0; j < star[i].length; ++j) {
                sum += star[i][j] > X ? star[i][j] : 0;
            }
        }
        return sum;
    }

    private interface LineCellVisitor {
        void visit(int i, int j, int line);
    }

    private static void traverseLines(int[][] star, int[] maxs, LineCellVisitor v) {
        for (int start = 0; start < maxs.length; ++start) {
            int d = 0;
            int i = START[start][0]; // row
            int j = START[start][1]; // col
            while (i >= 0 && j >= 0 && i < star.length && j < star[0].length && star[i][j] != X) {
                v.visit(i, j, start);
                i = i + TRAVERSE[start][d][0];
                j = j + TRAVERSE[start][d][1];
                d = (d + 1) % TRAVERSE[start].length;
            }
        }
    }

    private static boolean isValid(int[][] star, int[] maxs) {
        boolean[] valid = new boolean[12];
        traverseLines(star, maxs, (i, j, line) -> {
            if (star[i][j] == maxs[line]) {
                valid[line] = true;
            }
        });
        for (int i = 0; i < valid.length; i++) {
            if (!valid[i]) return false;
        }
        return true;
    }

    private static void fill(int[][] star, int[] maxs, Set[][] overlap) {
        traverseLines(star, maxs, (i, j, line) -> {
            star[i][j] = star[i][j] == E ? maxs[line] : Math.min(star[i][j], maxs[line]);
            if (overlap[i][j] == null) {
                overlap[i][j] = new HashSet();
            }
            overlap[i][j].add(line);
        });
    }

    private static Set<Integer> findNextLines(int[][] star,
                                              Set<Integer>[][] overlap,
                                              int[] maxs, int curr,
                                              Set<Integer> ignore) {
        Set<Integer> nextLines = new HashSet<>();
        for (int i = 0; i < star.length; i++) {
            for (int j = 0; j < star[i].length; j++) {
                if (star[i][j] != maxs[curr]) continue;
                Set<Integer> count = overlap[i][j].stream()
                        .filter(l -> maxs[l] == maxs[curr]).collect(Collectors.toSet());
                count.removeAll(ignore);
                if (count.size() > nextLines.size() && count.contains(curr)) {
                    nextLines = count;
                }
            }
        }
        return nextLines;
    }

    private static Set<Integer> nextLines(int[][] star,
                                          Set<Integer>[][] overlap,
                                          int[] maxs,
                                          Set<Integer> ignore) {
        int max = -1;
        Set<Integer> nextLines = new HashSet<>();
        for (int i = 0; i < maxs.length; i++) {
            Set<Integer> nl = findNextLines(star, overlap, maxs, i, ignore);
            if (maxs[i] > max && nl.size() > 0) {
                max = maxs[i];
                nextLines = nl;
            } else if (maxs[i] == max && nl.size() > nextLines.size()) {
                nextLines = nl;
            }
        }
        return nextLines;
    }

    private static int min(int[][] star, Set<Integer>[][] overlap, int[] maxs) {
        int min = 0;
        Set<Integer> lines;
        Set<Integer> ignore = new HashSet<>();
        while ((lines = nextLines(star, overlap, maxs, ignore)).size() > 0) {
            min += maxs[lines.iterator().next()];
            ignore.addAll(lines);
        }
        return min;
    }

    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            String[] input = currentLine.split(" ");
            int[] maxs = new int[12];
            for (int i = 0; i < 12; i++) {
                maxs[i] = Integer.parseInt(input[i]);
            }
            int[][] star = Arrays.stream(STAR).map(int[]::clone).toArray(int[][]::new);
            Set<Integer>[][] overlap = new HashSet[8][11];
            fill(star, maxs, overlap);
            if (isValid(star, maxs)) {
                int max = sum(star);
                int min = min(star, overlap, maxs);
                System.out.println(min + " " + max);
            } else {
                System.out.println("NO SOLUTION");
            }
        }
    }
}

