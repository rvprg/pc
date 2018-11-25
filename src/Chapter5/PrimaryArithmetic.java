import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PrimaryArithmetic {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static int[] asArray(String input, int pad) {
        int[] a = new int[input.length() + pad];
        for (int i = 0; i < input.length(); ++i) {
            a[i] = input.charAt(input.length() - i - 1) - '0';
        }
        return a;
    }

    public static int count(List<String> input) {
        int[] a = asArray(input.get(0), 0);
        int[] b = asArray(input.get(1),
                input.get(0).length() - input.get(1).length());
        int carry = 0;
        int count = 0;
        for (int i = 0; i < a.length; ++i) {
            int c = a[i] + b[i] + carry;
            if (c >= 10) {
                carry = 1;
                count++;
            } else {
                carry = 0;
            }
        }
        return count;
    }

    public static String toMessage(int count) {
        if (count == 0) {
            return "No carry operation.";
        } else if (count == 1) {
            return "1 carry operation.";
        } else {
            return count + " carry operations.";
        }
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            List<String> input = Arrays
                    .stream(currentLine.trim().split(" "))
                    .filter(x -> !x.equals(" "))
                    .sorted(Comparator.comparing(String::length).reversed())
                    .collect(toList());
            if (input.get(0).equals("0") && input.get(1).equals("0")) {
                break;
            }
            System.out.println(toMessage(count(input)));
        }
    }
}
