import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TheSternBrocotNumberSystem {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private static String get(int a, int b) {
        int gcd = gcd(a, b);
        a = a / gcd;
        b = b / gcd;

        int[] l = new int[] { 0, 1 };
        int[] m = new int[] { 1, 1 };
        int[] r = new int[] { 1, 0 };

        StringBuilder result = new StringBuilder();
        while (true) {
            int cmp = Integer.compare(a * m[1], b * m[0]);
            if (cmp == -1) {
                r = new int[] { m[0], m[1] };
                m = new int[] { l[0] + m[0], l[1] + m[1] };
                result.append("L");
            } else if (cmp == 1) {
                l = new int[] { m[0], m[1] };
                m = new int[] { r[0] + m[0], r[1] + m[1] };
                result.append("R");
            } else {
                break;
            }
        }
        return result.toString();
    }

    private static List<Integer> readList(String input) {
        return stream(input.trim().split(" "))
                .filter(x -> !x.equals("")).map(Integer::parseInt)
                .collect(toList());
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<Integer> ab = readList(currentLine);
            if (ab.get(0) == 1 && ab.get(1) == 1) {
                break;
            }
            System.out.println(get(ab.get(0), ab.get(1)));
        }
    }
}
