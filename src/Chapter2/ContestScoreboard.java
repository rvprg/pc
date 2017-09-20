package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContestScoreboard {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static class Team implements Comparable<Team> {
        private final int num;
        private int totalTime;
        private Set<Integer> solved = new HashSet<>();
        private final int[] penalties = new int[10];

        @Override
        public String toString() {
            return num + " " + solved.size() + " " + getTotalTime();
        }

        public Team(int num) {
            this.num = num;
        }

        public int getTotalTime() {
            int time = totalTime;
            for (Integer problemId : solved) {
                time += penalties[problemId];
            }
            return time;
        }

        public void update(Integer problem, Integer time, String verdict) {
            switch (verdict) {
            case "C":
                if (solved.add(problem)) {
                    totalTime += time;
                }
                break;
            case "I":
                if (!solved.contains(problem)) {
                    penalties[problem] += 20;
                }
                break;
            default:
                break;
            }
        }

        @Override
        public int compareTo(Team o) {
            int solvedCmp = Integer.compare(o.solved.size(),
                    this.solved.size());
            if (solvedCmp == 0) {
                int timeCmp = Integer.compare(getTotalTime(), o.getTotalTime());
                if (timeCmp == 0) {
                    return Integer.compare(this.num, o.num);
                }
                return timeCmp;
            }
            return solvedCmp;
        }
    }

    public static void main(String[] args) throws IOException {
        int cases = Integer.parseInt(reader.readLine().trim());
        reader.readLine();
        String currentLine = null;
        for (int i = 0; i < cases; ++i) {
            Map<Integer, Team> participants = new HashMap<>();
            while ((currentLine = reader.readLine()) != null &&
                    !currentLine.trim().equals("")) {
                List<String> inputLine = stream(currentLine.trim().split(" "))
                        .filter(x -> !x.equals(""))
                        .collect(toList());
                Integer num = Integer.parseInt(inputLine.get(0));
                Integer problem = Integer.parseInt(inputLine.get(1));
                Integer time = Integer.parseInt(inputLine.get(2));
                String verdict = inputLine.get(3);
                if (!participants.containsKey(num)) {
                    participants.put(num, new Team(num));
                }
                participants.get(num).update(problem, time, verdict);
            }
            participants.values().stream().sorted()
                    .forEach(System.out::println);
            if (i < cases - 1) {
                System.out.println();
            }
        }
    }
}

