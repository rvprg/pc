package com.rvprg.pc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BiggerSquarePleaseOffline {
    static class SquareSideLengthsGenerator {
        private static final int HEURISTIC_DEPTH = 15;
        private Deque<Integer> currSquareSizes = new ArrayDeque<>();
        private List<List<Integer>> squareSizes = new ArrayList<>();

        SquareSideLengthsGenerator(int startSquareSize,
                int targetArea,
                int minSolutionSize) {
            search(startSquareSize, 0, targetArea, HEURISTIC_DEPTH);
            squareSizes = squareSizes.stream()
                    .filter(SquareSideLengthsGenerator::heuristicFilter)
                    .sorted((x, y) -> Integer.compare(x.size(), y.size()))
                    .filter(x -> x.size() >= minSolutionSize)
                    .collect(Collectors.toList());
        }

        private static boolean heuristicFilter(List<Integer> s) {
            Map<Integer, Long> result = s.stream().collect(
                    Collectors.groupingBy(
                            Function.identity(), Collectors.counting()));
            return Collections.max(result.values()) <= 4;
        }

        private void search(int startSquareSize, int currArea, int targetArea,
                int maxLen) {
            if (currArea == targetArea) {
                squareSizes.add(new ArrayList<>(currSquareSizes));
                return;
            }
            for (int i = startSquareSize; i >= 1; --i) {
                if (currArea + i * i <= targetArea &&
                        currSquareSizes.size() <= maxLen) {
                    currSquareSizes.addLast(i);
                    search(i, currArea + i * i, targetArea, maxLen);
                    currSquareSizes.removeLast();
                }
            }
        }
    }

    static class SolutionFinder {
        private static final int HEURISTIC_THRESHOLD = 25000;
        private final int n;
        private final boolean[][] square;
        private final List<Integer> candidate;
        private final Deque<int[]> solution = new ArrayDeque<>();
        private final boolean solutionFound;
        private boolean haltSearch;
        private int heuristicCounter;

        SolutionFinder(Deque<int[]> initial,
                List<Integer> candidateSquareSideSizes,
                int n) {
            this.n = n;
            this.candidate = candidateSquareSideSizes;
            this.square = new boolean[n][n];
            initial.forEach(x -> {
                this.solution.add(x);
                set(x, true);
            });
            solutionFound = find(0);
        }

        private boolean isInsertable(int[] s) {
            for (int i = s[0]; i <= s[0] + s[2] - 1; ++i) {
                for (int j = s[1]; j <= s[1] + s[2] - 1; ++j) {
                    if (j > n - 1 || i > n - 1 || square[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }

        private void set(int[] s, boolean f) {
            for (int i = s[0]; i <= s[0] + s[2] - 1; ++i) {
                for (int j = s[1]; j <= s[1] + s[2] - 1; ++j) {
                    square[i][j] = f;
                }
            }
        }

        private boolean find(int pos) {
            if (pos == candidate.size()) {
                return true;
            }

            if (++heuristicCounter > HEURISTIC_THRESHOLD) {
                haltSearch = true;
                return false;
            }

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (square[i][j]) {
                        continue;
                    }
                    int[] s = new int[] { i, j, candidate.get(pos) };
                    if (isInsertable(s)) {
                        set(s, true);
                        solution.addLast(s);
                        if (find(pos + 1)) {
                            return true;
                        }
                        if (haltSearch) {
                            return false;
                        }
                        solution.removeLast();
                        set(s, false);
                    }
                }
            }

            return false;
        }
    }

    static class SolutionFinderTaskResult {
        final Deque<int[]> solution;
        final boolean found;
        final int index;

        public SolutionFinderTaskResult(boolean found, Deque<int[]> solution,
                int index) {
            this.solution = solution;
            this.found = found;
            this.index = index;
        }
    }

    static class SolutionFinderTask
            implements Callable<SolutionFinderTaskResult> {
        final int index;
        final Deque<int[]> initial;
        final List<Integer> sizes;
        final int n;

        SolutionFinderTask(int index, Deque<int[]> initial, List<Integer> sizes,
                int n) {
            this.index = index;
            this.initial = initial;
            this.sizes = sizes;
            this.n = n;
        }

        @Override
        public SolutionFinderTaskResult call() throws Exception {
            SolutionFinder finder = new SolutionFinder(initial, sizes, n);
            return new SolutionFinderTaskResult(finder.solutionFound,
                    finder.solution,
                    index);
        }
    }

    private static Deque<int[]> trivial(int n, int d) {
        Deque<int[]> initial = new ArrayDeque<>();
        int h = n / d;
        initial.add(new int[] { 0, 0, (d - 1) * h });
        initial.add(new int[] { (d - 1) * h, (d - 1) * h, h });
        for (int i = 0; i < (d - 1); ++i) {
            initial.add(new int[] { (d - 1) * h, h * i, h });
            initial.add(new int[] { h * i, (d - 1) * h, h });
        }
        return initial;
    }

    final static int[] primes = new int[] { 3, 5, 7, 11, 13, 17, 19, 23,
            29, 31, 37, 41, 43, 47 };

    static Deque<int[]> getSolution(int n, int minSolutionSize, int adjust)
            throws InterruptedException, ExecutionException {
        if (n % 2 == 0) {
            return trivial(n, 2);
        } else if (n % 3 == 0) {
            return trivial(n, 3);
        } else {
            Deque<int[]> initial = new ArrayDeque<>();
            int size = n / 2 + 1;
            initial = new ArrayDeque<>();
            initial.add(new int[] { 0, 0, size + adjust });
            initial.add(new int[] { size + adjust, 0, size - 1 - adjust });
            initial.add(new int[] { 0, size + adjust, size - 1 - adjust });
            if (Arrays.binarySearch(primes, n) >= 0) {
                return getSolution(n, initial, minSolutionSize);
            } else {
                return getSolution(n, initial, 0);
            }
        }
    }

    static Deque<int[]> getSolution(int n, int minSolutionSize)
            throws InterruptedException, ExecutionException {
        Deque<int[]> best = getSolution(n, minSolutionSize, 0);
        int size = n / 2 + 1;
        for (int i = 1; size + i < n - 1 && i < 4; ++i) {
            Deque<int[]> curr = getSolution(n, minSolutionSize, i);
            if (curr.size() < best.size()) {
                best = curr;
            }
        }
        return best;
    }

    static Deque<int[]> getSolution(int n, Deque<int[]> initial,
            int minSolutionSize)
            throws InterruptedException, ExecutionException {
        int count = 0;
        for (int[] s : initial) {
            count += s[2] * s[2];
        }
        int maxHeight = n - initial.getFirst()[2];
        SquareSideLengthsGenerator s = new SquareSideLengthsGenerator(
                maxHeight,
                n * n - count,
                minSolutionSize - initial.size());

        final ExecutorService executor = 
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        final CompletionService<SolutionFinderTaskResult> completionService = 
            new ExecutorCompletionService<>(executor);

        for (int i = 0; i < s.squareSizes.size(); ++i) {
            completionService.submit(
                new SolutionFinderTask(i, initial, s.squareSizes.get(i), n));
        }

        SolutionFinderTaskResult[] result = 
            new SolutionFinderTaskResult[s.squareSizes.size()];

        int lastIndex = 0;
        try {
            while (true) {
                Future<SolutionFinderTaskResult> f = completionService.take();
                result[f.get().index] = f.get();
                for (int i = lastIndex; i < result.length; ++i) {
                    if (result[i] == null) {
                        break;
                    } else if (!result[i].found) {
                        lastIndex++;
                    } else if (result[i].found) {
                        return result[i].solution;
                    }
                }
            }
        } finally {
            if (executor != null) {
                executor.shutdownNow();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int minSolutionSize = 0;
        for (int i = 1; i <= 10; ++i) {
            System.out.println(i);
            if (i < 3) {
                System.out.println("0");
                continue;
            }
            Deque<int[]> solution = getSolution(i, minSolutionSize);
            minSolutionSize = solution.size();
            System.out.println(solution.size());
            for (int[] square : solution) {
                System.out.printf("%d %d %d\n", square[0] + 1,
                        square[1] + 1, square[2]);
            }
        }
    }

}
