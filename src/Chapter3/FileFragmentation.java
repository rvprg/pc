package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

class FileFragmentation {
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));

    private static boolean fit(List<String> fragments, String candidate) {
        List<String> temp = new ArrayList<String>(fragments);
        for (int i = 1; i < candidate.length() && !temp.isEmpty(); ++i) {
            final int j = i;
            temp.removeIf(x -> x.equalsIgnoreCase(candidate.substring(0, j)));
            temp.removeIf(x -> x.equalsIgnoreCase(candidate.substring(j)));
        }
        return temp.isEmpty();
    }

    private static String restore(List<String> fragments) {
        fragments.sort(comparing(String::length));
        String large = fragments.get(fragments.size() - 1);
        List<String> smallest = fragments.stream().filter(
            x -> x.length() == fragments.get(0).length()).collect(toList());
        for (String small : smallest) {
            if (fit(fragments, large + small)) {
                return large + small;
            } else if (fit(fragments, small + large)) {
                return small + large;
            }
        }
        return "Impossible";
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        reader.readLine();
        for (int i = 0; i < n; ++i) {
            List<String> fragments = new ArrayList<String>();
            do {
                String s = reader.readLine();
                if (s == null || s.equalsIgnoreCase("")) {
                    break;
                }
                fragments.add(s);
            } while (true);
            System.out.println(restore(fragments));
            System.out.println();
        }
    }
}
