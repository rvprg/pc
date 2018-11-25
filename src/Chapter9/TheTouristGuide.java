import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TheTouristGuide {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static int solve(int[][] w, int size, int s, int e,
            int t) {
        int[][] d = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                d[i][j] = w[i][j];
            }
        }
        for (int i = 0; i < size; i++) {
            d[i][i] = 0;
        }
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    d[i][j] = Math.max(d[i][j], Math.min(d[i][k], d[k][j]));
                }
            }
        }
        return d[s][e];
    }

    private static List<Integer> parseLine(String line) {
        return stream(line.trim().split(" "))
                .filter(x -> !x.equals(""))
                .map(Integer::parseInt)
                .collect(toList());
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        int scenario = 1;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            List<Integer> nr = parseLine(currentLine);
            if (nr.get(0) == 0 && nr.get(1) == 0) {
                break;
            }
            int size = nr.get(0) + 1;
            int[][] w = new int[size][size];
            for (int i = 0; i < nr.get(1); ++i) {
                List<Integer> node = parseLine(reader.readLine());
                w[node.get(0)][node.get(1)] = node.get(2);
                w[node.get(1)][node.get(0)] = node.get(2);
            }
            List<Integer> sdt = parseLine(reader.readLine());
            int m = solve(w, size, sdt.get(0), sdt.get(1),
                    sdt.get(2));
            int c = (sdt.get(2)) / (m - 1);
            if (sdt.get(2) % (m - 1) != 0) {
                c++;
            }
            System.out.println("Scenario #" + scenario);
            System.out.println("Minimum Number of Trips = " + c);
            System.out.println();
            scenario++;
        }
    }
}

