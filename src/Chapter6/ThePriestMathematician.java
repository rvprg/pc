package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class ThePriestMathematician {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger ZERO = BigInteger.ZERO;

    public static BigInteger[] generate() {
        BigInteger[] hanoi = new BigInteger[10001];
        hanoi[0] = ZERO;
        hanoi[1] = ONE;
        for (int i = 2, k = 1; i <= 10000; ++i) {
            BigInteger n1 = TWO.pow(i - k).subtract(ONE)
                    .add(hanoi[k].multiply(TWO));
            BigInteger n2 = hanoi[k + 1] != null ? TWO.pow(i - (k + 1))
                    .subtract(ONE).add(hanoi[k + 1].multiply(TWO))
                    : null;
            if (n2 != null && n2.compareTo(n1) == -1) {
                k++;
                hanoi[i] = n2;
            } else {
                hanoi[i] = n1;
            }
        }
        return hanoi;
    }

    public static void main(String[] args) throws IOException {
        BigInteger[] hanoi = generate();
        String currentLine = null;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equals("")) {
            System.out.println(hanoi[Integer.parseInt(currentLine.trim())]);
        }
    }
}

