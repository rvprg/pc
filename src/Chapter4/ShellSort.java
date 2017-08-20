package com.rvprg.pc;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

class ShellSort {
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));

    private static List<String> getStrategy(List<String> input, List<String> target) {
        int i = input.size() - 1;
        int j = target.size() - 1;
        while (i >= 0 && j >= 0) {
            while (j >= 0 && !target.get(i).equals(input.get(j))) {
                j--;
            }
            if (j < 0) {
                break;
            }
            i--;
            j--;
        }
        List<String> output = target.subList(0, i + 1);
        Collections.reverse(output);
        return output;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.valueOf(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            int count = Integer.valueOf(reader.readLine().trim());
            List<String> input = reader.lines().limit(count).collect(toList());
            List<String> target = reader.lines().limit(count).collect(toList());
            getStrategy(input, target).forEach(System.out::println);
            System.out.println();
        }
    }
}
