import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Fmt {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final int LINE_WIDTH = 72;

    private static List<String> tokenize(String input) {
        List<String> output = new ArrayList<>();
        StringBuilder block = new StringBuilder();
        int pos = 0;
        while (pos < input.length()) {
            if (input.charAt(pos) == ' ') {
                while (pos < input.length() && input.charAt(pos) == ' ') {
                    block.append(input.charAt(pos));
                    pos++;
                }
                output.add(block.toString());
                block = new StringBuilder();
            } else if (input.charAt(pos) == '\n') {
                output.add(new String("\n"));
                pos++;
            } else {
                while (pos < input.length() && input.charAt(pos) != ' ' &&
                        input.charAt(pos) != '\n') {
                    block.append(input.charAt(pos));
                    pos++;
                }
                output.add(block.toString());
                block = new StringBuilder();
            }
        }
        if (block.length() > 0) {
            output.add(block.toString());
        }
        return output;
    }

    private static void flush(List<String> line, StringBuilder output,
            boolean newline) {
        if (line.size() > 1 && line.get(line.size() - 1).startsWith(" ")) {
            line.remove(line.size() - 1);
        }

        StringBuilder lineStr = new StringBuilder();
        for (String x : line) {
            lineStr.append(x);
        }

        output.append(lineStr).append(newline ? "\n" : "");
        line.clear();
    }

    private static String format(List<String> tokens) {
        StringBuilder output = new StringBuilder();
        List<String> line = new ArrayList<>();

        int i = 0;
        while (i < tokens.size()) {
            int currLength = line.stream().map(String::length)
                    .reduce(0, Integer::sum).intValue();
            String token = tokens.get(i);

            if (token.startsWith(" ")) {
                line.add(token);
            } else if (token.equals("\n")) {
                if (i + 1 < tokens.size()) {
                    String next = tokens.get(i + 1);
                    if (next.equals("\n") || next.startsWith(" ") ||
                            line.size() == 0 || (line.size() == 1 &&
                                    line.get(0).startsWith(" "))) {
                        flush(line, output, true);
                    } else if (currLength + next.length() <= LINE_WIDTH) {
                        line.add(" ");
                        line.add(next);
                        ++i;
                    } else {
                        flush(line, output, true);
                    }
                } else {
                    line.add(token);
                }
            } else if (currLength == 0 && token.length() > LINE_WIDTH) {
                line.add(token);
                if (i + 1 < tokens.size()) {
                    flush(line, output, true);
                    i++;
                }
            } else {
                if (currLength + token.length() > LINE_WIDTH) {
                    flush(line, output, true);
                }
                line.add(token);
            }
            ++i;
        }

        if (line.size() > 0) {
            flush(line, output, false);
        }

        return output.toString();
    }

    public static void main(String[] args) throws IOException {
        StringBuilder input = new StringBuilder();
        while (true) {
            int c = reader.read();
            if (c == -1) {
                break;
            }
            input.append((char) c);
        }
        System.out.print(format(tokenize(input.toString())));
    }
}

