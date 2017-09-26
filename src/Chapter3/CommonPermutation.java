package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CommonPermutation {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static int[] runs(int[] arr) {
        int[] runs = new int[256];
        for (int i = 0; i < arr.length; ++i) {
            runs[arr[i]] += 1;
        }
        return runs;
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            int[] line1 = currentLine.trim().chars().sorted().toArray();
            int[] line2 = reader.readLine().chars().sorted().toArray();
            int[] run1 = runs(line1);
            int[] run2 = runs(line2);
            List<Integer> distinct = stream(line1).distinct().boxed()
                    .collect(toList());
            distinct.retainAll(stream(line2).distinct().boxed()
                    .collect(toList()));

            StringBuilder longest = new StringBuilder();
            for (int i = 0; i < distinct.size(); ++i) {
                int len = Math.min(run1[distinct.get(i)],
                        run2[distinct.get(i)]);
                for (int j = 0; j < len; ++j) {
                    longest.append((char) distinct.get(i).intValue());
                }
            }
            System.out.println(longest);
        }
    }
}
