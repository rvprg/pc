package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Yahtzee {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private final static int fullhouse = 12;
    private final static int longstraight = 11;
    private final static int shortstraight = 10;
    private final static int fiveofakind = 9;
    private final static int fourofakind = 8;
    private final static int threeofakind = 7;
    private final static int chance = 6;

    private final int[] bestSolutionResult = new int[2];
    private final int[] bestSolution = new int[13];


    public static class Round {
        private final List<Integer> dice;
        private final int allDiceSum;
        private final int[] points = new int[13];
        private final Set<Integer> category;

        public Round(List<Integer> dice) {
            this.allDiceSum = dice.stream().reduce(0, Integer::sum);
            this.dice = dice;
            this.category = new HashSet<>();

            if (isFullhouse()) {
                category.add(fullhouse);
                points[fullhouse] = 40;
            }
            if (isLongStraight()) {
                category.add(longstraight);
                points[longstraight] = 35;
            }
            if (isShortStraight()) {
                category.add(shortstraight);
                points[shortstraight] = 25;
            }
            if (isFiveOfAKind()) {
                category.add(fiveofakind);
                points[fiveofakind] = 50;
            }
            if (isFourOfAKind()) {
                category.add(fourofakind);
                points[fourofakind] = allDiceSum;
            }
            if (isThreeOfAKind()) {
                category.add(threeofakind);
                points[threeofakind] = allDiceSum;
            }

            category.add(chance);
            points[chance] = allDiceSum;

            for (int i = 0; i < 6; ++i) {
                final int v = i + 1;
                if (dice.contains(Integer.valueOf(v))) {
                    category.add(i);
                    points[i] = (int) (dice.stream().filter(x -> x == v)
                            .count() * v);
                }
            }
        }

        private boolean isFullhouse() {
            boolean halfsDifferent = dice.get(0) != dice.get(4);
            boolean twoThree = dice.subList(0, 2).stream().distinct()
                    .count() == 1 &&
                    dice.subList(2, 5).stream().distinct().count() == 1;
            boolean threeTwo = dice.subList(0, 3).stream().distinct()
                    .count() == 1 &&
                    dice.subList(3, 5).stream().distinct().count() == 1;
            return halfsDifferent && (twoThree || threeTwo);
        }

        private int getLongestSequence(List<Integer> list) {
            int longest = 1;
            int currLen = 1;
            for (int i = 0; i < list.size(); ++i) {
                if (i > 0 && list.get(i) - list.get(i - 1) == 1) {
                    currLen += 1;
                } else if (i > 0 && list.get(i) == list.get(i - 1)) {
                    continue;
                } else {
                    longest = Math.max(currLen, longest);
                    currLen = 1;
                }
            }
            return Math.max(currLen, longest);
        }

        private boolean isLongStraight() {
            return getLongestSequence(dice) >= 5;
        }

        private boolean isShortStraight() {
            return getLongestSequence(dice) >= 4;
        }

        private boolean isFiveOfAKind() {
            return (dice.stream().distinct().count() == 1);
        }

        private boolean isFourOfAKind() {
            List<Integer> v1 = dice.subList(0, 4);
            List<Integer> v2 = dice.subList(1, 5);
            return (v1.stream().distinct().count() == 1 ||
                    v2.stream().distinct().count() == 1);
        }

        private boolean isThreeOfAKind() {
            List<Integer> v1 = dice.subList(0, 3);
            List<Integer> v2 = dice.subList(1, 4);
            List<Integer> v3 = dice.subList(2, 5);
            return (v1.stream().distinct().count() == 1 ||
                    v2.stream().distinct().count() == 1 ||
                    v3.stream().distinct().count() == 1);
        }
        @Override
        public boolean equals(Object obj) {
            return dice.equals(((Round) obj).dice);
        }
    }

    Yahtzee(List<List<Integer>> input) {
        solve(input.stream().map(x -> new Round(x)).collect(toList()));
    }


    private void solve(List<Round> input) {
        int[] candidateSolution = new int[13];
        for (int category = 12; category > 8; --category) {
            Round dice = filter(category, input).stream()
                    .min((x, y) -> Integer.compare(x.allDiceSum, y.allDiceSum))
                    .orElse(null);
            if (dice != null) {
                input.remove(dice);
                candidateSolution[category] = dice.points[category];
            }
        }
        search(8, input, candidateSolution);
    }

    private static List<Round> filter(final int category, List<Round> input) {
        List<Round> res = new ArrayList<>();
        Integer categoryInteger = Integer.valueOf(category);
        for (Round d : input) {
            if (d.category.contains(categoryInteger)) {
                res.add(d);
            }
        }
        return res;
    }

    private static int[] total(int[] solution) {
        int[] res = new int[2];
        int sixSum = 0;
        for (int i = 0; i < solution.length; ++i) {
            if (i < 6) {
                sixSum += solution[i];
            }
            res[1] += solution[i];
        }
        if (sixSum >= 63) {
            res[1] += 35;
            res[0] = 35;
        }
        return res;
    }

    private void search(int pos, List<Round> input, int[] solution) {
        if (pos == -1) {
            int[] solutionResult = total(solution);
            if (bestSolutionResult[1] < solutionResult[1]) {
                System.arraycopy(solution, 0, bestSolution, 0,
                        solution.length);
                System.arraycopy(solutionResult, 0, bestSolutionResult, 0,
                        solutionResult.length);
            }
            return;
        }

        List<Round> candidates = filter(pos, input);
        Set<Round> checked = new HashSet<Round>();
        for (Round round : candidates) {
            if (checked.contains(round)) {
                continue;
            }
            solution[pos] = round.points[pos];
            input.remove(round);
            search(pos - 1, input, solution);
            solution[pos] = 0;
            input.add(round);
            checked.add(round);
        }
        if (pos >= 7 || candidates.size() == 0) {
            solution[pos] = 0;
            search(pos - 1, input, solution);
        }
    }

    private String getSolutionString() {
        return Arrays.stream(getSolution()).mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public int[] getSolution() {
        int[] solution = new int[bestSolution.length +
                bestSolutionResult.length];
        System.arraycopy(bestSolution, 0, solution, 0, bestSolution.length);
        System.arraycopy(bestSolutionResult, 0, solution,
                bestSolution.length,
                bestSolutionResult.length);
        return solution;
    }

    public static void main(String[] args) throws IOException {
        String currentLine = null;
        List<List<Integer>> input = new ArrayList<>();
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equals("")) {
            List<Integer> inputLine = stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals(""))
                    .map(Integer::parseInt).sorted()
                    .collect(toList());
            input.add(inputLine);
            if (input.size() == 13) {
                Yahtzee yahtzee = new Yahtzee(input);
                System.out.println(yahtzee.getSolutionString());
                input.clear();
            }
        }
    }
}
