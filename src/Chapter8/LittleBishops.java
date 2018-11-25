import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class LittleBishops {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private final int[][] attack;
    private final int n;
    private final int totalCount;

    public LittleBishops(int n, int k) {
        this.attack = new int[n][n];
        this.n = n;
        if (k > 0) {
            int total = 0;
            for (int i = 1; i < k; ++i) {
                int v = getCount(0, i);
                if (v > 0) {
                    total += v * getCount(1, k - i);
                }
            }
            this.totalCount = total + getCount(0, k) + getCount(1, k);
        } else {
            this.totalCount = 1;
        }
    }

    private int getCount(int s, int k) {
        AtomicInteger counter = new AtomicInteger(0);
        count(s, k, counter);
        return counter.get();
    }

    private void attack(int i, int j, int d) {
        while (i + 1 < n && j + 1 < n) {
            attack[++i][++j] += d;
        }
    }

    public void count(int s, int k, AtomicInteger counter) {
        if (k == 0) {
            counter.incrementAndGet();
            return;
        }

        for (int i = s; i <= 2 * n - 1; i += 2) {
            int ii = i >= n ? n * (i - n + 2) - 1 : i;
            int squares = i >= n ? 2 * n - i - 1 : i + 1;
            for (int j = ii, num = 0; num < squares; j += (n - 1), ++num) {
                int x = j / n;
                int y = j % n;
                if (attack[x][y] > 0) {
                    continue;
                }
                attack(x, y, 1);
                count(i + 2, k - 1, counter);
                attack(x, y, -1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int[][] memo = new int[9][65];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 65; ++j) {
                memo[i][j] = -1;
            }
        }

        String currentLine = null;
        while ((currentLine = reader.readLine()) != null) {
            List<Integer> input = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            if (input.get(0) == 0 && input.get(1) == 0) {
                break;
            }
            int i = input.get(0);
            int j = input.get(1);
            if (memo[i][j] == -1) {
                LittleBishops s = new LittleBishops(i, j);
                memo[i][j] = s.totalCount;
            }
            System.out.println(memo[i][j]);
        }
    }
}

