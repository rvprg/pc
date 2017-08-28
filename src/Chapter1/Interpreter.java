package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Interpreter {
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));

    private static int interpret(List<Integer> input) {
        int[] reg = new int[10];
        int[] ram = new int[1000];
        for (int i = 0; i < input.size(); ++i) {
            ram[i] = input.get(i);
        }
        int pc = 0;
        int r = 0;
        while (ram[pc] != 100) {
            int op = ram[pc];
            int c = (op / 100) % 10;
            pc = (pc + 1) % 1000;
            r++;
            switch (c) {
            case 2:
                reg[(op / 10) % 10] = op % 10;
                break;
            case 3:
                reg[(op / 10) % 10] = (reg[(op / 10) % 10] + (op % 10)) % 1000;
                break;
            case 4:
                reg[(op / 10) % 10] = (reg[(op / 10) % 10] * (op % 10)) % 1000;
                break;
            case 5:
                reg[(op / 10) % 10] = reg[op % 10];
                break;
            case 6:
                reg[(op / 10) % 10] = (reg[(op / 10) % 10] + reg[op % 10]) % 1000;
                break;
            case 7:
                reg[(op / 10) % 10] = (reg[(op / 10) % 10] * reg[op % 10]) % 1000;
                break;
            case 8:
                reg[(op / 10) % 10] = ram[reg[op % 10]];
                break;
            case 9:
                ram[reg[op % 10]] = reg[(op / 10) % 10];
                break;
            case 0:
                if (reg[op % 10] != 0) {
                    pc = reg[(op / 10) % 10];
                }
                break;
            }
        }

        return r + 1;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.valueOf(reader.readLine().trim());
        reader.readLine();
        String currentLine;
        for (int i = 0; i < n; ++i) {
            List<Integer> input = new ArrayList<Integer>();
            while ((currentLine = reader.readLine()) != null &&
                    !currentLine.trim().equalsIgnoreCase("")) {
                input.add(Integer.parseInt(currentLine.trim()));
            }
            System.out.println(interpret(input));
            if (i < n - 1) {
                System.out.println();
            }
        }
    }
}
