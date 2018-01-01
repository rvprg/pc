package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FireStations {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    static class AdjNode {
        final int index;
        final long dist;

        public AdjNode(int index, long dist) {
            this.index = index;
            this.dist = dist;
        }
    }

    private static long[] dijkstra(
            Map<Integer, List<AdjNode>> graph, int src) {
        long[] distances = new long[501];
        Arrays.fill(distances, Long.MAX_VALUE);
        Set<Integer> next = new HashSet<>();
        for (Entry<Integer, List<AdjNode>> e : graph.entrySet()) {
            next.add(e.getKey());
        }
        distances[src] = 0L;

        while (!next.isEmpty()) {
            long min = -1;
            int node = -1;
            for (Integer nextNode : next) {
                if (distances[nextNode] < min || min == -1) {
                    min = distances[nextNode];
                    node = nextNode;
                }
            }
            next.remove(node);
            for (AdjNode adj : graph.get(node)) {
                long newMin = min + adj.dist;
                if (newMin < distances[adj.index]) {
                    distances[adj.index] = newMin;
                }
            }
        }

        return distances;
    }

    private static long[] merge(Collection<Integer> nodes,
            long[] prevDistances,
            long[] distancesFromStation) {
        long[] merged = Arrays.copyOf(prevDistances,
                prevDistances.length);
        for (Integer j : nodes) {
            merged[j] = Math.min(merged[j],
                    distancesFromStation[j]);
        }
        return merged;
    }

    private static int solve(Set<Integer> stations,
         Map<Integer, List<AdjNode>> graph) {
        long[] distances = null;
        for (Integer station : stations) {
            long[] distancesFromStation = dijkstra(graph, station);
            distances = distances != null ? merge(graph.keySet(), distances,
                    distancesFromStation) : distancesFromStation;
        }

        long minMax = Long.MAX_VALUE;
        int newStationNumber = 1;
        for (Integer src : graph.keySet()) {
            if (stations.contains(src)) {
                continue;
            }
            long[] distancesFromStation = dijkstra(graph, src);
            long[] newDistances = merge(graph.keySet(), distances,
                    distancesFromStation);
            long max = 0;
            for (Integer i : graph.keySet()) {
                max = Math.max(newDistances[i], max);
            }
            if (max < minMax) {
                minMax = max;
                newStationNumber = src;
            }
        }

        return newStationNumber;
    }

    private static List<Integer> parseLine(String line) {
        return stream(line.trim().split(" "))
                .filter(x -> !x.equals(""))
                .map(Integer::parseInt)
                .collect(toList());
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        reader.readLine();
        for (int i = 0; i < n; ++i) {
            List<Integer> fi = parseLine(reader.readLine());
            Set<Integer> stations = new HashSet<>();
            for (int j = 0; j < fi.get(0); ++j) {
                stations.add(Integer.parseInt(reader.readLine().trim()));
            }
            Map<Integer, List<AdjNode>> graph = new HashMap<>();
            String currentLine = "";
            while ((currentLine = reader.readLine()) != null &&
                    !currentLine.trim().isEmpty()) {
                List<Integer> node = parseLine(currentLine);
                graph.putIfAbsent(node.get(0), new ArrayList<>());
                graph.putIfAbsent(node.get(1), new ArrayList<>());
                graph.get(node.get(0))
                        .add(new AdjNode(node.get(1), node.get(2)));
                graph.get(node.get(1))
                        .add(new AdjNode(node.get(0), node.get(2)));
            }

            System.out.println(solve(stations, graph));

            if (i < n - 1) {
                System.out.println();
            }
        }
    }

}

