import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DogAndGopher {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static long len(double x, double y, double gx, double gy) {
        double lx = (gx - x) * (gx - x);
        double ly = (gy - y) * (gy - y);
        return Math.round(Math.sqrt(lx + ly) * 1000.0);
    }

    public static void main(String[] args) throws IOException {
        DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.trim().isEmpty()) continue;
            String[] l = currentLine.split(" ");
            int n = Integer.parseInt(l[0]);
            double gx = Double.parseDouble(l[1]);
            double gy = Double.parseDouble(l[2]);
            double dx = Double.parseDouble(l[3]);
            double dy = Double.parseDouble(l[4]);
            boolean found = false;
            for (int i = 0; i < n; i++) {
                String[] hole =  reader.readLine().split(" ");
                double x = Double.parseDouble(hole[0]);
                double y = Double.parseDouble(hole[1]);
                long len1 = len(x, y, gx, gy);
                long len2 = len(x, y, dx, dy);
                if ((len1 == 0 || len1*2 <= len2) && !found) {
                    System.out.println("The gopher can escape through the hole at (" 
                        + df.format(x) + "," + df.format(y) + ").");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("The gopher cannot escape.");
            }
        }
    }
}

