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
        lengths[1] = 1;
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
         String input;
         while ((input = reader.readLine()) != null &&
                 !input.trim().equalsIgnoreCase("")) {
             List<String> str = Arrays.stream(input.trim().split(" "))
                .filter(x -> !x.equals("")).collect(Collectors.toList());
             int x[] = new int[] { Integer.parseInt(str.get(0)),
                     Integer.parseInt(str.get(1)) };
             System.out.println(x[0] + " " + x[1] + " " +
                     IntStream.rangeClosed(Math.min(x[0], x[1]), Math.max(x[0],
                             x[1])).map(v -> s.lengths[v]).max().getAsInt());
         }
    }
}
