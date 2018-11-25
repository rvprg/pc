import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HanoiTowerTroublesAgain {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static boolean isSquare(int v) {
        int i = (int) Math.sqrt(v);
        return i * i == v;
    }

    public static int solve(int m) {
        int j = 0;
        int i = 0;
        int[] v = new int[m];
        while (i < m) {
            j++;
            for (i = 0; i < m; ++i) {
                if (v[i] == 0 || isSquare(v[i] + j)) {
                    v[i] = j;
                    break;
                }
            }
        }
        return j - 1;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            int m = Integer.parseInt(reader.readLine().trim());
            System.out.println(solve(m));
        }
    }

}

