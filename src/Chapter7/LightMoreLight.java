import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

class LightMoreLight {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private final List<Long> primes;
    private final static int MAX_PRIMES = 70000;

    LightMoreLight() {
        BitSet bits = new BitSet(MAX_PRIMES);
        for (int i = 2; i < Math.sqrt(MAX_PRIMES); ++i) {
            if (!bits.get(i)) {
                int k = 0;
                int ii = i * i;
                int j = ii + k * i;
                while (j < MAX_PRIMES) {
                    bits.set(j);
                    k++;
                    j = ii + k * i;
                }
            }
        }
        primes = new ArrayList<Long>();
        for (int i = 2; i < bits.length(); ++i) {
            if (!bits.get(i)) {
                primes.add((long) i);
            }
        }
    }

    public long calculate(long value) {
        List<Long> factors = new ArrayList<Long>();
        for (int i = 0; i < primes.size() && value > 1 &&
                (primes.get(i) * primes.get(i)) <= value; ++i) {
            long p = 0;
            while (value % primes.get(i) == 0) {
                value /= primes.get(i);
                p++;
            }
            if (p > 0) {
                factors.add(p);
            }
        }
        if (value > 1) {
            factors.add(1L);
        }
        return factors.stream().map(x -> x + 1).reduce(1L, (a, b) -> a * b);
    }

    public static void main(String[] args) throws IOException {
        LightMoreLight l = new LightMoreLight();
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equals("0")) {
            long value = Long.parseLong(currentLine.trim());
            System.out.println(l.calculate(value) % 2 == 0 ? "no" : "yes");
        }
    }
}
