package dev.esz.aoc.day6;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day6Test {

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day6/input.txt");
        Assertions.assertThat(Day6.part1(lines)).isEqualTo(6763);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day6/test.txt");
        Assertions.assertThat(Day6.part2(lines)).isEqualTo(6);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day6/input.txt");
        Assertions.assertThat(Day6.part2(lines)).isEqualTo(3512);
    }
}
