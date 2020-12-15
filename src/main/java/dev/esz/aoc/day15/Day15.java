package dev.esz.aoc.day15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Day15 {
    static int part1(List<Integer> numbers) {
        return computeNth(numbers, 2020);
    }

    static int part2(List<Integer> numbers, int n) {
        return computeNth(numbers, n);
    }

    private static int computeNth(List<Integer> numbers, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (; i < numbers.size() - 1; i++) {
            map.put(numbers.get(i), i + 1);
        }

        int latest = numbers.get(i++);
        int result = latest;
        for (; i <= n; i++) {
            int newNumber =  map.containsKey(latest) ?  i - map.get(latest) : 0;
            map.put(latest, i);
            result = latest;
            latest = newNumber;
        }

        return result;
    }
}
