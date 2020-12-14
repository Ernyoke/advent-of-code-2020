package dev.esz.aoc.day14;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day14Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day14/test1.txt");
        Assertions.assertThat(Day14.part1(lines)).isEqualTo(165);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day14/input.txt");
        Assertions.assertThat(Day14.part1(lines)).isEqualTo(14722016054794L);
    }

    @Test
    void part1Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day14/test2.txt");
        Assertions.assertThat(Day14.part2(lines)).isEqualTo(208);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day14/input.txt");
        Assertions.assertThat(Day14.part2(lines)).isEqualTo(3618217244644L);
    }
}
