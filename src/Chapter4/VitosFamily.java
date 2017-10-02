package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class VitosFamily {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static int solve(List<Integer> input) {
        Collections.sort(input);
        int median = 0;

        if (input.size() % 2 == 0) {
            int p = input.size() / 2 - 1;
            median = (input.get(p) + input.get(p + 1)) / 2;
        } else {
            int p = input.size() / 2;
            median = (input.get(p));
        }

        int sum = 0;
        for (Integer v : input) {
            sum += Math.abs(v - median);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        int cases = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < cases; ++i) {
            List<Integer> input = stream(reader.readLine().trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            System.out.println(solve(input.subList(1, input.size())));
        }
    }

}

