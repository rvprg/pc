import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CDVII {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    private static Pattern pattern = Pattern.compile(
            "([\\d\\w]+)\\s+(\\d\\d):(\\d\\d):(\\d\\d):(\\d\\d)\\s+(\\w+)\\s+(\\d+)");

    private static class Event {
        private final String license;
        private final LocalDateTime timestamp;
        private final String action;
        private final int location;

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getAction() {
            return action;
        }

        public String getLicense() {
            return license;
        }

        public Event(String line) {
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            license = matcher.group(1);
            timestamp = LocalDateTime.of(LocalDate.now().getYear(),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5)));
            action = matcher.group(6).toLowerCase();
            location = Integer.parseInt(matcher.group(7));
        }
    }

    private static List<Entry<String, AtomicInteger>> getBills(
            List<Integer> rate,
            List<Event> events) {
        Map<String, AtomicInteger> bills = new HashMap<>();
        Map<String, List<Event>> groups = events.stream().collect(
                Collectors.groupingBy(Event::getLicense, Collectors.toList()));

        for (Entry<String, List<Event>> entry : groups.entrySet()) {
            String license = entry.getKey();
            Map<String, List<Event>> actions = entry.getValue().stream()
                    .collect(Collectors.groupingBy(Event::getAction,
                            Collectors.toList()));
            List<Event> enters = actions.get("enter");
            List<Event> exits = actions.get("exit");
            if (enters == null || exits == null) {
                continue;
            }
            exits.sort(Comparator.comparing(Event::getTimestamp));
            enters.sort(Comparator.comparing(Event::getTimestamp));
            Event[] interval = findInterval(enters, exits);
            while (interval != null) {
                Event enter = interval[0];
                Event exit = interval[1];
                bills.putIfAbsent(license, new AtomicInteger(200));
                int distance = Math.abs(exit.location - enter.location);
                bills.get(license).addAndGet(
                        rate.get(enter.timestamp.getHour()) * distance + 100);
                interval = findInterval(enters, exits);
            }
        }

        return bills.entrySet().stream()
                .sorted((x, y) -> x.getKey().compareTo(y.getKey()))
                .collect(toList());

    }

    private static Event[] findInterval(List<Event> enters, List<Event> exits) {
        while (!enters.isEmpty() && !exits.isEmpty()) {
            final Event enter = enters.remove(0);
            final Event exit = exits.stream()
                    .filter(x -> x.timestamp.isAfter(enter.timestamp))
                    .findFirst()
                    .orElse(null);

            if (exit == null) {
                continue;
            }

            boolean overlap = enters.stream()
                    .anyMatch(x -> x.timestamp.isAfter(enter.timestamp) &&
                            x.timestamp.isBefore(exit.timestamp));

            if (!overlap) {
                exits.remove(exit);
                return new Event[] { enter, exit };
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        reader.readLine();
        for (int i = 0; i < n; ++i) {
            List<Integer> rate = stream(reader.readLine().trim().split(" "))
                    .filter(x -> !x.equals("")).map(Integer::parseInt)
                    .collect(toList());
            String currentLine;
            List<Event> events = new ArrayList<>();
            while ((currentLine = reader.readLine()) != null &&
                    !currentLine.trim().equals("")) {
                events.add(new Event(currentLine));
            }
            for (Entry<String, AtomicInteger> e : getBills(rate, events)) {
                System.out.println(e.getKey() + " $" +
                        BigDecimal.valueOf(e.getValue().intValue(), 2));
            }
            if (i < n - 1) {
                System.out.println();
            }
        }

    }
}

