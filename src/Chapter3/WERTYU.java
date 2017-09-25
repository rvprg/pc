package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WERTYU {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private final static String KEYS = "`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./";
    private final static int[] map = new int[256];

    static {
        for (int i = 0; i < KEYS.length(); ++i) {
            map[KEYS.charAt(i)] = i;
        }
    }

    private static String shift(String currentLine) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < currentLine.length(); ++i) {
            output.append(map[currentLine.charAt(i)] != 0
                    ? KEYS.charAt(map[currentLine.charAt(i)] - 1)
                    : currentLine.charAt(i));
        }
        return output.toString();
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            System.out.println(shift(currentLine));
        }
    }

}
