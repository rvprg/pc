package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

class Minesweeper {
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));
    private static final String INPUT_END = "0 0";
    private static final String SPACE = " ";
    private static final int[][] p = new int[][] {
            { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 0 }, 
            { 1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }
    };

    public static void main(String[] args) throws IOException {
        int lineNum = 0;
        String currentLine = INPUT_END;
        while (!(currentLine = reader.readLine().trim()).equalsIgnoreCase(INPUT_END)) {
            List<Integer> nm = stream(currentLine.split(SPACE)).map(Integer::parseInt).collect(toList());
            int n = nm.get(0);
            int m = nm.get(1);

            final int[] field = reader.lines().limit(m)
                .collect(joining()).chars().map(x -> x == '*' ? -1 : 0).toArray();

            final IntBinaryOperator mine = 
               (x, y) -> (x < 0 || x > (n - 1) || y < 0 || y > (m - 1)) ? 0 : field[x * m + y];

            final IntUnaryOperator count = (i) -> range(0, p.length)
                .map(j -> Math.abs(mine.applyAsInt(i / m + p[j][0], i % m + p[j][1]))).sum();

            int[] result = range(0, field.length)
                .map(x -> field[x] >= 0 ? count.applyAsInt(x) : field[x]).toArray();

            System.out.println("Field #" + (++lineNum) + ":");
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    System.out.print(result[i * m + j] == -1 ? "*" : result[i * m + j]);
                }
                System.out.println();
            }
        }
    }
}
