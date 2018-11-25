import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;

public class HowManyFibs {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<BigInteger> range = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals("")).map(BigInteger::new)
                    .collect(toList());
            if (range.get(0).equals(BigInteger.ZERO) &&
                    range.get(1).equals(BigInteger.ZERO)) {
                break;
            }
            BigInteger fn2 = BigInteger.ZERO;
            BigInteger fn1 = BigInteger.ONE;
            BigInteger fn = fn2.add(fn1);
            long counter = 0;
            while (fn.compareTo(range.get(1)) <= 0) {
                if (fn.compareTo(range.get(0)) >= 0) {
                    counter++;
                }
                fn2 = fn1;
                fn1 = fn;
                fn = fn1.add(fn2);
            }
            System.out.println(counter);
        }
    }
}

