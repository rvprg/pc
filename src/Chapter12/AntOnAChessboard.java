import java.util.Scanner;

public class AntOnAChessboard {
    private static long[] get(long v) {
        long k = (long) Math.ceil(Math.sqrt(v));
        long m = k * k - k + 1;
        boolean isOdd = k % 2 == 1;
        if (v == m) {
            return new long[]{k, k};
        } else if (v >= m) {
            if (isOdd) {
                return new long[]{k * k - v + 1, k};
            }
            return new long[]{k, k * k - v + 1};
        } else {
            if (isOdd) {
                return new long[]{k, k - (m - v)};
            }
            return new long[]{k - (m - v), k};
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            long v = s.nextLong();
            if (v == 0) {
                break;
            }
            long[] ans = get(v);
            System.out.println(String.format("%d %d", ans[0], ans[1]));
        }
    }
}

