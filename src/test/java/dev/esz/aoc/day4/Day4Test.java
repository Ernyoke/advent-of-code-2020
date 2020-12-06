package dev.esz.aoc.day4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.esz.aoc.utils.Utils;

import java.util.List;

class Day4Test {
    @Test
    void part1() {
        List<List<String>> groups = Utils.readLinesGroupedByEmptyLine("src/main/resources/day4/input.txt");
        Assertions.assertThat(Day4.part1(groups)).isEqualTo(230);
    }

    @Test
    void part2() {
        List<List<String>> lines = Utils.readLinesGroupedByEmptyLine("src/main/resources/day4/input.txt");
        Assertions.assertThat(Day4.part2(lines)).isEqualTo(156);
    }
}
