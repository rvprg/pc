package com.rvprg.pc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FootballAkaSoccer {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
    private static final PrintWriter output = new PrintWriter(
            new BufferedWriter(
                    new OutputStreamWriter(System.out,
                            Charset.forName("ISO-8859-1"))));

    private static class TeamRank implements Comparable<TeamRank> {
        private final String name;
        private int points;
        private int goalsScored;
        private int goalsAgainst;
        private int gamesPlayed;
        private int wins;
        private int ties;
        private int losses;

        public TeamRank(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name + " " + points + "p, " + gamesPlayed + "g (" + wins +
                    "-" + ties + "-" + losses + "), " +
                    (goalsScored - goalsAgainst) + "gd (" + goalsScored + "-" +
                    goalsAgainst + ")";
        }

        @Override
        public int compareTo(TeamRank o) {
            int pointsCmp = o.points - points;
            if (pointsCmp != 0) {
                return pointsCmp;
            }

            int winsCmp = o.wins - wins;
            if (winsCmp != 0) {
                return winsCmp;
            }

            int gdCmp = (o.goalsScored - o.goalsAgainst) -
                    (goalsScored - goalsAgainst);
            if (gdCmp != 0) {
                return gdCmp;
            }

            int goalsCmp = o.goalsScored - goalsScored;
            if (goalsCmp != 0) {
                return goalsCmp;
            }

            int gamesPlayedCmp = gamesPlayed - o.gamesPlayed;
            if (gamesPlayedCmp != 0) {
                return gamesPlayedCmp;
            }

            return name.toLowerCase().compareTo(o.name.toLowerCase());
        }
    }

    private static class Game {
        private final String[] teams;
        private final int[] goals;

        private String[] split(String input, String ch) {
            return new String[] {
                    input.substring(0, input.indexOf(ch)),
                    input.substring(input.indexOf(ch) + 1, input.length())
            };
        }

        public Game(String game) {
            String[] parts = split(game, "@");
            goals = new int[] {
                    Integer.parseInt(split(parts[0], "#")[1]),
                    Integer.parseInt(split(parts[1], "#")[0])
            };
            teams = new String[] {
                    split(parts[0], "#")[0],
                    split(parts[1], "#")[1]
            };
        }
    }

    private static TeamRank getRank(String teamName, List<Game> games) {
        TeamRank rank = new TeamRank(teamName);
        for (Game g : games) {
            boolean t1 = g.teams[0].equals(teamName);
            boolean t2 = g.teams[1].equals(teamName);
            if (t1 || t2) {
                rank.goalsScored += g.goals[t1 ? 0 : 1];
                rank.goalsAgainst += g.goals[t1 ? 1 : 0];
                rank.gamesPlayed++;
                int cmp = Integer.compare(g.goals[t1 ? 0 : 1],
                        g.goals[t1 ? 1 : 0]);
                if (cmp == 0) {
                    rank.ties++;
                    rank.points++;
                } else if (cmp == -1) {
                    rank.losses++;
                } else {
                    rank.wins++;
                    rank.points += 3;
                }
            }
        }
        return rank;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; ++i) {
            String tournamentName = reader.readLine();
            int teamCount = Integer.parseInt(reader.readLine().trim());
            Set<String> teamSet = new HashSet<>();
            for (int j = 0; j < teamCount; ++j) {
                teamSet.add(reader.readLine());
            }
            int gamesCount = Integer.parseInt(reader.readLine().trim());
            List<Game> games = new ArrayList<>();
            for (int j = 0; j < gamesCount; ++j) {
                games.add(new Game(reader.readLine()));
            }
            List<String> teams = new ArrayList<>(teamSet);
            List<TeamRank> ranks = new ArrayList<>();
            for (int j = 0; j < teams.size(); ++j) {
                ranks.add(getRank(teams.get(j), games));
            }
            Collections.sort(ranks);
            output.println(tournamentName);
            for (int j = 0; j < ranks.size(); ++j) {
                output.println((j + 1) + ") " + ranks.get(j));
                output.flush();
            }
            if (i < n - 1) {
                output.println();
            }
        }
        output.close();
    }
}

