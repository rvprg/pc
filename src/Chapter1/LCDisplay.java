import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import static java.lang.String.join;
import static java.util.Collections.nCopies;
import java.util.function.Function;
import static java.util.stream.Collectors.joining;
import java.util.function.BiFunction;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

class LCDisplay {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final String INPUT_END = "0 0";
    private static final String EMPTY = "";
    private static final String SPACE = " ";
    private static final byte[] pattern = new byte[] {
            0x77, 0x03, 0x5d, 0x1f, 0x2b, 0x3e, 0x7e, 0x07, 0x7f, 0x3f
    };

    private static List<String> segments(final String digits, final int scale) {
        Function<String, String> g = x -> join(EMPTY, nCopies(scale, x));
        BiFunction<Stream<Integer>, Byte, Stream<String>> h = 
            (m, x) -> m.map(mask -> (x & mask) > 0 ? "|" : SPACE);
        BiFunction<Stream<Integer>, Byte, String> k = 
            (m, d) -> SPACE + h.apply(m, d).map(x -> g.apply(x)).collect(joining(SPACE)) + SPACE;
        final int digitHeight = scale * 2 + 3;
        Function<Byte, Stream<String>> f = x -> Arrays.asList(
          of(k.apply(of(0x40, 0x20), x)),
          nCopies(scale, h.apply(of(0x10, 0x08, 0x04), x)
              .collect(joining(g.apply(SPACE))).replace('|', '-')).stream(),
          of(k.apply(of(0x02, 0x01), x)),
          of(join(EMPTY, nCopies(digitHeight, SPACE))))
            .stream().reduce(Stream::concat).get();

        List<String> segments = digits.chars().map(x -> x - '0').boxed()
            .flatMap(x -> f.apply(pattern[x])).collect(toList());

        return rangeClosed(1, digitHeight).boxed()
            .map(j -> digitHeight - j).map(j -> range(0, segments.size() - 1).boxed()
            .map(i -> Character.toString(segments.get(i).charAt(j))).collect(joining())).collect(toList());
    }

    public static void main(String[] args) throws IOException {
        String currentLine = INPUT_END;
        while ((currentLine = reader.readLine()) != null && 
                !currentLine.trim().equalsIgnoreCase(INPUT_END)) {
            List<String> input = Arrays.stream(currentLine.trim().split(SPACE))
                .filter(x -> !x.equals("")).collect(Collectors.toList());
            segments(input.get(1), Integer.valueOf(input.get(0))).stream()
                .forEach(System.out::println);
            System.out.println();
        }
    }
}
