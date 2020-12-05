package dev.esz.aoc.utils;

public interface MathUtils {
    static boolean isBetweenInclusive(int value, int min, int max) {
        return min <= value && value <= max;
    }
}
