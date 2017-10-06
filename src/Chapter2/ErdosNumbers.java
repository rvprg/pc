package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErdosNumbers {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final Pattern namePattern = Pattern
            .compile("[\\w^.,]+\\s*,\\s*(\\w\\.)+\\s*[,:]");

    private static final String ERDOS = "Erdos, P.";

    private static void add(Map<String, Set<String>> graph,
            List<String> names) {
        for (int i = 0; i < names.size(); ++i) {
            String currName = names.get(i);
            if (!graph.containsKey(names.get(i))) {
                graph.put(currName, new HashSet<String>());
            }
            names.forEach(name -> {
                if (!currName.equalsIgnoreCase(name)) {
                    graph.get(currName).add(name);
                }
            });
        }
    }

    private static List<String> getNames(String input) {
        List<String> names = new ArrayList<>();
        Matcher m = namePattern.matcher(input);
        while (m.find()) {
            names.add(input.substring(m.start(), m.end() - 1).trim());
        }
        return names;
    }

    private static Map<String, Integer> getAnswer(
            Map<String, Set<String>> graph) {
        Deque<String> q = new ArrayDeque<>();
        Set<String> s = new HashSet<String>();
        Map<String, Integer> r = new HashMap<>();
        q.push(ERDOS);
        r.put(ERDOS, Integer.valueOf(0));

        while (!q.isEmpty()) {
            String n = q.pop();
            int depth = r.get(n);
            for (String x : graph.get(n)) {
                if (!s.contains(x)) {
                    s.add(x);
                    q.addLast(x);
                    r.put(x, Integer.valueOf(depth + 1));
                }
            }
        }

        return r;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            List<Integer> nm = stream(reader.readLine().trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            Map<String, Set<String>> graph = new HashMap<>();
            for (int j = 0; j < nm.get(0); ++j) {
                add(graph, getNames(reader.readLine().trim()));
            }
            Map<String, Integer> r = getAnswer(graph);
            System.out.println("Scenario " + (i + 1));
            for (int j = 0; j < nm.get(1); ++j) {
                String name = reader.readLine().trim();
                System.out.println(name + " " +
                        (r.containsKey(name) ? r.get(name) : "infinity"));
            }
        }
    }
}

