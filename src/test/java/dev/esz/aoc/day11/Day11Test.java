package dev.esz.aoc.day11;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day11Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day11/test1.txt");
        Assertions.assertThat(Day11.part1(lines)).isEqualTo(37);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day11/input.txt");
        Assertions.assertThat(Day11.part1(lines)).isEqualTo(2468);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day11/test1.txt");
        Assertions.assertThat(Day11.part2(lines)).isEqualTo(26);
    }

    @Test
    void part2Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day11/test2.txt");
        Assertions.assertThat(Day11.part2(lines)).isEqualTo(26);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day11/input.txt");
        Assertions.assertThat(Day11.part2(lines)).isEqualTo(2214);
    }
}
