import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EditStepLadders {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static void add(List<String> list, Set<String> dict, String word,
            String newWord) {
        if (newWord.compareTo(word) < 0 && dict.contains(newWord)) {
            list.add(newWord);
        }
    }

    private static List<String> adjacent(Set<String> dict, String word) {
        List<String> words = new ArrayList<>();
        char[] wordArr = word.toCharArray();
        for (int i = 0; i <= word.length(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                add(words, dict, word, new StringBuilder().append(wordArr, 0, i)
                        .append(c)
                        .append(wordArr, i, wordArr.length - i).toString());
                if (i < word.length()) {
                    add(words, dict, word,
                            new StringBuilder().append(wordArr, 0, i)
                                    .append(c)
                                    .append(wordArr, i + 1,
                                            wordArr.length - i - 1)
                                    .toString());
                }
            }
            if (i < word.length()) {
                add(words, dict, word,
                        new StringBuilder().append(wordArr, 0, i)
                                .append(wordArr, i + 1, wordArr.length - i - 1)
                                .toString());
            }
        }
        return words;
    }

    private static int solve(Set<String> dict) {
        List<String> sorted = new ArrayList<>(dict);
        sorted.sort((x, y) -> x.compareTo(y));
        Map<String, Integer> lengths = new HashMap<>();
        Integer answer = 1;
        for (String word : sorted) {
            Integer max = 1;
            for (String source : adjacent(dict, word)) {
                Integer length = lengths.get(source);
                max = Math.max(max, length != null ? length + 1 : -1);
            }
            lengths.put(word, max);
            answer = Math.max(max, answer);
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Set<String> dict = new HashSet<>();
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            dict.add(currentLine.trim());
        }
        System.out.println(solve(dict));
    }
}

