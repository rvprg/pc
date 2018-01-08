package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.LongStream;

public class Queue {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private final int n;
    private final int k;
    private final int[] buckets;
    private final int[] queue;
    private final int[] values;
    private final boolean[] skip;
    private long total;
    private long counter;

    private static final long[] factorial = LongStream.rangeClosed(0, 13)
            .map(f -> f == 0 ? 1
                    : LongStream.rangeClosed(1, f).reduce(1L,
                            (x, y) -> x * y))
            .toArray();

    private static final Long[][][] cache = new Long[13 + 1][13 + 1][13 + 1];

    public Queue(int n, int l, int r) {
        this.n = n;
        this.k = l - 1;
        buckets = new int[n + 1];
        skip = new boolean[n + 1];
        queue = new int[l + r - 2];
        values = new int[n - (l + r - 2) - 1 < 0 ? 0 : n - (l + r - 2) - 1];
        skip[n] = true;
        if (cache[n][l][r] != null) {
            total = cache[n][l][r];
        } else {
            select(0);
            cache[n][l][r] = total;
        }
    }

    private void select(int pos) {
        if (pos == queue.length) {
            for (int i = 1, j = 0; i <= n; ++i) {
                if (!skip[i]) {
                    values[j++] = i;
                }
            }
            counter = 0;
            count(0);
            int nn = queue.length;
            total += counter * factorial[nn] /
                    (factorial[k] * factorial[nn - k]);
            return;
        }

        int start = pos - 1 >= 0 ? queue[pos - 1] + 1 : 1;
        for (int i = start; i < n; ++i) {
            queue[pos] = i;
            skip[i] = true;
            select(pos + 1);
            skip[i] = false;
        }
    }

    private void count(int pos) {
        if (pos == values.length) {
            long permutations = 1;
            for (int i = 0; i < buckets.length; ++i) {
                permutations *= factorial[buckets[i]];
            }
            counter += permutations;
            return;
        }

        for (int bucket : queue) {
            if (bucket > values[pos]) {
                buckets[bucket]++;
                count(pos + 1);
                buckets[bucket]--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            List<Integer> line = stream(reader.readLine().split(" "))
                    .filter(x -> !x.equals(""))
                    .map(Integer::parseInt)
                    .collect(toList());
            int l = line.get(1);
            int r = line.get(2);
            if (l + r - 1 > 13) {
                System.out.println(0);
            } else {
                Queue q = new Queue(line.get(0), l, r);
                System.out.println(q.total);
            }
        }
    }

}

