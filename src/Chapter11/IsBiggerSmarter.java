package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IsBiggerSmarter {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static int[] solve(List<int[]> input) {
        if (input.size() == 1) {
            return new int[] { 0 };
        }
        List<int[]> sorted = new ArrayList<>(input);
        Collections.sort(sorted, (x, y) -> Integer.compare(x[0], y[0]));
        int[] res = new int[sorted.size()];
        int[] ref = new int[sorted.size()];
        int index = 0;

        for (int i = 0; i < sorted.size(); ++i) {
            res[i] = 1;
            ref[0] = -1;
            for (int j = 0; j < i; ++j) {
                if (sorted.get(j)[0] < sorted.get(i)[0] &&
                        sorted.get(j)[1] > sorted.get(i)[1]) {
                    if (res[i] < res[j] + 1) {
                        res[i] = res[j] + 1;
                        ref[i] = j;
                        if (index == -1 || res[i] > res[index]) {
                            index = i;
                        }
                    }
                }
            }
        }

        int[] solution = new int[res[index]];
        int i = solution.length - 1;
        do {
            solution[i] = sorted.get(index)[2];
            index = ref[index];
            i--;
        } while (index != -1 && i >= 0);

        return solution;
    }

    public static void main(String[] args) throws Exception {
        String currentLine;
        List<int[]> input = new ArrayList<>();
        int i = 0;
        while ((currentLine = reader.readLine()) != null) {
            Scanner s = new Scanner(currentLine);
            input.add(new int[] { s.nextInt(), s.nextInt(), i });
            s.close();
            i++;
        }
        int[] solution = solve(input);
        System.out.println(solution.length);
        for (int idx : solution) {
            System.out.println(idx + 1);
        }
    }
}

