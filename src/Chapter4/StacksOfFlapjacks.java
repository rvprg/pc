package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StacksOfFlapjacks {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static int max(List<Integer> input, int skip) {
        int index = -1;
        int max = Integer.MIN_VALUE;
        for (int i = skip; i < input.size(); ++i) {
            if (max < input.get(i)) {
                index = i;
                max = input.get(i);
            }
        }
        return index;
    }

    private static List<Integer> solve(List<Integer> input) {
        List<Integer> inputCopy = new ArrayList<>(input);
        Collections.reverse(inputCopy);
        List<Integer> flips = new ArrayList<>();
        int sorted = 0;

        while (sorted < inputCopy.size()) {
            int index = max(inputCopy, sorted);
            if (index != sorted) {
                flips.add(index + 1);
                flips.add(sorted + 1);
                Collections.reverse(inputCopy.subList(index, inputCopy.size()));
                Collections
                        .reverse(inputCopy.subList(sorted, inputCopy.size()));
            }
            sorted++;
        }
        flips.add(0);
        return flips;
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<Integer> input = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            System.out.println(currentLine);
            System.out.println(solve(input).stream().map(x -> x.toString())
                    .collect(Collectors.joining(" ")));
        }
    }
}

