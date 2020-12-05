package dev.esz.aoc.day3;

import java.util.List;

public interface Day3 {
    static int countTrees(List<String> lines, int right, int down) {
        int j = 0;
        int counter = 0;
        for (int i = down; i < lines.size(); i += down) {
            String line = lines.get(i);
            j += right;
            if (j >= line.length()) {
                j -= line.length();
            }
            if (lines.get(i).charAt(j) == '#') {
                counter++;
            }
        }
        return counter;
    }

    static int part1(List<String> lines) {
        return countTrees(lines, 3, 1);
    }

    static long part2(List<String> lines) {
        int a = countTrees(lines, 1, 1);
        int b = countTrees(lines, 3, 1);
        int c = countTrees(lines, 5, 1);
        int d = countTrees(lines, 7, 1);
        int e = countTrees(lines, 1, 2);
        return (long) a * b * c * d * e;
    }
}
