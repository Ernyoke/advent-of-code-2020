package dev.esz.aoc.day01;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.esz.aoc.utils.Utils;

import java.util.Set;

class Day01Test {
    @Test
    void testPart1() {
        Set<Integer> numbers = Utils.readUniqNumbers("src/main/resources/day01/input.txt");
        Assertions.assertThat(Day01.part1(numbers)).isEqualTo(802011);
    }

    @Test
    void testPart2() {
        Set<Integer> numbers = Utils.readUniqNumbers("src/main/resources/day01/input.txt");
        Assertions.assertThat(Day01.part2(numbers)).isEqualTo(248607374);
    }
}
