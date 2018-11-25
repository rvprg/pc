import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TugOfWar {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static int[] solve(int[] arr) {
        int n = arr.length;
        int sum = Arrays.stream(arr).sum();
        long[] m = new long[sum + 1];
        m[0] = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = sum - arr[i]; j >= 0; --j) {
                m[j + arr[i]] |= m[j] << 1L;
            }
        }

        for (int i = sum / 2; i >= 0; --i) {
            if (n % 2 == 0) {
                if ((m[i] & 1L << (n / 2)) > 0) {
                    return new int[] { i, sum - i };
                }
            } else {
                if ((m[i] & 1L << (n / 2)) > 0 ||
                        (m[i] & 1L << (n / 2 + 1)) > 0) {
                    return new int[] { i, sum - i };
                }
            }
        }

        return null;
    }

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(reader.readLine().trim());
        reader.readLine();
        for (int i = 0; i < n; ++i) {
            int m = Integer.parseInt(reader.readLine().trim());
            int[] arr = new int[m];
            for (int j = 0; j < m; ++j) {
                arr[j] = Integer.parseInt(reader.readLine().trim());
            }
            int[] s = solve(arr);
            System.out.println(s[0] + " " + s[1]);
            if (i < n - 1) {
                System.out.println();
                reader.readLine();
            }
        }
    }

}

