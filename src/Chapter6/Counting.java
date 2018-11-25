import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Counting {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static BigInteger count(int n) {
        final BigInteger two = BigInteger.valueOf(2);
        if (n == 1) {
            return two;
        } else if (n == 0) {
            return BigInteger.ONE;
        } else if (n < 0) {
            return BigInteger.ZERO;
        }

        BigInteger[] prev = new BigInteger[] { BigInteger.ZERO, BigInteger.ONE,
                two };

        for (int i = 1; i < n; ++i) {
            BigInteger next = prev[2].multiply(two).add(prev[1]).add(prev[0]);
            prev[0] = prev[1];
            prev[1] = prev[2];
            prev[2] = next;
        }

        return prev[2];
    }

    public static void main(String[] args)
            throws NumberFormatException, IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            System.out.println(count(Integer.parseInt(currentLine.trim())));
        }
    }
}

