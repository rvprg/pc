package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class EuclidProblem {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static long[] euclid(long a, long b) {
        long s = 0;
        long old_s = 1;
        long t = 1;
        long old_t = 0;
        long r = b;
        long old_r = a;
        while (r != 0) {
            long quotient = old_r / r;
            long p = r;
            r = old_r - quotient * r;
            old_r = p;
            p = s;
            s = old_s - quotient * s;
            old_s = p;
            p = t;
            t = old_t - quotient * t;
            old_t = p;
        }
        return new long[] { old_s, old_t, old_r };
    }

    public static String toString(long[] arr) {
        return Arrays.stream(arr).mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) throws IOException {
        String currentLine = null;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equals("")) {
            List<Integer> input = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            System.out.println(toString(euclid(input.get(0), input.get(1))));
        }
    }
}

