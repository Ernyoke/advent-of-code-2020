package dev.esz.aoc.day24;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day24Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day24/test1.txt");
        Assertions.assertThat(Day24.part1(lines)).isEqualTo(10);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day24/input.txt");
        Assertions.assertThat(Day24.part1(lines)).isEqualTo(394);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day24/test1.txt");
        Assertions.assertThat(Day24.part2(lines, 100)).isEqualTo(2208);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day24/input.txt");
        Assertions.assertThat(Day24.part2(lines, 100)).isEqualTo(4036);
    }
}
