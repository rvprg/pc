package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

public class PairsumoniousNumbers {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private final static int[] IMPOSSIBLE = new int[0];

    private enum Result {
        success, failure, overflow
    };

    private final int n;
    private final int[] v;
    private final BitSet excluded;

    public int[] getSolution() {
        int[] partialResult = new int[n];

        int[] bounds = new int[] { Integer.MAX_VALUE, v[v.length - 1] };
        for (int i = 0; i < v.length; ++i) {
            for (int j = i + 1; j < v.length; j++) {
                bounds[0] = Math.min(bounds[0], v[i] - v[j]);
            }
        }

        if (bounds[0] > bounds[1]) {
            int t = bounds[0];
            bounds[0] = bounds[1];
            bounds[1] = t;
        }

        if (bounds[1] < 0) {
            bounds[1] = 0;
        }

        for (int i = bounds[0]; i < bounds[1]; ++i) {
            partialResult = new int[n];
            partialResult[0] = i;
            int[] result = search(partialResult, 0, 0);
            if (result != IMPOSSIBLE) {
                Arrays.sort(result);
                return result;
            }
        }
        return IMPOSSIBLE;
    }

    PairsumoniousNumbers(List<Integer> input) {
        n = input.get(0);
        v = new int[input.size() - 1];
        for (int i = 1; i < input.size(); ++i) {
            v[i - 1] = input.get(i);
        }
        Arrays.sort(v);
        excluded = new BitSet(n);
    }

    private Result verify(int upTo, int[] solution) {
        excluded.clear();
        for (int i = 0; i < upTo; ++i) {
            for (int j = i + 1; j < upTo; ++j) {
                int currValue = solution[i] + solution[j];
                if (currValue > v[v.length - 1]) {
                    return Result.overflow;
                }

                int p = Arrays.binarySearch(v, currValue);
                if (p < 0) {
                    return Result.failure;
                }
                while (p > 0 && v[p] == v[p - 1]) {
                    p--;
                }
                while (p < v.length - 1 && excluded.get(p) &&
                        v[p] == v[p + 1]) {
                    p++;
                }
                if (p == v.length || excluded.get(p)) {
                    return Result.failure;
                }
                excluded.set(p);
            }
        }

        for (int i = 0; i < upTo - 1; ++i) {
            if (!excluded.get(i)) {
                return Result.failure;
            }
        }

        return Result.success;
    }

    private int[] search(int[] partialSolution, int last, int pos) {
        for (int i = pos; i < v.length; ++i) {
            partialSolution[last + 1] = v[i] - partialSolution[0];
            Result verificationResult = verify(last + 2, partialSolution);
            if (verificationResult == Result.success) {
                if (last + 1 < n - 1) {
                    int[] solution = search(partialSolution, last + 1, i + 1);
                    if (solution != IMPOSSIBLE) {
                        return solution;
                    }
                } else {
                    return partialSolution;
                }
            } else if (verificationResult == Result.overflow) {
                break;
            }
        }
        return IMPOSSIBLE;
    }

    private static String toString(int[] arr) {
        return Arrays.stream(arr).mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<Integer> input = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            int[] solution = new PairsumoniousNumbers(input).getSolution();
            System.out
                    .println(solution == IMPOSSIBLE ? "Impossible"
                            : toString(solution));
        }
    }

}

