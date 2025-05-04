import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TheKnightsOfTheRoundTable {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        DecimalFormat df = new DecimalFormat("0.000");
        String line;
        while ((line = reader.readLine()) != null) {
            String[] sides = line.trim().split(" ");
            double a = Double.parseDouble(sides[0]);
            double b = Double.parseDouble(sides[1]);
            double c = Double.parseDouble(sides[2]);
            double s = (a + b + c) / 2.0;
            double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
            double r = 0.0;
            if (Math.abs(area) > 0.00000001) {
                r = area / s;
            }
            System.out.println("The radius of the round table is: " + df.format(r));
        }
    }
}

