import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BirthdayCake {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static int X = 0;
    private static int Y = 1;

    private static boolean isSplit(int[][] cherry, int x, int y) {
        int A = y;
        int B = -x;
        int left = 0;
        int right = 0;

        for (int i = 0; i < cherry.length; i++) {
            int[] a = cherry[i];
            int r = A * a[X] + B * a[Y];
            if (r == 0) {
                return false;
            } else if (r < 0) {
                left++;
            } else {
                right++;
            }
        }

        return left == right;
    }

    private static int[] solve(int[][] cherry) {
        for (int i = 0; i < cherry.length; i++) {
            for (int j = i + 1; j < cherry.length; j++) {
                int[] a = cherry[i];
                int[] b = cherry[j];

                double mx = (b[X] + a[X]) / 2.0;
                double my = (b[Y] + a[Y]) / 2.0;

                if (mx > 0) {
                    double k = my / mx;
                    for (int l = 1; l <= 500; l++) {
                        int x = l;
                        int y = (int) Math.round(x * k);
                        if (isSplit(cherry, x, y)) {
                            return new int[]{y, -x};
                        }
                    }
                } else if (mx < 0) {
                    double k = my / mx;
                    for (int l = -1; l >= -500; l--) {
                        int x = l;
                        int y = (int) Math.round(x * k);
                        if (isSplit(cherry, x, y)) {
                            return new int[]{y, -x};
                        }
                    }
                } else {
                    if (isSplit((cherry), 0, 1)) {
                        return new int[]{1, 0};
                    }
                }
            }
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        while (n != 0) {
            int[][] cherry = new int[2 * n][2];
            for (int i = 0; i < 2 * n; i++) {
                String[] c = reader.readLine().split(" ");
                int x = Integer.parseInt(c[0]);
                int y = Integer.parseInt(c[1]);
                cherry[i] = new int[]{x, y};
            }
            int[] solution = solve(cherry);
            System.out.println(solution[0] + " " + solution[1]);
            n = Integer.parseInt(reader.readLine());
        }
    }
}

