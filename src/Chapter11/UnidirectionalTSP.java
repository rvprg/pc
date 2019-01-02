import java.io.IOException;
import java.util.Scanner;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class UnidirectionalTSP {
    private static class Cell {
        Cell next;
        int row;
        int value;
        int minPath;

        public Cell(int value, int row) {
            this.value = value;
            this.minPath = Integer.MAX_VALUE;
            this.row = row;
        }
    }

    final Cell[][] table;
    final int m;
    final int n;
    int minPath;
    int[] path;

    public UnidirectionalTSP(Cell[][] table, int m, int n) {
        this.table = table;
        this.m = m;
        this.n = n;
        this.path = new int[n];
        this.minPath = Integer.MAX_VALUE;
        solve();
    }

    private Cell get(int row, int col) {
        if (row > m - 1) {
            return table[0][col];
        } else if (row < 0) {
            return table[m - 1][col];
        }
        return table[row][col];
    }

    private void solve() {
        for (int row = 0; row < m; ++row) {
            Cell cell = get(row, n - 1);
            cell.minPath = cell.value;
            cell.next = null;
        }
        for (int col = n - 2; col >= 0; --col) {
            for (int row = 0; row < m; ++row) {
                Cell cell = get(row, col);
                int min = Integer.MAX_VALUE;
                for (int i = -1; i <= 1; ++i) {
                    Cell next = get(row + i, col + 1);
                    if (min > next.minPath) {
                        min = next.minPath;
                        cell.next = next;
                    } else if (min == next.minPath) {
                        if (cell.next == null || (cell.next != null && cell.next.row > next.row)) {
                            cell.next = next;
                        }
                    }
                }
                cell.minPath = min + cell.value;
            }
        }
        Cell cell = null;
        for (int row = 0; row < m; ++row) {
            Cell curr = get(row, 0);
            if (cell == null || (cell != null && curr.minPath < cell.minPath)) {
                cell = curr;
            }
        }

        this.minPath = cell.minPath;
        for (int col = 0; col < n; ++col) {
            path[col] = cell.row + 1;
            cell = cell.next;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        while (s.hasNextInt()) {
            int m = s.nextInt(); // rows
            int n = s.nextInt(); // cols
            Cell[][] table = new Cell[m][n];
            for (int i = 0; i < m * n; ++i) {
                table[i / n][i % n] = new Cell(s.nextInt(), i / n);
            }
            UnidirectionalTSP main = new UnidirectionalTSP(table, m, n);
            System.out.println(stream(main.path).mapToObj(String::valueOf).collect(joining(" ")));
            System.out.println(main.minPath);
        }
    }

}

