import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class Steps {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static long getLength(long limit) {
        long n = 1;
        while (n * (1 + n) <= limit) {
            n++;
        }
        if (n * (1 + n) > limit) {
            n--;
        }
        return n;
    }

    public static long getSteps(long n, long limit) {
        long sum = n * (1 + n);
        long steps = n * 2;
        long i = n;
        short j = 0;

        while (sum > limit) {
            sum -= i;
            steps--;
            if (j == 1) {
                j = 0;
                i--;
            }
            j++;
        }

        while (sum < limit) {
            while (sum + i <= limit) {
                sum += i;
                steps++;
            }
            if (i > 1) {
                i--;
            }
        }
        return steps;
    }

    public static long solve(long x, long y) {
        long limit = y - x;
        if (limit <= 1) {
            return limit;
        }
        long len = getLength(limit);
        return Math.min(getSteps(len, limit), getSteps(len + 1, limit));
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            List<Integer> input = stream(reader.readLine().trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            System.out.println(solve(input.get(0), input.get(1)));
        }
    }
}

