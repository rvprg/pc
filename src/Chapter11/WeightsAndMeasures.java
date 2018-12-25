import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WeightsAndMeasures {
    private static class Turtle {
        int weight;
        int strength;

        public Turtle(int weight, int strength) {
            this.weight = weight;
            this.strength = strength;
        }
    }

    public static int solve(List<Turtle> input) {
        input.sort(Comparator.comparingInt(x -> x.strength));
        int[][] height = new int[2][input.size() + 1];
        height[0][0] = 0;
        for (int i = 1; i <= input.size(); ++i) {
            height[0][i] = Integer.MAX_VALUE;
            height[1][i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= input.size(); ++i) {
            Turtle t = input.get(i - 1);
            for (int k = i; k >= 1; --k) {
                if (height[0][k - 1] <= (t.strength - t.weight)) {
                    height[1][k] = Math.min(height[0][k], height[0][k - 1] + t.weight);
                } else {
                    height[1][k] = height[0][k];
                }
            }
            System.arraycopy(height[1], 0, height[0], 0, height[1].length);
            for (int k = 1; k <= input.size(); ++k) {
                height[1][k] = Integer.MAX_VALUE;
            }
        }
        for (int i = input.size(); i >= 0; --i) {
            if (height[0][i] < Integer.MAX_VALUE) {
                return i;
            }
        }
        return 1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<Turtle> input = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            Scanner scanner = new Scanner(line);
            input.add(new Turtle(scanner.nextInt(), scanner.nextInt()));
        }
        System.out.println(solve(input));
    }
}


