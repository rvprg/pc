package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GraphicalEditor {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private int[][] canvas;
    private int m = 0, n = 0;

    private void clear() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                canvas[j][i] = 'O';
            }
        }
    }

    private void execute(List<String> command) {
        int x, y1, y2, y, x1, x2, c;
        switch (command.get(0)) {
        case "I":
            m = Integer.parseInt(command.get(1));
            n = Integer.parseInt(command.get(2));
            canvas = new int[m][n];
            clear();
            break;
        case "C":
            clear();
            break;
        case "L":
            x = Integer.parseInt(command.get(1)) - 1;
            y = Integer.parseInt(command.get(2)) - 1;
            canvas[x][y] = command.get(3).charAt(0);
            break;
        case "V":
            x = Integer.parseInt(command.get(1)) - 1;
            y1 = Integer.parseInt(command.get(2)) - 1;
            y2 = Integer.parseInt(command.get(3)) - 1;
            c = command.get(4).charAt(0);
            for (y = Math.min(y1, y2); y <= Math.max(y1, y2); ++y) {
                canvas[x][y] = c;
            }
            break;
        case "H":
            x1 = Integer.parseInt(command.get(1)) - 1;
            x2 = Integer.parseInt(command.get(2)) - 1;
            y = Integer.parseInt(command.get(3)) - 1;
            c = command.get(4).charAt(0);
            for (x = Math.min(x1, x2); x <= Math.max(x1, x2); ++x) {
                canvas[x][y] = c;
            }
            break;
        case "K":
            x1 = Integer.parseInt(command.get(1)) - 1;
            y1 = Integer.parseInt(command.get(2)) - 1;
            x2 = Integer.parseInt(command.get(3)) - 1;
            y2 = Integer.parseInt(command.get(4)) - 1;
            c = command.get(5).charAt(0);
            for (x = x1; x <= x2; ++x) {
                for (y = y1; y <= y2; ++y) {
                    canvas[x][y] = c;
                }
            }
            break;
        case "F":
            x = Integer.parseInt(command.get(1)) - 1;
            y = Integer.parseInt(command.get(2)) - 1;
            int newColor = command.get(3).charAt(0);
            int oldColor = canvas[x][y];
            fill(new Point(x, y), oldColor, newColor);
            break;
        case "S":
            String name = command.get(1);
            System.out.println(name);
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    System.out.print((char) canvas[j][i]);
                }
                System.out.println();
            }
            break;
        default:
            break;
        }
    }

    private static class Point {
        final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void fill(Point pt, int oldColor, int newColor) {
        if (canvas[pt.x][pt.y] != oldColor || oldColor == newColor) {
            return;
        }
        Deque<Point> q = new ArrayDeque<>();
        q.addLast(pt);
        canvas[pt.x][pt.y] = newColor;
        while (!q.isEmpty()) {
            pt = q.pop();
            if (pt.x + 1 < m && canvas[pt.x + 1][pt.y] == oldColor) {
                canvas[pt.x + 1][pt.y] = newColor;
                q.addLast(new Point(pt.x + 1, pt.y));
            }
            if (pt.x - 1 >= 0 && canvas[pt.x - 1][pt.y] == oldColor) {
                canvas[pt.x - 1][pt.y] = newColor;
                q.addLast(new Point(pt.x - 1, pt.y));
            }
            if (pt.y + 1 < n && canvas[pt.x][pt.y + 1] == oldColor) {
                canvas[pt.x][pt.y + 1] = newColor;
                q.addLast(new Point(pt.x, pt.y + 1));
            }
            if (pt.y - 1 >= 0 && canvas[pt.x][pt.y - 1] == oldColor) {
                canvas[pt.x][pt.y - 1] = newColor;
                q.addLast(new Point(pt.x, pt.y - 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GraphicalEditor editor = new GraphicalEditor();
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<String> command = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals("")).collect(toList());
            if (command.get(0).equalsIgnoreCase("X")) {
                break;
            }
            editor.execute(command);
        }
    }
}
