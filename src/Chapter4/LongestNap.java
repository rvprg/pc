import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LongestNap {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static final int START = 0;
    private static final int END = 1;

    private final static Pattern pattern = Pattern
            .compile("(\\d\\d:\\d\\d)\\s+(\\d\\d:\\d\\d)");

    private final static DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("HH:mm");

    private static final LocalTime[] parseTime(String line) {
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return new LocalTime[] {
                LocalTime.parse(matcher.group(1), formatter),
                LocalTime.parse(matcher.group(2), formatter)
        };
    }

    private static final List<LocalTime[]> combine(
            List<LocalTime[]> intervals) {
        List<LocalTime[]> intr = new ArrayList<>(intervals);
        intr.sort((x, y) -> x[START].compareTo(y[START]));
        List<LocalTime[]> res = new ArrayList<>();

        while (!intr.isEmpty()) {
            LocalTime[] curr = intr.remove(0);
            while (!intr.isEmpty()) {
                if (intr.get(0)[START].isBefore(curr[END]) ||
                        intr.get(0)[START].equals(curr[END])) {
                    LocalTime[] next = intr.remove(0);
                    if (curr[END].isAfter(next[START]) ||
                            curr[END].equals(next[START])) {
                        if (curr[END].isBefore(next[END])) {
                            curr[END] = next[END];
                        }
                    }
                } else {
                    break;
                }
            }
            res.add(curr);
        }

        return res;
    }

    private static LocalTime[] findLongest(List<LocalTime[]> intervals) {
        if (intervals.size() == 0) {
            return new LocalTime[] { LocalTime.of(10, 0),
                    LocalTime.of(8, 0) };
        }

        LocalTime earliest = intervals.get(0)[START];
        LocalTime latest = intervals.get(intervals.size() - 1)[END];

        long i1 = earliest.toSecondOfDay() -
                LocalTime.of(10, 0).toSecondOfDay();
        long i2 = LocalTime.of(18, 0).toSecondOfDay() - latest.toSecondOfDay();

        LocalTime[] result = (i1 < i2)
                ? new LocalTime[] { latest, LocalTime.ofSecondOfDay(i2) }
                : new LocalTime[] { LocalTime.of(10, 0),
                        LocalTime.ofSecondOfDay(i1) };

        for (int i = 0; i < intervals.size() - 1; ++i) {
            long interval = intervals.get(i + 1)[START].toSecondOfDay() -
                    intervals.get(i)[END].toSecondOfDay();
            if (interval >= result[1].toSecondOfDay()) {
                boolean same = interval == result[1].toSecondOfDay();
                result[1] = LocalTime.ofSecondOfDay(interval);
                if (same && result[0].isAfter(intervals.get(i)[END]) || !same) {
                    result[0] = intervals.get(i)[END];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        String currentLine;
        int d = 1;
        while ((currentLine = reader.readLine()) != null) {
            int appointments = Integer.parseInt(currentLine.trim());
            List<LocalTime[]> intervals = new ArrayList<>();
            for (int i = 0; i < appointments; ++i) {
                intervals.add(parseTime(reader.readLine()));
            }
            LocalTime[] result = findLongest(combine(intervals));
            System.out.println("Day #" + d + ": the longest nap starts at " +
                    formatter.format(result[START]) + " and will last for " +
                    ((result[1].getHour() > 0)
                            ? result[1].getHour() + " hours and " : "") +
                    result[1].getMinute() + " minutes.");
            d++;
        }
    }
}

