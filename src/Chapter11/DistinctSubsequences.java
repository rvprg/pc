import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    private BigInteger slowRecursive(String line, String subs) {
        BigInteger[][] memo = new BigInteger[line.length()+1][subs.length()+1];
        return slowRecursive(line, subs, memo);
    }

    private BigInteger slowRecursive(String line, String subs, BigInteger[][] memo) {
        BigInteger count = BigInteger.ZERO;

        if (memo[line.length()][subs.length()] != null) {
            return memo[line.length()][subs.length()];
        }

        for (int i = line.length() - 1; i >= 0; --i) {
            if (line.charAt(i) == subs.charAt(subs.length() - 1)) {
                if (subs.length() == 1) {
                    count = count.add(BigInteger.ONE);
                } else {
                    count = count.add(slowRecursive(line.substring(0, i),
                            subs.substring(0, subs.length() - 1), memo));
                }
            }
        }

        memo[line.length()][subs.length()] = count;
        return count;
    }

    private BigInteger fastTabular(String line, String subs) {
        BigInteger[][] memo = new BigInteger[line.length() + 1][subs.length() + 1];
        for (int i = 0; i <= subs.length(); ++i) {
            memo[0][i] = BigInteger.ZERO;
        }
        for (int i = 0; i <= line.length(); ++i) {
            memo[i][0] = BigInteger.ZERO;
        }
        for (int i = 1; i <= subs.length(); ++i) {
            for (int j = 1; j <= line.length(); ++j) {
                if (line.charAt(j - 1) == subs.charAt(i - 1)) {
                    if (i == 1) {
                        memo[j][i] = memo[j - 1][i].add(BigInteger.ONE);
                    } else {
                        memo[j][i] = memo[j - 1][i].add(memo[j - 1][i - 1]);
                    }
                } else {
                    memo[j][i] = memo[j - 1][i];
                }
            }
        }
        return memo[line.length()][subs.length()];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            String line = reader.readLine().trim();
            String subs = reader.readLine().trim();
            Main solver = new Main();
            //System.out.println(solver.slowRecursive(line, subs));
            System.out.println(solver.fastTabular(line, subs));
        }
    }
}

