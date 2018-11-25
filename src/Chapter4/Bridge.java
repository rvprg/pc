import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiConsumer;
import java.util.stream.Stream;


class Bridge {
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));
    private static final int LEFT_RIGHT = 0;
    private static final int RIGHT_LEFT = 1;

    private static void printResult(final List<List<Integer>> result) {
        List<Integer> lr = result.get(LEFT_RIGHT);
        List<Integer> rl = result.get(RIGHT_LEFT);
        if (lr.size() == 1) {
            System.out.println(lr.get(0));
            System.out.println(lr.get(0));
            return;
        }

        int totalTime = range(0, lr.size()).filter(x -> (x + 1) % 2 == 0)
                .map(x -> lr.get(x)).sum() +
                rl.stream().mapToInt(Integer::intValue).sum();
        System.out.println(totalTime);
        Stream.iterate(0, i -> i + 2).limit(lr.size() / 2).forEachOrdered(i -> {
            System.out.println(lr.get(i) + " " + lr.get(i + 1));
            if (i / 2 < rl.size()) {
                System.out.println(rl.get(i / 2));
            }
        });
    }

    private static List<List<Integer>> getStrategy(List<Integer> input) {
        final List<List<Integer>> output = Arrays
                        .asList(new ArrayList<Integer>(), new ArrayList<Integer>());

        if (input.size() == 1) {
            output.get(LEFT_RIGHT).add(input.get(0));
            return output;
        }

        final PriorityQueue<Integer> left = new PriorityQueue<>();
        final PriorityQueue<Integer> right = new PriorityQueue<>();
        final BiConsumer<PriorityQueue<Integer>, PriorityQueue<Integer>> move = (
                from, to) -> {
            if (!from.isEmpty()) {
                Integer v = from.remove();
                to.add(v);
                output.get(from == left ? LEFT_RIGHT : RIGHT_LEFT).add(v);
            }
        };

        left.addAll(input);

        while (!left.isEmpty()) {
            move.accept(right, left);
            move.andThen(move).accept(left, right);
            if (left.isEmpty()) {
                break;
            }

            move.accept(right, left);
            if (left.size() == 2) {
                move.andThen(move).accept(left, right);
                break;
            }

            Integer x1 = left.remove();
            Integer x2 = right.peek();
            Integer x4 = left.stream().max(Integer::compareTo).get();
            left.remove(x4);
            Integer x3 = left.stream().max(Integer::compareTo).get();
            left.remove(x3);
            int[] x = (2 * x2 <= x1 + x3) ? new int[] { x1, x3, x4 }
                    : new int[] { x4, x1, x3 };

            left.add(x[0]);
            output.get(LEFT_RIGHT).add(x[1]);
            output.get(LEFT_RIGHT).add(x[2]);
            right.add(x[1]);
            right.add(x[2]);
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.valueOf(reader.readLine().trim());
        reader.readLine();
        for (int i = 0; i < n; ++i) {
            int count = Integer.valueOf(reader.readLine().trim());
            List<Integer> input = reader.lines().map(String::trim)
                .limit(count).map(Integer::parseInt).collect(toList());
            printResult(getStrategy(input));
            if (i < n - 1) {
                reader.readLine();
                System.out.println();
            }
        }
    }
}
