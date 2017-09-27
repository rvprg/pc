package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AutomatedJudgeScript {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static String read(int n) throws IOException {
        StringBuilder input = new StringBuilder();
        int newlines = 0;
        while (newlines < n) {
            int c = reader.read();
            if (c == '\n') {
                newlines++;
            }
            input.append((char) c);
        }
        return input.toString();
    }

    public static void main(String[] args) throws IOException {
        int i = 0;
        while (true) {
            int n = Integer.parseInt(reader.readLine().trim());
            if (n == 0) {
                break;
            }
            String src = read(n);
            String dst = read(Integer.parseInt(reader.readLine().trim()));

            System.out.print("Run #" + (++i) + ": ");
            if (src.equals(dst)) {
                System.out.println("Accepted");
            } else {
                if (Arrays.equals(
                        src.chars().filter(Character::isDigit).toArray(),
                        dst.chars().filter(Character::isDigit).toArray())) {
                    System.out.println("Presentation Error");
                } else {
                    System.out.println("Wrong Answer");
                }
            }
        }
    }
}

