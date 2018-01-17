package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class GardenOfEden {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    static final boolean[][] trans = new boolean[][] {
            { false, false, false },
            { false, false, true },
            { false, true, false },
            { false, true, true },
            { true, false, false },
            { true, false, true },
            { true, true, false },
            { true, true, true }
    };

    final boolean[] auto;
    final boolean[] curr;
    final boolean[] prev;
    boolean reachable;

    GardenOfEden(boolean[] auto, boolean[] curr) {
        this.auto = auto;
        this.curr = curr;
        this.prev = new boolean[curr.length];
        for (int j = 0; j < auto.length; ++j) {
            if (auto[j] == curr[0]) {
                prev[prev.length - 1] = trans[j][0];
                prev[0] = trans[j][1];
                prev[1] = trans[j][2];
                reachable = backtrack(1);
                if (reachable) {
                    break;
                }
            }
        }
    }

    boolean backtrack(int pos) {
        for (int j = 0; j < auto.length; ++j) {
            if (auto[j] != curr[pos]) {
                continue;
            }
            if (prev[pos - 1] == trans[j][0] &&
                    prev[pos] == trans[j][1]) {
                if (pos == curr.length - 1) {
                    if (prev[0] == trans[j][2]) {
                        return true;
                    }
                } else if (pos + 1 == curr.length - 1) {
                    if (prev[pos + 1] == trans[j][2]) {
                        if (backtrack(pos + 1)) {
                            return true;
                        }
                    }
                } else {
                    prev[pos + 1] = trans[j][2];
                    if (backtrack(pos + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            List<String> input = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals(""))
                    .collect(toList());
            boolean[] automaton = new boolean[8];
            String id = Integer.toBinaryString(Integer.parseInt(input.get(0)));
            for (int i = id.length() - 1, j = 0; i >= 0; --i, ++j) {
                automaton[j] = id.charAt(i) == '1';
            }
            boolean[] state = new boolean[input.get(2).length()];
            for (int i = 0; i < state.length; ++i) {
                state[i] = input.get(2).charAt(i) == '1';
            }
            System.out
                    .println(new GardenOfEden(automaton, state).reachable ? "REACHABLE"
                            : "GARDEN OF EDEN");
        }
    }

}

