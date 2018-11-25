import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.range;
import java.util.Map;

class AustralianVoting {
    private static final String EMPTY = "";
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));

    private static List<String> elect(List<String> candidates, 
            List<Deque<Integer>> ballots) {
        final int majority = ballots.size() / 2 + 1;
        final int[] counter = new int[candidates.size()];
        ballots.stream().map(Deque::peek).forEach(x -> counter[x]++);
        while (true) {
            Map<Integer, List<Integer>> result = range(0, candidates.size()).boxed()
                .filter(x -> counter[x] >= 0).collect(groupingBy(i -> counter[i], toList()));
            int max = result.keySet().stream().max(Integer::compareTo).get();
            int min = result.keySet().stream().min(Integer::compareTo).get();
            if (max >= majority || max == min) {
                return result.get(max).stream().map(x -> candidates.get(x)).collect(toList());
            }
            List<Integer> eliminated = result.get(min);
            eliminated.forEach(x -> counter[x] = -1);
            ballots.forEach(b -> {
                int first = b.peek();
                eliminated.forEach(x -> b.remove(x));
                counter[b.peek()] += (first != b.peek()) ? 1 : 0;
            });
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.valueOf(reader.readLine().trim());
        reader.readLine();
        for (int i = 0; i < n; ++i) {
            int count = Integer.valueOf(reader.readLine().trim());
            List<String> candidates = reader.lines().limit(count).collect(toList());
            List<Deque<Integer>> ballots = new ArrayList<Deque<Integer>>();
            String currentLine = EMPTY;
            while ((currentLine = reader.readLine()) != null && 
                    !currentLine.equalsIgnoreCase(EMPTY)) {
                ballots.add(new ArrayDeque<Integer>(stream(currentLine.trim().split(" "))
                        .filter(x -> !x.equals(EMPTY))
                        .map(Integer::parseInt).map(x -> x - 1).collect(toList())));
            }
            elect(candidates, ballots).forEach(System.out::println);
            if (i < n - 1) {
                System.out.println();
            }
        }
    }
}
