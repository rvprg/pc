package com.rvprg.pc;

import static java.lang.Math.abs;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class JollyJumpers {
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<Integer> nums = stream(currentLine.trim().split(" ")).filter(x -> !x.equals(""))
                .skip(1).map(Integer::parseInt).collect(toList());
            int[] diffs = range(0, nums.size() - 1)
                .map(i -> abs(nums.get(i) - nums.get(i + 1))).distinct().sorted().toArray();
            boolean isJolly = range(0, diffs.length).boxed()
                .map(i -> diffs[i] == i + 1).reduce(true, (x, y) -> x && y);
            System.out.println(diffs.length == nums.size() - 1 && isJolly ? "Jolly" : "Not jolly");
        }
    }
}
