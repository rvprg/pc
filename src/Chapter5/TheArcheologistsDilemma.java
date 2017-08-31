package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class TheArcheologistsDilemma {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static long calculate(long v) {
        long n = BigDecimal.valueOf(v).precision() + 1;
        final double left = Math.log10(v);
        final double right = Math.log10(v + 1);
        final double log10_2 = Math.log10(2);
        while (true) {
            long m = (long) Math.floor((left / log10_2) + n / log10_2);
            while (left + n > (log10_2 * m)) {
                m++;
            }
            if (right + n > (log10_2 * m)) {
                return m;
            }
            n++;
        }
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            System.out.println(calculate(Long.parseLong(currentLine.trim())));
        }
    }
}
