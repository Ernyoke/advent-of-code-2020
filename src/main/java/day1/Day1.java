package day1;

import java.util.NoSuchElementException;
import java.util.Set;

public interface Day1 {
    static long part1(Set<Integer> numbers) {
        for (Integer number : numbers) {
            int numberToFind = 2020 - number;
            if (numbers.contains(numberToFind)) {
                return (long) numberToFind * number;
            }
        }
        throw new NoSuchElementException();
    }

    static long part2(Set<Integer> numbers) {
        for (Integer number : numbers) {
            for (Integer number2 : numbers) {
                int numberToFind = 2020 - number - number2;
                if (numbers.contains(numberToFind)) {
                    return (long) number * number2 * numberToFind;
                }
            }
        }
        throw new NoSuchElementException();
    }
}
