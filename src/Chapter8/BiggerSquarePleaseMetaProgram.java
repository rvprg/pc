import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiggerSquarePleaseMetaProgram {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final String indend8 = "        ";
    private static final String indend4 = "    ";

    private static void printProgram(Map<Integer, List<List<Integer>>> solutions) {
        System.out.println("import java.io.BufferedReader;");
        System.out.println("import java.io.InputStreamReader;");
        System.out.println();
        System.out.println("public class BiggerSquarePlease {");
        System.out.println("    private static final BufferedReader reader = new BufferedReader(");
        System.out.println("            new InputStreamReader(System.in));");
        System.out.println();
        System.out.println("    private static final int[][][] table = new int[][][] {");

        for (int i = 0; i <= 50; ++i) {
            List<List<Integer>> solution = solutions.get(i);
            if (solution == null || solution.size() == 0) {
                System.out.println(indend8 + "{{}}, //" + i);
                continue;
            }
            System.out.print(indend8 + "{");
            for (List<Integer> s : solution) {
                System.out.printf("{%d, %d, %d},", s.get(0), s.get(1), s.get(2));
            }
            System.out.println("}, //" + i);
        }
        System.out.println(indend4 + "};");
        System.out.println();
        System.out.println(indend4 + "public static void main(String[] args) throws Exception {");
        System.out.println(indend4 + "    int n = Integer.parseInt(reader.readLine().trim());");
        System.out.println(indend4 + "    for (int i = 0; i < n; ++i) {");
        System.out.println(indend4 + "        int m = Integer.parseInt(reader.readLine().trim());");
        System.out.println(indend4 + "        int[][] solution = table[m];");
        System.out.println(indend4 + "        System.out.println(solution.length);");
        System.out.println(indend4 + "        for (int[] s : solution) {");
        System.out.println(indend4 + "          System.out.println(s[0] + \" \" + s[1] + \" \" + s[2]);");
        System.out.println(indend4 + "        }");
        System.out.println(indend4 + "    }");
        System.out.println(indend4 + "}");
        System.out.println("}");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        String currentLine;
        Map<Integer, List<List<Integer>>> solutions = new HashMap<>();
        while ((currentLine = reader.readLine()) != null) {
            int n = Integer.parseInt(currentLine.trim());
            int s = Integer.parseInt(reader.readLine().trim());
            List<List<Integer>> solution = new ArrayList<>();
            for (int i = 0; i < s; ++i) {
                solution.add(stream(reader.readLine().trim().split(" "))
                        .filter(x -> !x.equals(""))
                        .map(Integer::parseInt)
                        .collect(toList()));
            }
            solutions.put(n, solution);
        }
        printProgram(solutions);
    }
}

