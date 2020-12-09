package dev.esz.aoc.day09;

import java.util.*;

public interface Day09 {
    static long part1(List<Long> numbers, int preamble) {
        return getFistInvalidNumber(numbers, preamble);
    }

    static long part2(List<Long> numbers, int preamble) {
        long invalidNumber = getFistInvalidNumber(numbers, preamble);
        return getContiguousSumForNumber(numbers, invalidNumber);
    }

    private static long getFistInvalidNumber(List<Long> numbers, int preamble) {
        Queue<Long> preambleQueue = new ArrayDeque<>();
        for (int i = 0; i < preamble; i++) {
            preambleQueue.offer(numbers.get(i));
        }

        for (int i = preamble; i < numbers.size(); i++) {
            long number = numbers.get(i);
            if (isSumInPreambleQueue(preambleQueue, number)) {
                preambleQueue.poll();
                preambleQueue.offer(number);
            } else {
                return number;
            }
        }

        throw new IllegalStateException();
    }

    private static boolean isSumInPreambleQueue(Queue<Long> preambleQueue, long number) {
        for (long preambleValue : preambleQueue) {
            if (preambleQueue.contains(number - preambleValue)) {
                return true;
            }
        }
        return false;
    }

    private static long getContiguousSumForNumber(List<Long> numbers, long number) {
        Queue<Long> queue = new ArrayDeque<>();
        for (long currentNumber : numbers) {
            queue.offer(currentNumber);
            while (!queue.isEmpty()) {
                long sum = queue.stream().mapToLong(i -> i).sum();
                if (sum == number) {
                    LongSummaryStatistics statistics = queue.stream().mapToLong(i -> i).summaryStatistics();
                    return statistics.getMin() + statistics.getMax();
                } else {
                    if (sum > number) {
                        queue.poll();
                    } else {
                        break;
                    }
                }
            }
        }
        throw new IllegalStateException();
    }
}
