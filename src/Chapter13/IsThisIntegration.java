import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class IsThisIntegration {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String line;
        DecimalFormat format = new DecimalFormat("0.000");
        format.setRoundingMode(RoundingMode.HALF_EVEN);
        while ((line = reader.readLine()) != null) {
            double r = Double.parseDouble(line);

            double b = r * r - r * r * Math.PI / 2 + r * r * Math.PI 
                / 6 + r * r * Math.PI / 6 - r * r * Math.sqrt(3) / 4;
            double a = r * r - r * r * Math.PI / 4 - 2 * b;
            double c = r * r - 4 * a - 4 * b;

            System.out.println(format.format(c) + " " 
                + format.format(a*4) + " " 
                + format.format(b*4));
        }
    }
}
