import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class SummationOfFourPrimes {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private final List<Integer> primes;
    private final static int MAX_PRIMES = 10_000_000;

    SummationOfFourPrimes() {
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
        primes = new ArrayList<>();
        for (int i = 2; i < bits.length(); ++i) {
            if (!bits.get(i)) {
                primes.add(i);
            }
        }
    }

    public int[] find(int a, int c, int[] current) {
        if (a == 0 && c == -1) {
            return current;
        } else if (a < 0 || c == -1) {
            return null;
        }
        int startIndex = Collections.binarySearch(primes, a);
        startIndex = (startIndex < 0) ? Math.abs(startIndex + 1) : startIndex;
        startIndex = primes.size() - 1 >= startIndex ? startIndex
                : primes.size() - 1;
        for (int i = startIndex; i >= 0; --i) {
            current[c] = primes.get(i);
            int[] result = find(a - primes.get(i), c - 1, current);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public static String toString(int[] arr) {
        return Arrays.stream(arr).mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) throws IOException {
        SummationOfFourPrimes n = new SummationOfFourPrimes();
        String currentLine = null;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equals("")) {
            int[] res = n.find(Integer.parseInt(currentLine.trim()), 3,
                    new int[4]);
            System.out.println(res != null ? toString(res) : "Impossible.");
        }
    }
}

