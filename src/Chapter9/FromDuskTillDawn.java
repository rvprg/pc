import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FromDuskTillDawn {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    static class Train {
        String src;
        int srcTime;
        String dst;
        int dstTime;

        public Train(String src, int srcTime, String dst, int dstTime) {
            this.src = src;
            this.srcTime = srcTime;
            this.dst = dst;
            this.dstTime = dstTime;
        }
    }

    private static List<Train> nextTrains(Train lastTrain,
            List<Train> trains) {
        List<Train> nextTrains = new ArrayList<>();
        for (Train next : trains) {
            if (next.src.equals(lastTrain.dst) &&
                    next.srcTime >= lastTrain.dstTime) {
                nextTrains.add(next);
            }
        }
        return nextTrains;
    }

    private static int solve(String src, String dst, List<Train> trains) {
        int count = 0;
        if (src.equals(dst)) {
            return count;
        }
        Set<String> reachable = new HashSet<>();
        Set<String> visited = new HashSet<>();
        reachable.add(src);
        visited.add(src);

        while (!reachable.isEmpty()) {
            Set<String> _reachable = new HashSet<>();
            for (String srcStation : reachable) {
                for (Train trn : trains) {
                    if (trn.src.equals(srcStation)) {
                        _reachable.addAll(getReachable(trn, trains));
                    }
                }
            }
            _reachable.removeAll(visited);
            visited.addAll(reachable);
            reachable = _reachable;
            if (reachable.contains(dst)) {
                return count;
            }
            count++;
        }

        return -1;
    }

    private static Set<String> getReachable(Train src,
            List<Train> trains) {
        Deque<Train> stack = new ArrayDeque<>();
        Set<Train> used = new HashSet<>();
        Set<String> reachable = new HashSet<>();
        stack.push(src);
        while (!stack.isEmpty()) {
            Train train = stack.pop();
            reachable.add(train.dst);
            List<Train> nextTrn = nextTrains(train, trains);
            for (Train nextTrain : nextTrn) {
                if (!used.contains(nextTrain)) {
                    stack.push(nextTrain);
                }
            }
            used.add(train);
        }
        return reachable;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            List<Train> trains = new ArrayList<>();
            int m = Integer.parseInt(reader.readLine().trim());
            for (int j = 0; j < m; ++j) {
                List<String> input = Arrays
                        .stream(reader.readLine().trim().split(" "))
                        .filter(x -> !x.equals(""))
                        .collect(toList());
                int srcTime = Integer.parseInt(input.get(2));
                srcTime = srcTime <= 6 ? srcTime + 24 : srcTime;
                int dstTime = (srcTime + Integer.parseInt(input.get(3)));
                String src = input.get(0);
                String dst = input.get(1);
                if (!(srcTime >= 18 && srcTime <= 29 &&
                        dstTime >= 19 && dstTime <= 30)) {
                    continue;
                }
                trains.add(new Train(src.toLowerCase(), srcTime,
                        dst.toLowerCase(), dstTime));
            }
            List<String> input = Arrays
                    .stream(reader.readLine().trim().split(" "))
                    .filter(x -> !x.equals(""))
                    .collect(toList());
            int count = solve(input.get(0).toLowerCase(),
                    input.get(1).toLowerCase(), trains);
            System.out.println("Test Case " + (i + 1) + ".");
            if (count == -1) {
                System.out.println("There is no route Vladimir can take.");
            } else {
                System.out.println(
                        "Vladimir needs " + count + " litre(s) of blood.");
            }
        }
    }

}

