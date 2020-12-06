package dev.esz.aoc.day06;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day06Test {

    @Test
    void part1() {
        List<List<String>> groups = Utils.readLinesGroupedByEmptyLine("src/main/resources/day06/input.txt");
        Assertions.assertThat(Day06.part1(groups)).isEqualTo(6763);
    }

    @Test
    void part2Test1() {
        List<List<String>> groups = Utils.readLinesGroupedByEmptyLine("src/main/resources/day06/test.txt");
        Assertions.assertThat(Day06.part2(groups)).isEqualTo(6);
    }

    @Test
    void part2() {
        List<List<String>> groups = Utils.readLinesGroupedByEmptyLine("src/main/resources/day06/input.txt");
        Assertions.assertThat(Day06.part2(groups)).isEqualTo(3512);
    }
}
