import static java.util.Arrays.stream;
import static java.lang.Math.abs;
import static java.util.stream.Collectors.partitioningBy;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

class TheTrip {
    static long calculate(long[] values) {
        if (values.length == 1)
            return 0;
        long total = stream(values).sum();
        long quotient = total / values.length;
        long reminder = total % values.length;
        Map<Boolean, List<Long>> diff = 
            stream(values).map(x -> x - quotient).boxed().collect(partitioningBy(x -> x > 0));
        long sum = abs(diff.get(false).stream().reduce(Long::sum).get());
        long len = diff.get(true).size();
        reminder = len <= reminder ? reminder - len : 0;
        return sum + reminder;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int n = 0;
        while ((n = Integer.parseInt(r.readLine().trim())) > 0) {
            long[] values = r.lines().limit(n).map(x -> x.replaceAll("\\.",
                    "").trim()).mapToLong(Long::parseLong).toArray();
            System.out.println("$" + BigDecimal.valueOf(calculate(values), 2));
        }
    }
}
