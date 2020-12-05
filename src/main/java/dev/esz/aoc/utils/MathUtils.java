package dev.esz.aoc.utils;

public class MathUtils {
    public static boolean isBetweenInclusive(int value, int min, int max) {
        return min <= value && value <= max;
    }
}
