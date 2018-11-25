import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class Factovisors {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static boolean check(int n, int p, int k) {
        for (int i = p; i <= n && k > 0; i += p) {
            int m = i;
            while (m % p == 0) {
                m /= p;
                k--;
            }
        }
        return k <= 0;
    }

    public static boolean solve(int n, int m) {
        n = (n == 0) ? 1 : n;
        m = (m == 0) ? 1 : m;
        if (n >= m) {
            return true;
        }
        int k = 0;
        while (m % 2 == 0) {
            m /= 2;
            k++;
        }
        if (!check(n, 2, k)) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(m); i += 2) {
            k = 0;
            while (m % i == 0) {
                m /= i;
                k++;
            }
            if (!check(n, i, k)) {
                return false;
            }
        }
        return m <= n;
    }

    public static void main(String[] args) throws IOException {
        String currentLine = null;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equals("")) {
            List<Integer> input = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            boolean solution = solve(input.get(0), input.get(1));
            System.out.println(input.get(1) +
                    (solution ? " divides " : " does not divide ") +
                    input.get(0) + "!");
        }
    }
}

