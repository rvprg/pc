import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.sqrt;

public class RopeCrisisInRopeland {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    public static final double TWOPI = 2.0 * Math.PI;

    public static boolean crosses(double r, double ax, double ay, double bx, double by) {
        double dx = bx - ax;
        double dy = by - ay;
        double t = -(ax * dx + ay * dy) / (dx * dx + dy * dy);
        double projectionX = ax + t * dx;
        double projectionY = ay + t * dy;
        boolean projectionOnSegment = (t >= 0 && t <= 1);
        double distSqToProjection = projectionX * projectionX + projectionY * projectionY;
        return projectionOnSegment && distSqToProjection < r * r;
    }

    public static void main(String[] args) throws Exception {
        DecimalFormat df = new DecimalFormat("0.000");
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] l = reader.readLine().trim().split(" ");

            double x1 = Double.parseDouble(l[0]);
            double y1 = Double.parseDouble(l[1]);
            double x2 = Double.parseDouble(l[2]);
            double y2 = Double.parseDouble(l[3]);
            double r = Double.parseDouble(l[4]);
            double angleAlpha = acos(
               (x1 * x2 + y1 * y2) / (sqrt(x1 * x1 + y1 * y1) * sqrt(x2 * x2 + y2 * y2)));

            double lx2 = (x1) * (x1);
            double ly2 = (y1) * (y1);
            double len1 = sqrt(lx2 + ly2);
            double seg1 = sqrt(len1 * len1 - r * r);
            double asin1 = Math.asin(seg1 / len1);

            double lx1 = (x2) * (x2);
            double ly1 = (y2) * (y2);
            double len2 = sqrt(lx1 + ly1);
            double seg2 = sqrt(len2 * len2 - r * r);
            double asin2 = Math.asin(seg2 / len2);

            double angle = angleAlpha - asin1 - asin2;
            double arcLen = abs(angle / (TWOPI) * (TWOPI * r));

            double lx = (x2 - x1) * (x2 - x1);
            double ly = (y2 - y1) * (y2 - y1);
            double seg1Seg2len = sqrt(lx + ly);

            if (crosses(r, x1, y1, x2, y2)) {
                System.out.println(df.format(arcLen + seg1 + seg2));
            } else {
                System.out.println(df.format(seg1Seg2len));
            }
        }
    }
}

