package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TowerOfCubes {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    final static int[] map = new int[] { 1, 0, 3, 2, 5, 4 };
    final static String[] toString = new String[] { "front", "back",
            "left", "right", "top", "bottom" };
    final List<String> tower = new ArrayList<>();
    final int[][] table;
    final int[][][] refs;
    int height = 0;
    int colorIdx = 0;
    int head = 0;

    TowerOfCubes(List<List<Integer>> cubes) {
        table = new int[cubes.size()][6];
        refs = new int[cubes.size()][6][2];
        buildUpTable(cubes);
        buildUpTower();
    }

    private void buildUpTower() {
        int currentColorIdx = colorIdx;
        while (true) {
            tower.add((head + 1) + " " + toString[currentColorIdx]);
            int[] ref = refs[head][currentColorIdx];
            if (ref == null) {
                break;
            }
            head = ref[0];
            currentColorIdx = ref[1];
        }
    }

    private static int indexOf(int start, int value, List<Integer> array) {
        for (int i = start; i < array.size(); ++i) {
            if (array.get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    private void buildUpTable(List<List<Integer>> cubes) {
        for (int current = cubes.size() - 1; current >= 0; --current) {
            List<Integer> cube = cubes.get(current);
            for (int j = 0; j < 6; ++j) {
                int color = cube.get(map[j]);
                int max = 1;
                refs[current][j] = null;
                for (int i = current + 1; i < cubes.size(); ++i) {
                    int topColorIndex = -1;
                    while ((topColorIndex = indexOf(topColorIndex + 1, color,
                            cubes.get(i))) != -1) {
                        if (max < table[i][topColorIndex] + 1) {
                            max = table[i][topColorIndex] + 1;
                            refs[current][j] = new int[] { i, topColorIndex };
                        }
                    }
                }
                table[current][j] = max;
                if (height < max) {
                    colorIdx = j;
                    height = max;
                    head = current;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        int caseNo = 1;
        while ((currentLine = reader.readLine()) != null) {
            while (currentLine.trim().isEmpty()) {
                currentLine = reader.readLine();
            }
            int n = Integer.parseInt(currentLine.trim());
            if (n == 0) {
                break;
            }
            List<List<Integer>> cubes = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                cubes.add(stream(
                        reader.readLine().trim().split(" "))
                                .filter(x -> !x.equals(""))
                                .map(Integer::parseInt)
                                .collect(toList()));
            }
            System.out.println("Case #" + (caseNo++));
            TowerOfCubes toc = new TowerOfCubes(cubes);
            System.out.println(toc.height);
            toc.tower.stream().forEach(System.out::println);
            System.out.println();
        }
    }
}

