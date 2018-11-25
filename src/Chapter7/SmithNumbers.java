import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class SmithNumbers {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static int digitsSum(int value) {
        int sum = 0;
        while (value > 9) {
            sum += (value % 10);
            value /= 10;
        }
        return sum + value;
    }

    public static List<Integer> factor(int m) {
        List<Integer> factors = new ArrayList<>();
        if (m <= 2) {
            factors.add(m);
            return factors;
        }
        while (m % 2 == 0) {
            m /= 2;
            factors.add(2);
        }
        for (int i = 3; i <= Math.sqrt(m); i += 2) {
            while (m % i == 0) {
                m /= i;
                factors.add(i);
            }
        }
        if (m > 1) {
            factors.add(m);
        }
        return factors;
    }

    public static int find(int m) {
        int i = m + 1;
        while (true) {
            List<Integer> factors = factor(i);
            if (factors.size() > 1) {
                if (factors.stream().map(SmithNumbers::digitsSum).reduce(Integer::sum)
                        .get() == digitsSum(i)) {
                    return i;
                }
            }
            ++i;
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            int v = Integer.parseInt(reader.readLine().trim());
            System.out.println(find(v));
        }
    }
}

