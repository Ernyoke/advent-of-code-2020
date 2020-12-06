package dev.esz.aoc.day05;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Day05 {
    static long part1(List<String> codes) {
        return getMax(codes.stream().map(Day05::computeSeatNumber).collect(Collectors.toUnmodifiableSet()));
    }

    // The description of the problem is pretty vague. What we have to do is to find the missing maximum value from
    // the available seat IDs. We know that in total can be 1024 seats on the plane, but some seats from the front or back
    // are missing. We know what is ID of the biggest seat number (from part1). We could also try to find the minimum value,
    // but in the end does not really matter, since our missing seat is somewhere in the middle ("Your seat wasn't at
    // the very front or back, though"). So we iterate from 0 and try to find the biggest value missing from the set.
    static long part2(List<String> codes) {
        Set<Long> seats = codes.stream().map(Day05::computeSeatNumber).collect(Collectors.toUnmodifiableSet());
        long max = getMax(seats);
        int result = 0;
        for (int i = 0; i < max; i++) {
            if (!seats.contains((long) i)) {
                result = i;
            }
        }
        return result;
    }

    private static long computeSeatNumber(String code) {
        int row = computeRowNumber(code.substring(0, 7));
        int column = computeColumnNumber(code.substring(7));

        return (long) row * 8 + column;
    }

    private static long getMax(Set<Long> seats) {
        return seats.stream().mapToLong(i -> i).max().orElseThrow();
    }

    private static int computeRowNumber(String code) {
        int minRowNum = 0;
        int maxRowNum = 127;
        int row = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            switch (c) {
                case 'F': {
                    maxRowNum = minRowNum + (maxRowNum - minRowNum) / 2;
                    row = maxRowNum;
                    break;
                }
                case 'B': {
                    minRowNum = minRowNum + (maxRowNum - minRowNum) / 2 + 1;
                    row = minRowNum;
                    break;
                }
                default:
                    throw new IllegalStateException("Invalid character: " + c);
            }
        }
        
        return row;
    }

    private static int computeColumnNumber(String code) {
        int minColNum = 0;
        int maxColNum = 7;
        int col = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            switch (c) {
                case 'L': {
                    maxColNum = minColNum + (maxColNum - minColNum) / 2;
                    col = maxColNum;
                    break;
                }
                case 'R': {
                    minColNum = minColNum + (maxColNum - minColNum) / 2 + 1;
                    col = minColNum;
                    break;
                }
                default:
                    throw new IllegalStateException("Invalid character: " + c);
            }
        }

        return col;
    }
}
