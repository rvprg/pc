package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class HowManyPiecesOfLand {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static BigInteger calculate(BigInteger v) {
        BigInteger t1 = v.multiply(v.subtract(BigInteger.ONE))
                .divide(BigInteger.valueOf(2));
        BigInteger t2 = v.multiply(v.subtract(BigInteger.ONE))
                .multiply(v.subtract(BigInteger.valueOf(2)))
                .multiply(v.subtract(BigInteger.valueOf(3)))
                .divide(BigInteger.valueOf(4 * 3 * 2));
        return BigInteger.ONE.add(t2).add(t1);
    }

    public static void main(String[] args)
            throws NumberFormatException, IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            System.out.println(
                    calculate(new BigInteger(reader.readLine().trim())));
        }
    }
}

