import java.util.Scanner;

public class CuttingSticks {
    private final int[] cuts;
    private final int length;
    private final Integer[][] memo = new Integer[1000][1000];

    public CuttingSticks(int[] cuts, int length) {
        this.cuts = cuts;
        this.length = length;
    }

    public int solve() {
        return solve(0, length);
    }

    private int solve(int start, int end) {
        if (memo[start][end] != null) {
            return memo[start][end];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cuts.length; ++i) {
            if (cuts[i] > start && cuts[i] < end) {
                min = Math.min(min, solve(start, cuts[i]) + solve(cuts[i], end));
            }
        }
        memo[start][end] = min < Integer.MAX_VALUE ? (end - start) + min : 0;
        return memo[start][end];
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            int length = s.nextInt();
            if (length == 0) {
                break;
            }
            int n = s.nextInt();
            int[] cuts = new int[n];
            for (int i = 0; i < n; ++i) {
                cuts[i] = s.nextInt();
            }
            CuttingSticks solver = new CuttingSticks(cuts, length);
            System.out.println(String.format("The minimum cutting is %d.", solver.solve()));
        }
    }
}

