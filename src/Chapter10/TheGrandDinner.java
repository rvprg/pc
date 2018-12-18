import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TheGrandDinner {
    static class Tuple {
        final int v1;
        int v2;

        public Tuple(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public static List<List<Integer>> solve(List<Tuple> teams, List<Tuple> tables) {
        List<List<Integer>> solution = new ArrayList<>();
        teams.forEach(x -> solution.add(new ArrayList<>()));
        teams.sort((x, y) -> Integer.compare(y.v2, x.v2));
        for (int i = 0; i < teams.size(); ++i) {
            tables.sort((x, y) -> Integer.compare(y.v2, x.v2));
            for (int j = 0; j < tables.size() && teams.get(i).v2 > 0; ++j) {
                solution.get(teams.get(i).v1 - 1).add(tables.get(j).v1);
                tables.get(j).v2--;
                teams.get(i).v2--;
                if (tables.get(j).v2 < 0) {
                    return null;
                }
            }
            if (teams.get(i).v2 > 0) {
                return null;
            }
        }
        return solution;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = 0;
        int m = 0;

        while (true) {
            m = s.nextInt();
            n = s.nextInt();
            if (m == 0 && n == 0) {
                break;
            }
            List<Tuple> teams = new ArrayList<>();
            List<Tuple> tables = new ArrayList<>();
            for (int i = 0; i < m; ++i) {
                teams.add(new Tuple(i + 1, s.nextInt()));
            }
            for (int i = 0; i < n; ++i) {
                tables.add(new Tuple(i + 1, s.nextInt()));
            }

            List<List<Integer>> solution = solve(teams, tables);
            if (solution != null) {
                System.out.println(1);
                for (int i = 0; i < m; i++) {
                    System.out.println(solution.get(i)
                            .stream().map(String::valueOf)
                            .collect(Collectors.joining(" ")));
                }
            } else {
                System.out.println(0);
            }
        }
    }

}

