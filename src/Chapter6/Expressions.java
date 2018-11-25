import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Expressions {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final BigInteger[][] memo = new BigInteger[151][151];

    public static BigInteger c(int n, int d) {
        if (d == 1 || n == 0) {
            return BigInteger.ONE;
        }

        if (memo[n][d] != null) {
            return memo[n][d];
        }

        BigInteger v = BigInteger.ZERO;
        for (int i = 0; i <= n - 1; ++i) {
            BigInteger v1 = c(i, d - 1);
            BigInteger v2 = c(n - i - 1, d);
            v = v1.multiply(v2).add(v);
        }

        memo[n][d] = v;
        return v;
    }

    public static BigInteger solve(int n, int d) {
        if (n % 2 != 0) {
            return BigInteger.ZERO;
        }
        for (int i = 0; i < memo.length; ++i) {
            Arrays.fill(memo[i], null);
        }
        return c(n / 2, d).subtract(c(n / 2, d - 1));
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            List<Integer> input = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            System.out.println(solve(input.get(0), input.get(1)));
        }
    }
}

