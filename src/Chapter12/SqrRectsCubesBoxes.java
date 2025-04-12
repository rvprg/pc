import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SqrRectsCubesBoxes {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static long[] calculate(int n) {
        long[] res = new long[]{0, 0, 0, 0, 0, 0};
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                int ij = i * j;
                if (i <= n && j <= n) {
                    if (i == j) {
                        res[0] += ij;
                    } else {
                        res[1] += ij * 2;
                    }
                }
                for (int k = j; k <= n; k++) {
                    int ijk = ij * k;
                    if (i <= n && j <= n && k <= n) {
                        boolean a = i == j;
                        boolean b = j == k;
                        if (a && b) {
                            res[2] += ijk;
                        } else if (a && !b || !a && b ) {
                            res[3] += ijk * 3;
                        } else {
                            res[3] += ijk * 6;
                        }
                    }
                    for (int l = k; l <= n; l++) {
                        boolean a = i == j;
                        boolean b = j == k;
                        boolean c = k == l;
                        if (i == j && j == k && k == l) {
                            res[4] += ijk * l;
                        } else {
                            long f = 0;
                            if (a && !b && !c || !a && b && !c || !a && !b && c) {
                                f = 12;
                            } else if (a && b && !c || !a && b && c) {
                                f = 4;
                            } else if (!a && !b && !c) {
                                f = 24;
                            } else {
                                f = 6;
                            }
                            res[5] += ijk * l * f;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        long[][] lookup = new long[101][6];
        for (int i = 0; i <= 100; i++) {
            lookup[i] = calculate(i);
        }
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            int n = Integer.parseInt(currentLine);
            long[] res = lookup[n];
            System.out.println(String.format(
                "%d %d %d %d %d %d", res[0], res[1], res[2], res[3], res[4], res[5]));
        }
    }
}

