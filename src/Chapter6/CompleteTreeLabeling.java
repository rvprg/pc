import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CompleteTreeLabeling {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static List<BigInteger> expand(int n, int upto) {
        List<BigInteger> res = new ArrayList<BigInteger>();
        for (int i = n; i > upto; --i) {
            res.add(BigInteger.valueOf(i));
        }
        return res;
    }

    private static BigInteger mult(List<BigInteger> l) {
        return l.stream().reduce(BigInteger.ONE, (x, y) -> x.multiply(y));
    }

    private static BigInteger choose(int n, int k) {
        List<BigInteger> numerator = expand(n, (n - k));
        List<BigInteger> denominator = expand(k, 0);
        Iterator<BigInteger> it = denominator.iterator();
        while (it.hasNext()) {
            if (numerator.remove(it.next())) {
                it.remove();
            }
        }
        return mult(numerator).divide(mult(denominator));
    }

    private static BigInteger count(BigInteger base, int treeSize, int k,
            int h) {
        if (h == 1) {
            return base;
        }
        int subtreeSize = (treeSize - 1) / k;
        BigInteger subtreeCount = count(base, subtreeSize, k, h - 1);
        BigInteger count = base;
        for (int i = subtreeSize - 1; i <= treeSize - 2; i += subtreeSize) {
            count = count.multiply(subtreeCount)
                    .multiply(choose(i, subtreeSize - 1));
        }
        return count;
    }

    private static BigInteger solve(int k, int h) {
        if (k == 1) {
            return BigInteger.ONE;
        }
        int pow = 1;
        for (int i = 0; i < h + 1; ++i) {
            pow *= k;
        }
        return count(mult(expand(k, 0)), (pow - 1) / (k - 1), k, h);
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<Integer> input = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            System.out.println(solve(input.get(0), input.get(1)));
        }
    }
}

