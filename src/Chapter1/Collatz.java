package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

class Collatz {
    private static int MAX = 1000000;
    private int[] lengths = new int[MAX];
    private static final BufferedReader reader = 
             new BufferedReader(new InputStreamReader(System.in));
    int get(long index, HashMap<Long, Integer> surplus) {
        return (index < MAX) ? lengths[(int) index] : 
            (surplus.containsKey(index) ? surplus.get(index) : 0);
    }

    void set(long index, int value, HashMap<Long, Integer> surplus) {
        if (index < MAX) {
            lengths[(int) index] = value;
        } else {
            surplus.put(index, value);
        }
    }

    Collatz() {
        final HashMap<Long, Integer> surplus = new HashMap<Long, Integer>();
        for (long i = 2; i < MAX; ++i) {
            final Deque<Long> stack = new ArrayDeque<Long>();
            long n = i;
            int len = 2;
            while (n != 1) {
                stack.push(n);
                int prev = get(n, surplus);
                if (prev > 0) {
                    len = prev;
                    break;
                }
                n = n % 2 == 0 ? n / 2 : n * 3 + 1;
            }
            while (!stack.isEmpty()) {
                set(stack.pop(), len++, surplus);
            }
        }
    }

    public static void main(String[] args) {
        Collatz s = new Collatz();
        reader.lines()
                .map(x -> x.split(" "))
                .map(x -> new int[] { Integer.parseInt(x[0]), Integer.parseInt(x[1]) })
                .map(x -> new int[] { x[0], x[1], Math.min(x[0], x[1]), Math.max(x[0], x[1]) })
                .forEach(x -> {
                    System.out.println(x[0] + " " + x[1] + " " +
                            IntStream.range(x[2], x[3]).map(v -> s.lengths[v]).max().getAsInt());
                });
    }
}
