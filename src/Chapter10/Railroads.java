package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Railroads {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static final int MAX_TIME = 2360;
    private static final int DEP_TIME = 0;
    private static final int DEP_STATION = 1;
    private static final int ARR_TIME = 2;
    private static final int ARR_STATION = 3;

    private static int[] solve(int m, List<int[]> trains, int source,
            int startTime, int destination) {
        Map<Integer, int[]> times = new HashMap<>();

        for (int j = 0; j < m; ++j) {
            int[] tab = new int[MAX_TIME];
            Arrays.fill(tab, -1);
            times.put(j, tab);
        }

        Collections.sort(trains,
                (x, y) -> x[DEP_TIME] != y[DEP_TIME] ? x[DEP_TIME] - y[DEP_TIME]
                        : x[ARR_TIME] - x[ARR_TIME]);

        for (int[] t : trains) {
            int[] tab = times.get(t[ARR_STATION]);
            if (t[DEP_STATION] == source && t[DEP_TIME] >= startTime) {
                for (int j = t[ARR_TIME]; j < tab.length; ++j) {
                    tab[j] = Math.max(tab[j], t[DEP_TIME]);
                }
            } else if (times.get(t[DEP_STATION])[t[DEP_TIME]] >= 0) {
                for (int j = t[ARR_TIME]; j < tab.length; ++j) {
                    tab[j] = Math.max(tab[j],
                            times.get(t[DEP_STATION])[t[DEP_TIME]]);
                }
            }
        }

        for (int j = 0; j < MAX_TIME; ++j) {
            if (times.get(destination)[j] > 0) {
                return new int[] { times.get(destination)[j], j };
            }
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < n; ++i) {
            int m = Integer.parseInt(reader.readLine().trim());
            Map<String, Integer> names = new HashMap<>();
            for (int j = 0; j < m; ++j) {
                names.put(reader.readLine().trim(), j);
            }

            List<int[]> trains = new ArrayList<>();
            int trainsCount = Integer.parseInt(reader.readLine().trim());
            for (int j = 0; j < trainsCount; ++j) {
                int stops = Integer.parseInt(reader.readLine().trim());
                int[] prev = null;
                for (int k = 0; k < stops; ++k) {
                    List<String> stop = Arrays
                            .stream(reader.readLine().trim().split(" "))
                            .filter(x -> !x.isEmpty())
                            .collect(Collectors.toList());
                    if (prev != null) {
                        trains.add(new int[] {
                                prev[0], prev[1],
                                Integer.parseInt(stop.get(0)),
                                names.get(stop.get(1)) });
                    }
                    prev = new int[] { Integer.parseInt(stop.get(0)),
                            names.get(stop.get(1)) };
                }
            }

            int startTime = Integer.parseInt(reader.readLine().trim());
            String sourceStr = reader.readLine().trim();
            int source = names.get(sourceStr);
            String destinationStr = reader.readLine().trim();
            int destination = names.get(destinationStr);

            int[] result = solve(m, trains, source, startTime, destination);

            System.out.println("Scenario " + (i + 1));
            if (result != null) {
                System.out.printf("Departure %04d %s\n", result[0], sourceStr);
                System.out.printf("Arrival   %04d %s\n", result[1],
                        destinationStr);
            } else {
                System.out.println("No connection");
            }
            System.out.println();
        }
    }

}

