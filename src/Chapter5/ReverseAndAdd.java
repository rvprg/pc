package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ReverseAndAdd {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static long reverse(long value) {
        long reversed = 0;
        while (value > 9) {
            reversed = reversed * 10 + (value % 10);
            value /= 10;
        }
        reversed = reversed * 10 + value;
        return reversed;
    }

    private static boolean isPalindrome(long value) {
        return value == reverse(value);
    }

    public static long[] calculate(long value) {
        int count = 0;
        do {
            value = value + reverse(value);
            count++;
        } while (!isPalindrome(value));
        return new long[] { count, value };
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            long v = Integer.parseInt(reader.readLine().trim());
            long[] res = calculate(v);
            System.out.println(res[0] + " " + res[1]);
        }
    }
}
