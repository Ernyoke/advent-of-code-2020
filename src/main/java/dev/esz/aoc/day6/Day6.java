package dev.esz.aoc.day6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Day6 {
    static int part1(List<String> lines) {
        Set<Character> answers = new HashSet<>();
        int count = 0;

        for (String line : lines) {
            if (line.isBlank() || line.isEmpty()) {
                count += answers.size();
                answers.clear();
            } else {
                answers.addAll(line.chars().mapToObj(c -> (char) c).collect(Collectors.toUnmodifiableSet()));
            }
        }

        return count + answers.size();
    }

    static int part2(List<String> lines) {
        Set<Character> answers = new HashSet<>();
        int count = 0;
        boolean newGroup = true;

        for (String line : lines) {
            if (line.isBlank() || line.isEmpty()) {
                count += answers.size();
                answers.clear();
                newGroup = true;
            } else {
                Set<Character> currentAnswers = line.chars().mapToObj(c -> (char) c).collect(Collectors.toUnmodifiableSet());
                if (newGroup) {
                    answers.addAll(currentAnswers);
                    newGroup = false;
                } else {
                    answers.retainAll(currentAnswers);
                }
            }
        }

        return count + answers.size();
    }
}
