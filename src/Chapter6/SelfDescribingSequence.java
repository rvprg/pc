package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SelfDescribingSequence {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private List<Tuple> seq = new ArrayList<>();
    private static final long limit = 2_000_000_000L;

    SelfDescribingSequence() {
        seq.add(new Tuple(1, 1));
        seq.add(new Tuple(2, 3));
        seq.add(new Tuple(4, 5));
        int tupleIndex = 2;
        boolean cont = true;
        do {
            Tuple currTuple = seq.get(tupleIndex);
            for (long i = currTuple.v1; i <= currTuple.v2; ++i) {
                Tuple lastTuple = seq.get(seq.size() - 1);
                if (lastTuple.v1 >= limit) {
                    cont = false;
                    break;
                }
                seq.add(new Tuple(lastTuple.v2 + 1, lastTuple.v2 + tupleIndex + 1));
            }
            tupleIndex++;
        } while (cont);
    }

    static class Tuple {
        private long v1;
        private long v2;

        public Tuple(long v1, long v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public long get(long n) {
        int i = Collections.binarySearch(seq, new Tuple(n + 1, 0),
                (x, y) -> Long.compare(x.v1, y.v1));
        if (i < 0) {
            i = Math.abs(i + 1);
        }
        return i;
    }

    public static void main(String[] args) throws IOException {
        SelfDescribingSequence g = new SelfDescribingSequence();
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equalsIgnoreCase("")) {
            long n = Long.parseLong(currentLine.trim());
            if (n == 0) {
                break;
            }
            System.out.println(g.get(n));
        }
    }
}

