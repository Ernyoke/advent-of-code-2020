package dev.esz.aoc.day10;

import java.util.*;
import java.util.stream.Collectors;

public interface Day10 {
    static int part1(List<Integer> numbers) {
        List<Integer> jolts = new ArrayList<>(numbers);
        jolts.sort(Comparator.naturalOrder());
        return getDifferences(jolts);
    }

    Map<String, Integer> cache = new HashMap<>();

    private static int getDifferences(List<Integer> jolts) {
        int previous = 0;
        int diffByOne = 0;
        int diffByThree = 0;
        for (int jolt : jolts) {
            int difference = jolt - previous;
            if (difference == 1) {
                diffByOne++;
            }
            if (difference == 3) {
                diffByThree++;
            }
            previous = jolt;
        }
        return diffByOne * (diffByThree + 1);
    }

    static int part2(List<Integer> numbers) {
        List<Integer> jolts = new ArrayList<>(numbers);
        jolts.add(0);
        jolts.sort(Comparator.naturalOrder());
        jolts.add(jolts.get(jolts.size() - 1) + 3);
        return getArrangements(jolts);
    }

    private static int getArrangements(List<Integer> jolts) {
        String key = jolts.stream().map(Object::toString).collect(Collectors.joining(", "));
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int result = 1;
        for (int i = 1; i < jolts.size() - 1; i++) {
            if (jolts.get(i + 1) - jolts.get(i - 1) <= 3) {
                List<Integer> newJolts = new ArrayList<>(List.of(jolts.get(i - 1)));
                newJolts.addAll(jolts.subList(i + 1, jolts.size()));
                result += getArrangements(newJolts);
            }
        }
        cache.put(key, result);
        return result;
    }
}
