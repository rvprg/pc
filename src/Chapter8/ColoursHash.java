package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ColoursHash {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static State finalState = new State(
            new int[] { 0, 3, 4, 3, 0, 5, 6, 5, 0, 1,
                    2, 1, 0, 7, 8, 7, 0, 9, 10, 9, 0 },
            0);

    final static int[] moves = new int[] { 3, 4, 1, 2 };
    final static Map<State, String> precomputed = init();

    static class State {
        final int[] curr;

        public State(State state, int rotation) {
            this(state.curr, rotation);
        }

        public State(List<Integer> state) {
            curr = new int[21];
            for (int i = 0; i < curr.length; ++i) {
                curr[i] = state.get(i);
            }
        }

        public State(int[] state, int rotation) {
            this.curr = new int[21];

            final LinkedList<Integer> left = new LinkedList<>();
            final LinkedList<Integer> right = new LinkedList<>();
            final LinkedList<Integer> middle = new LinkedList<>();

            for (int i = 0; i < 12; ++i) {
                if (i < 9) {
                    left.add(state[i]);
                    right.add(state[i + 12]);
                } else {
                    middle.add(state[i]);
                }
            }

            if (rotation == 1) {
                middle.addFirst(left.removeLast());
                middle.addFirst(left.removeLast());
                left.addFirst(middle.removeLast());
                left.addFirst(middle.removeLast());
            } else if (rotation == 2) {
                middle.addLast(right.removeFirst());
                middle.addLast(right.removeFirst());
                right.addLast(middle.removeFirst());
                right.addLast(middle.removeFirst());
            } else if (rotation == 3) {
                middle.addLast(left.removeFirst());
                middle.addLast(left.removeFirst());
                left.addLast(middle.removeFirst());
                left.addLast(middle.removeFirst());
            } else if (rotation == 4) {
                middle.addFirst(right.removeLast());
                middle.addFirst(right.removeLast());
                right.addFirst(middle.removeLast());
                right.addFirst(middle.removeLast());
            }

            for (int i = 0; i < left.size(); ++i) {
                curr[i] = left.get(i);
            }
            for (int i = 0; i < middle.size(); ++i) {
                curr[9 + i] = middle.get(i);
            }
            for (int i = 0; i < right.size(); ++i) {
                curr[12 + i] = right.get(i);
            }
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(curr);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            State other = (State) obj;
            if (!Arrays.equals(curr, other.curr))
                return false;
            return true;
        }
    }

    static class Tuple {
        int depth;
        String solution;

        public Tuple(int depth, String solution) {
            this.depth = depth;
            this.solution = solution;
        }
    }

    static Map<State, String> init() {
        Map<State, String> solutions = new HashMap<>();
        generate(solutions, finalState);
        return solutions;
    }

    private static void generate(Map<State, String> solutions, State node) {
        Deque<State> next = new ArrayDeque<>();
        Map<State, Tuple> depths = new HashMap<>();
        Set<State> visited = new HashSet<>();
        next.addLast(node);
        depths.put(node, new Tuple(0, ""));
        visited.add(node);

        while (!next.isEmpty()) {
            State n = next.pop();
            Tuple tuple = depths.get(n);
            if (tuple.depth > 8) {
                continue;
            }
            String solution = tuple.solution;
            for (int i = 1; i <= 4; ++i) {
                State adj = new State(n, moves[i - 1]);
                if (!visited.contains(adj)) {
                    next.add(adj);
                    depths.putIfAbsent(adj,
                            new Tuple(tuple.depth + 1, i + solution));
                    solutions.putIfAbsent(adj, i + solution);
                }
            }
            visited.add(n);
        }
    }


    private static String find(State node) {
        Deque<State> next = new ArrayDeque<>();
        Map<State, Tuple> depths = new HashMap<>();
        Set<State> visited = new HashSet<>();
        next.addLast(node);
        depths.put(node, new Tuple(0, ""));
        visited.add(node);

        while (!next.isEmpty()) {
            State n = next.pop();
            Tuple tuple = depths.get(n);
            String solution = tuple.solution;
            if (tuple.depth > 8) {
                continue;
            }
            if (ColoursHash.precomputed.containsKey(n)) {
                return solution + ColoursHash.precomputed.get(n);
            }
            for (int i = 1; i <= 4; ++i) {
                State adj = new State(n, i);
                if (!visited.contains(adj)) {
                    next.add(adj);
                    depths.putIfAbsent(adj,
                            new Tuple(tuple.depth + 1, solution + i));
                }
            }
            visited.add(n);
        }

        return null;
    }


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            String line = reader.readLine();
            List<Integer> input = stream(line.trim().split(" "))
                    .filter(x -> !x.equals(""))
                    .map(Integer::parseInt)
                    .collect(toList());
            State state = new State(input);
            if (state.equals(finalState)) {
                System.out.println("PUZZLE ALREADY SOLVED");
            } else {
                String solution = find(state);
                if (solution != null) {
                    System.out.println(solution);
                } else {
                    System.out.println("NO SOLUTION WAS FOUND IN 16 STEPS");
                }
            }
        }

    }
}
