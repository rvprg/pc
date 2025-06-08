import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TheLargestSmallestBox {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String line;
        DecimalFormat up = new DecimalFormat("0.000");
        up.setRoundingMode(RoundingMode.UP);
        DecimalFormat halfDown = new DecimalFormat("0.000");
        halfDown.setRoundingMode(RoundingMode.HALF_DOWN);
        while ((line = reader.readLine()) != null) {
            String[] s = line.trim().split(" ");
            double l = Double.parseDouble(s[0]);
            double w = Double.parseDouble(s[1]);
            double m = Math.min(l / 2.0, w / 2.0);

            double s1 = 1 / 6.0 * (-Math.sqrt(l * l - l * w + w * w) + l + w);
            double s2 = 1 / 6.0 * (Math.sqrt(l * l - l * w + w * w) + l + w);

            System.out.println(halfDown.format(s1) + " " 
                + up.format(0) + " " + up.format(Math.min(m, s2)));
        }
    }
}

