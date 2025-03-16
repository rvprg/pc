import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class TheMonocycle {
    private static int UP = 0;
    private static int RIGHT = 1;
    private static int DOWN = 2;
    private static int LEFT = 3;

    private static int[][] DIRECTION = {
            {0, -1}, {1, 0}, {0, 1}, {-1, 0}
    };

    private static int[][] TURNS = {
            {RIGHT, LEFT}, {UP, DOWN}, {RIGHT, LEFT}, {UP, DOWN}
    };

    private static class Cell {
        int x, y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class VisitedCell {
        int x, y;
        int d;
        int cost;
        int color;

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            VisitedCell that = (VisitedCell) o;
            return x == that.x && y == that.y && color == that.color && d == that.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, d, color);
        }

        public VisitedCell(int x, int y, int d, int cost, int color) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cost = cost;
            this.color = color;
        }
    }

    private static void visit(int x, int y, int d, int cost, int color, Queue<VisitedCell> queue, Set<VisitedCell> visited) {
        VisitedCell vn = new VisitedCell(x, y, d, cost, color);
        if (!visited.contains(vn)) {
            queue.add(vn);
            visited.add(vn);
        }
    }

    public static int search(Cell[][] grid, Cell source, Cell target) {
        Queue<VisitedCell> queue = new LinkedList<>();
        VisitedCell sourceCell = new VisitedCell(source.x, source.y, UP, 0, 0);
        queue.add(sourceCell);
        Set<VisitedCell> visited = new HashSet<>();
        visited.add(sourceCell);

        while (!queue.isEmpty()) {
            VisitedCell current = queue.poll();
            if (current.x == target.x && current.y == target.y && current.color == 0) {
                return current.cost;
            }
            Cell next = grid[current.y + DIRECTION[current.d][1]][current.x + DIRECTION[current.d][0]];
            if (next != null) {
                visit(next.x, next.y, current.d, current.cost + 1, (current.color + 1) % 5, queue, visited);
            }
            for (int direction: TURNS[current.d]) {
                visit(current.x, current.y, direction, current.cost + 1, current.color, queue, visited);
            }
        }
        return -1;
    }

    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String currentLine;
        int caseNum = 0;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            String[] dimensions = currentLine.split(" ");
            int h = Integer.parseInt(dimensions[0]);
            int w = Integer.parseInt(dimensions[1]);
            if (h == 0 && w == 0) {
                break;
            }
            if (caseNum > 0) {
                System.out.println();
            }
            System.out.println("Case #" + (++caseNum));
            Cell start = null;
            Cell end = null;
            Cell[][] grid = new Cell[h + 2][w + 2];
            for (int i = 0; i < h; i++) {
                currentLine = "#" + reader.readLine() + "#";
                for (int j = 0; j < currentLine.length(); ++j) {
                    if (currentLine.charAt(j) == '#') {
                        grid[i + 1][j] = null;
                    } else {
                        Cell cell = new Cell(j, i + 1);
                        grid[i + 1][j] = cell;
                        if (currentLine.charAt(j) == 'S') {
                            start = cell;
                        } else if (currentLine.charAt(j) == 'T') {
                            end = cell;
                        }
                    }
                }
            }

            int cost = search(grid, start, end);
            if (cost > -1) {
                System.out.println("minimum time = " + cost + " sec");
            } else {
                System.out.println("destination not reachable");
            }
        }
    }
}

