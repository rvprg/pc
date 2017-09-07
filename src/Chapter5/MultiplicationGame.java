package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class MultiplicationGame {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static int solve(long p, long n, int t,
            List<HashMap<Long, Integer>> memo) {
        if (p >= n) {
            return t - 1;
        }

        int s = t % 2;
        for (int i = 9; i >= 2; --i) {
            int result = 0;
            long next = p * i;
            if (memo.get(s).containsKey(next)) {
                result = memo.get(s).get(next);
            } else {
                result = solve(next, n, t + 1, memo);
                memo.get(s).put(next, result);
            }
            if (result % 2 == t % 2) {
                return result;
            }
        }

        return t + 1;
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            long input = Long.parseLong(currentLine.trim());
            List<HashMap<Long, Integer>> memo = new ArrayList<>();
            memo.add(new HashMap<Long, Integer>());
            memo.add(new HashMap<Long, Integer>());
            System.out.println(
                    solve(1, input, 1, memo) % 2 == 0 ? "Ollie wins."
                            : "Stan wins.");
        }
    }
}
