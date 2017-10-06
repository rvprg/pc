package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

public class Hartals {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static void set(int n, BitSet res, int s, int l, boolean v) {
        while (s <= n) {
            res.set(s, v);
            s += l;
        }
    }

    private static int solve(int n, int[] h) {
        BitSet res = new BitSet(n + 1);
        for (int i = 0; i < h.length; ++i) {
            set(n, res, h[i], h[i], true);
        }
        set(n, res, 6, 7, false);
        set(n, res, 7, 7, false);
        int count = 0;
        for (int i = 0; i < res.size(); ++i) {
            count += res.get(i) ? 1 : 0;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        int cases = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < cases; ++i) {
            int n = Integer.parseInt(reader.readLine().trim());
            int p = Integer.parseInt(reader.readLine().trim());
            int[] h = new int[p];
            for (int j = 0; j < p; ++j) {
                h[j] = Integer.parseInt(reader.readLine().trim());
            }
            System.out.println(solve(n, h));
        }
    }

}
