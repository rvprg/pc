import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Robbery {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final Boolean HIDDEN = Boolean.FALSE;
    private static final Boolean VISIBLE = Boolean.TRUE;
    private static int[][] DIRECTION = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static boolean inCity(int x, int y, Boolean[][][] city) {
        return x >= 1 && x < city[0].length && y >= 1 && y < city.length;
    }

    private static Boolean visibility(Boolean[][][] city, int x, int y, int time) {
        if (!inCity(x, y, city)) {
            return VISIBLE;
        }
        if (city[y][x][time] != null) {
            return city[y][x][time];
        }
        if (time == 1) {
            city[y][x][time] = HIDDEN;
            return city[y][x][time];
        }

        boolean hidden = false;
        for (int i = 0; i < DIRECTION.length; i++) {
            boolean isHidden = visibility(
                        city, 
                        x + DIRECTION[i][0], 
                        y + DIRECTION[i][1], 
                        time - 1) == HIDDEN;
            hidden |= isHidden;
        }

        city[y][x][time] = hidden ? HIDDEN : VISIBLE;
        return city[y][x][time];
    }

    private static int[] findUniqueLocation(Boolean[][][] city, int time) {
        List<int[]> locations = new ArrayList<>();
        for (int y = 1; y < city.length; y++) {
            for (int x = 1; x < city[0].length; x++) {
                if (city[y][x][time] == HIDDEN) {
                    locations.add(new int[]{x, y});
                }
            }
        }
        return locations.size() == 1 ? locations.get(0) : null;
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        int count = 0;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.isEmpty()) {
                continue;
            }
            Integer[] input = Arrays.stream(
                currentLine.trim().split(" ")).map(Integer::parseInt).toArray(l -> new Integer[l]);
            int w = input[0];
            int h = input[1];
            int t = input[2];
            if (w == 0 && h == 0 && t == 0) break;
            System.out.println("Robbery #" + (++count) + ":");
            Boolean[][][] city = new Boolean[h + 1][w + 1][t + 1]; // h x w x t
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                Integer[] m = Arrays.stream(
                    reader.readLine().trim().split(" "))
                        .map(Integer::parseInt).toArray(l -> new Integer[l]);
                // 0 time 1 left 2 top 3 right 4 bottom
                for (int x = m[1]; x <= m[3]; x++) {
                    for (int y = m[2]; y <= m[4]; y++) {
                        city[y][x][m[0]] = VISIBLE;
                    }
                }
            }

            boolean escaped = true;
            for (int y = 1; y <= h; y++) {
                for (int x = 1; x <= w; x++) {
                    if (visibility(city, x, y, t) == HIDDEN) {
                        escaped = false;
                    }
                }
            }

            if (escaped) {
                System.out.println("The robber has escaped.");
            } else {
                List<String> output = new ArrayList<>();
                for (int time = 1; time <= t; time++) {
                    int[] loc = findUniqueLocation(city, time);
                    if (loc != null) {
                        output.add("Time step " + time + 
                            ": The robber has been at " + loc[0] + "," + loc[1] + ".");
                    }
                }
                if (output.isEmpty()) {
                    System.out.println("Nothing known.");
                } else {
                    output.forEach(System.out::println);
                }
            }
            System.out.println();
        }
    }
}

