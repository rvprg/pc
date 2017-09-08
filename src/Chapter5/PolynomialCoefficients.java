package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolynomialCoefficients {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static List<Integer> expand(int n) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = n; i > 0; --i) {
            res.add(i);
        }
        return res;
    }

    private static long calculate(int n, List<Integer> v) {
        List<Integer> numerator = expand(n);
        List<Integer> denominator = new ArrayList<>();
        v.stream().filter(x -> x > 0)
                .forEach(x -> denominator.addAll(expand(x)));
        Iterator<Integer> it = denominator.iterator();
        while (it.hasNext()) {
            if (numerator.remove(it.next())) {
                it.remove();
            }
        }
        return numerator.stream().reduce(1, Math::multiplyExact).intValue() /
                denominator.stream().reduce(1, Math::multiplyExact).intValue();
    }

    private static List<Integer> readList(String input) {
        return stream(input.trim().split(" "))
                .filter(x -> !x.equals("")).map(Integer::parseInt)
                .collect(toList());
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<Integer> nk = readList(currentLine);
            List<Integer> v = readList(reader.readLine());
            System.out.println(calculate(nk.get(0), v));
        }
    }
}
