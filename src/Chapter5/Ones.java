package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Ones {
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));

    private static int calculate(int n) {
        if (n == 1) {
            return 1;
        }
        int l = 2;
        int s = 11;
        int r = 11;

        if (n != s) {
            do {
                s = r * 10 + 1;
                int d = s / n;
                r = s - (n * d);
                l++;
            } while (r > 0);
        }

        return l;
    }

    public static void main(String[] args) throws IOException {
        reader.lines().map(Integer::parseInt)
                      .map(Ones::calculate)
                      .forEach(System.out::println);
    }
}
