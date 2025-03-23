import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[][] PATTERN = {
            {0, 1}, {-1, 1}, {-1, 0}, {0, -1}, {1, -1}, {1, 0}, {0, 1}
    };

    private static int[][] getLookup() {
        int[][] lookup = new int[100000][2];
        int x = 0;
        int y = 0;
        int l = 0;
        int c = 0;
        int i = 0;
        lookup[0] = new int[]{0, 0};
        while (c < 100000) {
            int m = i % 7;
            if (m == 0) ++l;
            int r;
            if (m == 0) {
                r = 1;
            } else if (m == 1) {
                r = l - 1;
            } else {
                r = l;
            }
            for (int j = 0; j < r; j++) {
                x += PATTERN[m][0];
                y += PATTERN[m][1];
                c++;
                if (c == 100000) return lookup;
                lookup[c] = new int[]{x, y};
            }
            i++;
        }
        return lookup;
    }

    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[][] lookup = getLookup();
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            int n = Integer.parseInt(currentLine) - 1;
            System.out.println(lookup[n][0] + " " + lookup[n][1]);
        }
    }
}

