

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Doublets {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static List<String> find(Map<String, Set<String>> graph,
            String from,
            String to) {

        if (from.equalsIgnoreCase(to)) {
            List<String> output = new ArrayList<>();
            output.add(from);
            output.add(to);
            return output;
        }

        Deque<String> q = new ArrayDeque<>();
        Set<String> s = new HashSet<String>();
        Map<String, String> r = new HashMap<>();
        q.push(from);
        r.put(from, from);

        while (!q.isEmpty()) {
            String currWord = q.pop();
            for (String adjacentWord : graph.get(currWord)) {
                if (!s.contains(adjacentWord)) {
                    s.add(adjacentWord);
                    q.addLast(adjacentWord);
                    if (!r.containsKey(adjacentWord)) {
                        r.put(adjacentWord, currWord);
                    }
                }
            }
            if (r.containsKey(to)) {
                List<String> output = new ArrayList<>();
                String curr = to;
                while (!curr.equalsIgnoreCase(from)) {
                    output.add(0, curr);
                    curr = r.get(curr);
                }
                output.add(0, from);
                return output;
            }
        }

        return null;
    }

    private static boolean adjacent(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int diffCount = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount == 1;
    }

    private static Map<String, Set<String>> getGraph(List<String> dict) {
        Map<String, Set<String>> graph = new HashMap<>();
        dict = dict.stream().distinct()
                .sorted(Comparator.comparing(String::length)).collect(toList());
        Map<Integer, List<String>> grouped = dict.stream()
                .collect(Collectors.groupingBy(String::length));
        for (String word : dict) {
            if (!graph.containsKey(word)) {
                graph.put(word, new HashSet<>());
            }
            List<String> adjacent = grouped.get(word.length()).stream()
                    .filter(x -> adjacent(word, x)).collect(toList());
            graph.get(word).addAll(adjacent);
        }
        return graph;
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        List<String> dict = new ArrayList<>();
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equalsIgnoreCase("")) {
            dict.add(currentLine.trim());
        }
        Map<String, Set<String>> graph = getGraph(dict);
        int line = 0;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equalsIgnoreCase("")) {
            if (line > 0) {
                System.out.println();
            }
            List<String> input = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals(""))
                    .collect(toList());
            List<String> result = find(graph, input.get(0), input.get(1));
            if (result == null) {
                System.out.println("No solution.");
            } else {
                result.forEach(System.out::println);
            }
            line++;
        }
    }
}

