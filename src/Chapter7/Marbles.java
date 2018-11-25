import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class Marbles {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static long[] euclid(long a, long b) {
        long s = 0;
        long old_s = 1;
        long t = 1;
        long old_t = 0;
        long r = b;
        long old_r = a;
        while (r != 0) {
            long quotient = old_r / r;
            long p = r;
            r = old_r - quotient * r;
            old_r = p;
            p = s;
            s = old_s - quotient * s;
            old_s = p;
            p = t;
            t = old_t - quotient * t;
            old_t = p;
        }
        return new long[] { old_s, old_t, old_r };
    }

    public static long[] diophant(long a, long b, long c) {
        long[] bezouts = euclid(a, b);
        if (c % bezouts[2] != 0) {
            return null;
        }
        long e = c / bezouts[2];
        return new long[] { e * bezouts[0], b / bezouts[2], e * bezouts[1],
                -a / bezouts[2] };
    }

    public static long[] min(long c1, long c2, long[] solution, long start,
            long end) {
        long[] min = new long[] { Long.MAX_VALUE, 0, 0 };
        for (long i = start; i <= end; ++i) {
            long x = solution[0] + i * solution[1];
            long y = solution[2] + i * solution[3];
            if (x < 0 || y < 0) {
                continue;
            }
            long cost = c1 * x + c2 * y;
            if (cost < min[0]) {
                min[0] = cost;
                min[1] = x;
                min[2] = y;
            }
        }
        return min;
    }

    public static long[] solve(long c1, long n1, long c2, long n2, long n) {
        long[] solution = diophant(n1, n2, n);
        if (solution == null) {
            return null;
        }
        long left = -(solution[0] / solution[1]);
        long right = (solution[2] / -solution[3]);
        long[] minLeft = min(c1, c2, solution, left, left + 1);
        long[] minRight = min(c1, c2, solution, right - 1, right);
        long[] min = minLeft[0] < minRight[0] ? minLeft : minRight;
        return min[0] < Long.MAX_VALUE ? min : null;
    }

    public static List<Long> parse(String line) {
        return stream(line.trim().split(" "))
                .filter(x -> !x.equals(""))
                .map(Long::parseLong)
                .collect(toList());
    }

    public static void main(String[] args) throws IOException {
        String currentLine = null;
        while ((currentLine = reader.readLine()) != null) {
            long c = Long.parseLong(currentLine);
            if (c == 0) {
                break;
            }
            List<Long> cn1 = parse(reader.readLine());
            List<Long> cn2 = parse(reader.readLine());
            long[] solution = solve(cn1.get(0), cn1.get(1), cn2.get(0),
                    cn2.get(1), c);
            System.out
                    .println(solution == null ? "failed"
                            : solution[1] + " " + solution[2]);
        }

    }
}

