package com.rvprg.pc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class StackEmUp {
    private static final int DECK_SIZE = 52;

    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static final Map<Integer, String> map = initialize();

    private static Map<Integer, String> initialize() {
        Map<Integer, String> map = new HashMap<>();
        int k = 0;
        for (String suit : Stream.of("Clubs", "Diamonds", "Hearts", "Spades")
                .collect(toList())) {
            for (int i = 2; i <= 10; ++i) {
                map.put(Integer.valueOf(k++), i + " of " + suit);
            }
            map.put(Integer.valueOf(k++), "Jack of " + suit);
            map.put(Integer.valueOf(k++), "Queen of " + suit);
            map.put(Integer.valueOf(k++), "King of " + suit);
            map.put(Integer.valueOf(k++), "Ace of " + suit);
        }
        return map;
    }

    private static List<Integer> newDeck() {
        return Stream.iterate(0, i -> i + 1).limit(DECK_SIZE)
                .collect(toList());
    }

    private static List<Integer> apply(List<Integer> deck,
            List<Integer> shuffle) {
        List<Integer> output = newDeck();
        for (int j = 0; j < shuffle.size(); ++j) {
            output.set(j, deck.get(shuffle.get(j)));
        }
        return output;
    }

    private static List<Integer> shuffle(List<Integer> shuffleIndexes,
            List<List<Integer>> shuffles) {
        List<Integer> deck = newDeck();
        for (Integer i : shuffleIndexes) {
            deck = apply(deck, shuffles.get(i));
        }
        return deck;
    }

    public static void main(String[] args) throws IOException {
        int cases = Integer.parseInt(reader.readLine().trim());
        reader.readLine();
        for (int i = 0; i < cases; ++i) {
            int n = Integer.parseInt(reader.readLine().trim());
            List<Integer> shuffles = new ArrayList<>();
            String currentLine;
            while (shuffles.size() < n * DECK_SIZE) {
                currentLine = reader.readLine().trim();
                shuffles.addAll(stream(currentLine.split(" "))
                        .filter(x -> !x.equals(""))
                        .map(Integer::parseInt)
                        .map(x -> x - 1)
                        .collect(toList()));
            }
            List<List<Integer>> shuffleList = new ArrayList<List<Integer>>();
            for (int j = 0; j < n; ++j) {
                shuffleList.add(shuffles.subList(j * DECK_SIZE,
                        j * DECK_SIZE + DECK_SIZE));
            }
            List<Integer> shuffleIndexes = new ArrayList<>();
            while ((currentLine = reader.readLine()) != null &&
                    !currentLine.trim().equalsIgnoreCase("")) {
                shuffleIndexes.add(Integer.parseInt(currentLine.trim()) - 1);
            }
            shuffle(shuffleIndexes, shuffleList)
                    .forEach(x -> System.out.println(map.get(x)));
            if (i < cases - 1) {
                System.out.println();
            }
        }

    }

}
