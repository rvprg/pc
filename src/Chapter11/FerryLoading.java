import java.util.*;

public class FerryLoading {
    private final List<Integer> queue;
    private final int ferryLength;
    private final Map<Integer, Map<Integer, Map<Integer, Deque<Boolean>>>> memo = new HashMap<>();

    public FerryLoading(int ferryLength, List<Integer> queue) {
        this.ferryLength = ferryLength * 100;
        this.queue = queue;
    }

    private Deque<Boolean> solve() {
        return solve(0, new ArrayDeque<>(), 0, 0);
    }

    private Deque<Boolean> solve(int i, Deque<Boolean> solution, int leftLength, int rightLength) {
        if (i > queue.size() - 1) {
            return new ArrayDeque<>(solution);
        }
        Integer next = queue.get(i);
        int min = Math.min(leftLength, rightLength);
        int max = Math.max(leftLength, rightLength);

        if (memo.get(min) != null) {
            Map<Integer, Deque<Boolean>> m = memo.get(min).get(max);
            if (m != null && m.containsKey(i)) {
                return m.get(i);
            }
        }

        Deque<Boolean> leftSolution = null;
        if (next + leftLength <= ferryLength) {
            solution.addLast(true);
            leftSolution = solve(i + 1, solution, next + leftLength, rightLength);
            solution.removeLast();
        }

        Deque<Boolean> rightSolution = null;
        if (next + rightLength <= ferryLength) {
            solution.addLast(false);
            rightSolution = solve(i + 1, solution, leftLength, next + rightLength);
            solution.removeLast();
        }

        Deque<Boolean> finalSolution = null;
        if (leftSolution == null && rightSolution == null) {
            finalSolution = new ArrayDeque<>(solution);
        } else if (leftSolution == null || rightSolution == null) {
            finalSolution = leftSolution == null ? rightSolution : leftSolution;
        } else {
            finalSolution = leftSolution.size() > rightSolution.size() ? leftSolution : rightSolution;
        }

        memo.putIfAbsent(min, new HashMap<>());
        memo.get(min).putIfAbsent(max, new HashMap<>());
        memo.get(min).get(max).putIfAbsent(i, finalSolution);
        return finalSolution;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            List<Integer> queue = new ArrayList<>();
            int ferry = s.nextInt();
            int k;
            while ((k = s.nextInt()) != 0) {
                queue.add(k);
            }
            FerryLoading solver = new FerryLoading(ferry, queue);
            Deque<Boolean> solution = solver.solve();
            System.out.println(solution.size());
            solution.stream().map(x -> x ? "starboard" : "port").forEach(System.out::println);
            if (i < n - 1) {
                System.out.println();
            }
        }
    }
}

