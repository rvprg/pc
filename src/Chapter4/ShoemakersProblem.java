import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShoemakersProblem {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static class Job implements Comparable<Job> {
        int start;
        int fine;
        int index;

        public Job(int index, int start, int fine) {
            this.index = index;
            this.start = start;
            this.fine = fine;
        }

        @Override
        public int compareTo(Job o) {
            return Integer.compare(o.fine * start, fine * o.start);
        }
    }

    public static void main(String[] args)
            throws NumberFormatException, IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            reader.readLine();
            int count = Integer.parseInt(reader.readLine().trim());
            Job[] jobs = new Job[count];
            for (int j = 0; j < count; ++j) {
                List<Integer> input = stream(
                        reader.readLine().trim().split(" "))
                                .filter(x -> !x.equals(""))
                                .map(Integer::parseInt)
                                .collect(toList());
                jobs[j] = new Job(j, input.get(0), input.get(1));
            }
            Arrays.sort(jobs);
            System.out.println(Arrays.stream(jobs).map(x -> x.index + 1)
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
            if (i < n - 1) {
                System.out.println();
            }
        }
    }
}

