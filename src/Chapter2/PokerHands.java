import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerHands {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static class Card {
        final int value;
        final int suit;
        private final static Map<Character, Integer> map = initialize();

        private static Map<Character, Integer> initialize() {
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            for (char c = '2'; c <= '9'; ++c) {
                map.put(c, c - '0');
            }
            map.put('T', 10);
            map.put('J', 11);
            map.put('Q', 12);
            map.put('K', 13);
            map.put('A', 14);
            map.put('C', 1);
            map.put('D', 2);
            map.put('H', 3);
            map.put('S', 4);
            return map;
        }

        public Card(char value, char suit) {
            this.value = map.get(value);
            this.suit = map.get(suit);
        }
    }

    private static class Hand {
        private List<Card> hand;
        private int category = 0;
        private int rank = 0;

        public Hand(List<Card> hand) {
            this.hand = hand;
            Collections.sort(hand, (x, y) -> Integer.compare(x.value, y.value));
            straightFlush();
            fourOfAKind();
            fullHouse();
            flush();
            straight();
            threeOfAKind();
            twoPairs();
            pair();
            highCard();
        }

        private void highCard() {
            if (category != 0) {
                return;
            }

            category = 1;
            for (int i = hand.size() - 1; i >= 0; --i) {
                rank = (rank * 100) + hand.get(i).value;
            }
        }

        private void pair() {
            if (category != 0) {
                return;
            }

            Map<Integer, Integer> count = groups();
            List<Integer> pairs = new ArrayList<Integer>();
            for (Integer c : count.keySet()) {
                if (count.get(c) == 2) {
                    pairs.add(c);
                }
            }

            if (pairs.size() == 1) {
                category = 2;
                rank = pairs.get(0);
                for (int i = hand.size() - 1; i >= 0; --i) {
                    if (hand.get(i).value != pairs.get(0)) {
                        rank = (rank * 100) + hand.get(i).value;
                    }
                }
            }
        }

        private void twoPairs() {
            if (category != 0) {
                return;
            }

            Map<Integer, Integer> count = groups();
            List<Integer> pairs = new ArrayList<Integer>();
            int singleton = 0;
            for (Integer c : count.keySet()) {
                if (count.get(c) == 2) {
                    pairs.add(c);
                } else if (count.get(c) == 1) {
                    singleton = c;
                }
            }

            if (pairs.size() == 2) {
                category = 3;
                Collections.sort(pairs);
                for (int i = pairs.size() - 1; i >= 0; --i) {
                    rank = (rank * 100) + pairs.get(i);
                }
                rank = (rank * 100) + singleton;
            }
        }

        private void threeOfAKind() {
            if (category != 0) {
                return;
            }

            Map<Integer, Integer> count = groups();
            for (Integer c : count.keySet()) {
                if (count.get(c) == 3) {
                    category = 4;
                    rank = c;
                    for (int i = hand.size() - 1; i >= 0; --i) {
                        if (hand.get(i).value != c) {
                            rank = (rank * 100) + hand.get(i).value;
                        }
                    }
                    return;
                }
            }
        }

        private void straight() {
            if (category != 0) {
                return;
            }
            int value = hand.get(0).value;
            for (int i = 1; i < hand.size(); ++i) {
                if (hand.get(i).value - value == 1) {
                    value = hand.get(i).value;
                    continue;
                } else {
                    return;
                }
            }
            category = 5;
            rank = hand.get(hand.size() - 1).value;
        }

        private void flush() {
            if (category != 0) {
                return;
            }

            int suit = hand.get(0).suit;
            for (int i = 1; i < hand.size(); ++i) {
                if (hand.get(i).suit == suit) {
                    continue;
                } else {
                    return;
                }
            }

            category = 6;
            for (int i = hand.size() - 1; i >= 0; --i) {
                rank = (rank * 100) + hand.get(i).value;
            }
        }

        private void fourOfAKind() {
            fourOfAKindFullHouse(4, 8);
        }

        private void fullHouse() {
            fourOfAKindFullHouse(3, 7);
        }

        private void fourOfAKindFullHouse(int n, int cat) {
            if (category != 0) {
                return;
            }

            Map<Integer, Integer> count = groups();
            if (count.size() != 2) {
                return;
            }

            List<Integer> keys = new ArrayList<>(count.keySet());
            if (count.get(keys.get(0)) == n) {
                category = cat;
                rank = keys.get(0);
            } else if (count.get(keys.get(1)) == n) {
                category = cat;
                rank = keys.get(1);
            }
        }

        private void straightFlush() {
            if (category != 0) {
                return;
            }

            int suit = hand.get(0).suit;
            int value = hand.get(0).value;
            for (int i = 1; i < hand.size(); ++i) {
                if (hand.get(i).suit == suit &&
                        hand.get(i).value - value == 1) {
                    value = hand.get(i).value;
                    continue;
                } else {
                    return;
                }
            }

            category = 9;
            rank = hand.get(hand.size() - 1).value;
        }

        private Map<Integer, Integer> groups() {
            Map<Integer, Integer> count = new HashMap<Integer, Integer>();
            for (Card c : hand) {
                count.putIfAbsent(c.value, 0);
                count.put(c.value, count.get(c.value) + 1);
            }
            return count;
        }

    }

    private static int compare(Hand black, Hand white) {
        int compareCategory = Integer.compare(black.category, white.category);
        if (compareCategory == 0) {
            return Integer.compare(black.rank, white.rank);
        }
        return compareCategory;
    }

    private static int compare(String currentLine) {
        List<String> hands = Arrays.stream(currentLine.split(" "))
                .filter(x -> !x.equals("")).collect(toList());
        return compare(getHand(hands.subList(0, 5)),
                getHand(hands.subList(5, 10)));
    }

    private static Hand getHand(List<String> h) {
        return new Hand(h.stream().map(x -> new Card(x.charAt(0), x.charAt(1)))
                .collect(toList()));
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            int cmp = compare(currentLine);
            if (cmp == 0) {
                System.out.println("Tie.");
            } else {
                System.out.println(cmp > 0 ? "Black wins." : "White wins.");
            }
        }
    }

}

