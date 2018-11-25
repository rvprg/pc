import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import java.util.List;
import java.util.Map;
import java.util.Deque;
import static java.util.stream.Collectors.groupingBy;
import java.util.ArrayDeque;
import static java.lang.Math.abs;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Comparator.comparing;

class CryptKicker {
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));

    private final Map<Integer, List<String>> dictionary;
    private final Map<String, List<Integer>> patterns;
    private Map<String, List<Integer>> encryptedPatterns;
    private final Set<String> mappedWords = new HashSet<>();
    private static final int MAX_SIZE = 128;
    private static final int[] NOT_FOUND = new int[0];
    private final int[] dirMapping = new int[MAX_SIZE];
    private final int[] revMapping = new int[MAX_SIZE];
    private final int[] counter = new int[MAX_SIZE];

    public CryptKicker(List<String> inputDictionary) {
        dictionary = inputDictionary.stream()
            .collect(groupingBy(String::length));
        patterns = getPatterns(new ArrayDeque<>(inputDictionary));
    }


    private static List<Integer> getPattern(String word) {
        return range(0, word.length()).map(i -> word.indexOf(word.charAt(i)))
            .boxed().collect(toList());
    }

    private static Map<String, List<Integer>> getPatterns(Deque<String> words) {
        return words.stream().distinct().collect(
            toMap(identity(), CryptKicker::getPattern));
    }

    private static boolean compare(List<Integer> a, List<Integer> b) {
        return a.size() == b.size() && 
            range(0, a.size()).map(i -> abs(a.get(i) - b.get(i))).sum() == 0;
    }

    private void mapWord(String e, String c) {
        mappedWords.add(c);
        for (int i = 0; i < e.length(); ++i) {
            dirMapping[e.charAt(i)] = c.charAt(i);
            counter[e.charAt(i)]++;
            revMapping[c.charAt(i)] = e.charAt(i);
        }
    }

    private void unmapWord(String e, String c) {
        mappedWords.remove(c);
        for (int i = 0; i < e.length(); ++i) {
            counter[e.charAt(i)]--;
            if (counter[e.charAt(i)] == 0) {
                revMapping[dirMapping[e.charAt(i)]] = 0;
                dirMapping[e.charAt(i)] = 0;
            }
        }
    }

    private List<String> filter(String encrypted) {
        List<String> matchedWords = new ArrayList<String>();
        for (String word : dictionary.get(encrypted.length())) {
            if (mappedWords.contains(word) || 
                !compare(encryptedPatterns.get(encrypted), patterns.get(word))) {
                continue;
            }

            boolean matched = true;
            for (int i = 0; i < word.length() && matched; ++i) {
                boolean unmapped = dirMapping[encrypted.charAt(i)] == 0;
                boolean mapped = dirMapping[encrypted.charAt(i)] == word.charAt(i);
                boolean unused = revMapping[word.charAt(i)] == 0;
                matched = (unmapped && unused) || mapped;
            }
            if (matched) {
                matchedWords.add(word);
            }
        }
        return matchedWords;
    }

    private boolean map(Deque<String> encryptedWords) {
        if (encryptedWords.isEmpty()) {
            return true;
        }
        String encryptedWord = encryptedWords.pop();
        List<String> words = filter(encryptedWord);
        for (String candidate : words) {
            mapWord(encryptedWord, candidate);
            if (map(encryptedWords)) {
                return true;
            }
            unmapWord(encryptedWord, candidate);
        }
        encryptedWords.push(encryptedWord);
        return false;
    }

    private int[] findMapping(Deque<String> encryptedWords) {
        encryptedPatterns = getPatterns(encryptedWords);
        mappedWords.clear();
        Arrays.fill(dirMapping, 0);
        Arrays.fill(revMapping, 0);
        Arrays.fill(counter, 0);
        return map(encryptedWords) ? dirMapping : NOT_FOUND;
    }

    public String decrypt(String input) {
        StringBuilder result = new StringBuilder();
        int[] mapping = findMapping(
                new ArrayDeque<>(Arrays.stream(input.trim().split(" "))
                    .filter(x -> !x.equals("")).distinct()
                    .sorted(comparing(String::length).reversed()).collect(toList())));
        input.chars().map(c -> c != ' ' ? (mapping != NOT_FOUND ? mapping[c] : '*') : c)
            .forEachOrdered(x -> result.append((char) x));
        return result.toString();
    }


    public static void main(String[] args) throws IOException {
        String currentLine;
        final int size = Integer.parseInt(reader.readLine().trim());
        final List<String> dictionary = reader.lines().limit(size).collect(toList());
        CryptKicker cryptKicker = new CryptKicker(dictionary);
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().equals("")) {
            System.out.println(cryptKicker.decrypt(currentLine));
        }
    }
}
