package dev.esz.aoc.day13;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface Day13 {
    static long part1(int estimate, List<String> rawDepartures) {
        Pattern pattern = Pattern.compile("[0-9]+");
        List<Integer> departures = rawDepartures.stream()
                .filter(departure -> pattern.matcher(departure).matches())
                .map(Integer::valueOf)
                .collect(Collectors.toUnmodifiableList());
        Map<Integer, Integer> waitTimes = mapToWaitTime(estimate, departures);
        var minEntry = waitTimes.entrySet().stream()
                .min(Comparator.comparingLong(Map.Entry::getValue))
                .orElseThrow();
        return minEntry.getKey().longValue() * minEntry.getValue();
    }

    static long part2(List<String> rawDepartures) {
        Pattern pattern = Pattern.compile("[0-9]+");
        List<Integer> departures = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < rawDepartures.size(); i++) {
            String departure = rawDepartures.get(i);
            if (pattern.matcher(departure).matches()) {
                departures.add(Integer.valueOf(departure));
                indexes.add(Integer.parseInt(departure) - i);
            }
        }
        return chineseReminder(departures, indexes);
    }

    private static Map<Integer, Integer> mapToWaitTime(int estimate, List<Integer> departures) {
        return departures.stream()
                .collect(Collectors.toUnmodifiableMap(key -> key, v -> computeWaitTime(estimate, v)));
    }

    private static int computeWaitTime(int estimate, int departure) {
        int div = estimate / departure;
        return ((div + 1) * departure - estimate) % departure;
    }

    private static long chineseReminder(List<Integer> numbers, List<Integer> dividers) {
        long product = numbers.stream()
                .mapToLong(i -> i)
                .reduce(1, (a, b) -> a * b);
        long sum = 0;

        for (int i = 0; i < numbers.size(); i++) {
            long partialProduct = product / numbers.get(i);
            long inverse = BigInteger.valueOf(partialProduct)
                    .modInverse(BigInteger.valueOf(numbers.get(i)))
                    .longValue();
            sum += partialProduct * inverse * dividers.get(i);
        }

        return sum % product;
    }
}
