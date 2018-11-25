import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WheresWaldorf {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private final int n;
    private final int m;
    private final char[][] table;

    public WheresWaldorf(int n, int m, char[][] table) {
        this.n = n;
        this.m = m;
        this.table = table;
    }

    private static final int[][] dir = new int[][] {
            { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 },
            { -1, 1 }, { 1, -1 }
    };

    public int[] find(String word) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < dir.length; ++k) {
                    if (check(dir[k][0], dir[k][1], i, j, word)) {
                        return new int[] { i + 1, j + 1 };
                    }
                }
            }
        }
        return null;
    }

    private boolean check(int di, int dj, int i, int j, String word) {
        if (word.length() == 1) {
            return table[i][j] == word.charAt(0);
        }
        int pos = 0;
        while (i >= 0 && i < n && j >= 0 && j < m && pos < word.length() &&
                table[i][j] == word.charAt(pos)) {
            j += dj;
            i += di;
            pos++;
        }
        return pos == word.length();
    }

    private static String toString(int[] arr) {
        return Arrays.stream(arr).mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) throws IOException {
        int cases = Integer.parseInt(reader.readLine().trim());
        reader.readLine();
        for (int k = 0; k < cases; ++k) {
            List<Integer> nm = stream(reader.readLine().trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            char[][] table = new char[nm.get(0)][nm.get(1)];
            for (int i = 0; i < nm.get(0); ++i) {
                String currentLine = reader.readLine();
                for (int j = 0; j < nm.get(1); ++j) {
                    table[i][j] = currentLine.toLowerCase().charAt(j);
                }
            }
            int wordsCount = Integer.parseInt(reader.readLine().trim());
            List<String> words = new ArrayList<>();
            for (int i = 0; i < wordsCount; ++i) {
                String currentLine = reader.readLine().toLowerCase();
                words.add(currentLine);
            }
            WheresWaldorf ww = new WheresWaldorf(nm.get(0), nm.get(1), table);
            words.forEach(x -> System.out.println(toString(ww.find(x))));
            if (k < cases - 1) {
                System.out.println();
                reader.readLine();
            }
        }
    }
}
