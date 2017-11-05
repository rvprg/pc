package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;

class CarmichaelNumbers {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private final int[] primes;
    private final static int MAX_PRIMES = 66000;

    CarmichaelNumbers() {
        BitSet bits = new BitSet(MAX_PRIMES);
        for (int i = 2; i < Math.sqrt(MAX_PRIMES); ++i) {
            if (!bits.get(i)) {
                int k = 0;
                int ii = i * i;
                int j = ii + k * i;
                while (j < MAX_PRIMES) {
                    bits.set(j);
                    k++;
                    j = ii + k * i;
                }
            }
        }

        int size = 0;
        for (int i = 2; i < bits.length(); ++i) {
            if (!bits.get(i)) {
                size++;
            }
        }

        int j = 0;
        primes = new int[size];
        for (int i = 2; i < bits.length(); ++i) {
            if (!bits.get(i)) {
                primes[j++] = i;
            }
        }
    }

    public long modPow(long b, long e, long m) {
        if (m == 1) {
            return 0;
        }

        long result = 1;
        b = b % m;
        while (e > 0) {
            if (e % 2 == 1) {
                result = (result * b) % m;
            }
            e = e >> 1;
            b = (b * b) % m;
        }
        return result;
    }

    public boolean isCarmichael(int n) {
        if (Arrays.binarySearch(primes, n) >= 0) {
            return false;
        }

        for (int i = 2; i < n; ++i) {
            if (modPow(i, n, n) != i) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        CarmichaelNumbers n = new CarmichaelNumbers();
        String currentLine = null;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equals("")) {
            int v = Integer.parseInt(currentLine.trim());
            if (v == 0) {
                break;
            }
            if (n.isCarmichael(v)) {
                System.out.println(
                        "The number " + v + " is a Carmichael number.");
            } else {
                System.out.println(v + " is normal.");
            }
        }
    }
}

