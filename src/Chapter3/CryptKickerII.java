import static java.lang.Math.abs;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CryptKickerII {
    private static final BufferedReader reader = 
        new BufferedReader(new InputStreamReader(System.in));
    private final int[] mapping = new int[128];
    private static final String pangram = "the quick brown fox jumps over the lazy dog";
    private static final String pangramSpaces = pangram.replaceAll("[^ ]", ".");
    private static final List<Integer> pangramPattern = getPattern(pangram);

    private static List<Integer> getPattern(String word) {
        return range(0, word.length()).map(i -> word.indexOf(word.charAt(i)))
                .boxed().collect(toList());
    }

    private static boolean compare(List<Integer> a, List<Integer> b) {
        return a.size() == b.size() && range(0, a.size())
                .map(i -> abs(a.get(i) - b.get(i))).sum() == 0;
    }

    private boolean isPangram(String input) {
        String line = String.join(" ", Arrays.stream(input.trim()
                .split(" ")).filter(x -> !x.equals("")).collect(toList()));
        return compare(pangramPattern, getPattern(line.toString())) &&
                line.replaceAll("[^ ]", ".").equalsIgnoreCase(pangramSpaces);
    }

    public List<String> decrypt(List<String> input) {
        Arrays.fill(mapping, 0);
        List<String> output = new ArrayList<String>();
        String encryptedPangram = input.stream()
                .filter(x -> isPangram(x)).findFirst().orElse("");
        if (encryptedPangram.equalsIgnoreCase("")) {
            output.add("No solution.");
            return output;
        }

        for (int i = 0; i < encryptedPangram.length(); ++i) {
            mapping[encryptedPangram.charAt(i)] = pangram.charAt(i);
        }

        return input.stream().map(x -> {
            StringBuilder result = new StringBuilder();
            x.chars().map(c -> c != ' ' ? mapping[c] : c)
                    .forEachOrdered(c -> result.append((char) c));
            return result.toString();
        }).collect(toList());
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        final int n = Integer.parseInt(reader.readLine().trim());
        reader.readLine();
        CryptKickerII cryptKicker = new CryptKickerII();
        for (int i = 0; i < n; ++i) {
            List<String> input = new ArrayList<String>();
            while ((currentLine = reader.readLine()) != null &&
                    !currentLine.trim().equalsIgnoreCase("")) {
                input.add(currentLine);
            }
            cryptKicker.decrypt(input).forEach(System.out::println);
            if (i < n - 1) {
                System.out.println();
            }
        }
    }
}
