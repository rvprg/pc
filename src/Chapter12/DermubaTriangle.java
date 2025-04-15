import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class DermubaTriangle {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static final double SQRT3 = Math.sqrt(3);
    private static double D2 = SQRT3 / 6.0;
    private static double D1 = SQRT3 / 2.0 - D2;

    private static double adjust(int sn, int n) {
        if (sn % 2 == 0) {
            return n % 2 == 0 ? D2 : D1;
        } else {
            return n % 2 == 0 ? D1 : D2;
        }
    }

    private static double calculate(int n, int m) {
        int sn = (int) Math.floor(Math.sqrt((double) n));
        int sm = (int) Math.floor(Math.sqrt((double) m));
        double d = (sm - sn) / 2.0;

        double l1 = -(n - sn * sn) / 2.0;
        double ll = l1 - d;
        double l2 = +(m - sm * sm) / 2.0;
        double x = l2 + ll;

        double y = (sm - sn) * SQRT3 / 2.0;
        y += adjust(sn, n);
        y -= adjust(sm, m);
        return Math.sqrt(y * y + x * x);
    }

    public static void main(String[] args) throws IOException {
        DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        String currentLine;
        while ((currentLine = reader.readLine()) != null &&
                !currentLine.trim().isEmpty()) {
            Scanner s = new Scanner(currentLine);
            int n = s.nextInt();
            int m = s.nextInt();
            double distance = calculate(n, m);
            System.out.println(df.format(distance));
        }
    }
}

