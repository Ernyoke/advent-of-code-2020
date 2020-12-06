package dev.esz.aoc.day6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Day6 {
    static long part1(List<List<String>> groups) {
        return groups.stream().mapToLong(Day6::countDistinct).sum();
    }

    private static long countDistinct(List<String> answers) {
        return answers.stream().flatMapToInt(String::chars).distinct().count();
    }

    static long part2(List<List<String>> groups) {
        return groups.stream().mapToLong(Day6::countDistinctExcludeDuplicates).sum();
    }

    private static long countDistinctExcludeDuplicates(List<String> answers) {
        Set<Character> distinctAnswers = new HashSet<>(answers.get(0).chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toUnmodifiableSet()));

        for (int i = 1; i < answers.size(); i++) {
            distinctAnswers.retainAll(answers.get(i).chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toUnmodifiableSet()));
        }

        return distinctAnswers.size();
    }
}
