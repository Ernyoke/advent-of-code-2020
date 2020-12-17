package dev.esz.aoc.day17;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day17Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day17/test1.txt");
        Assertions.assertThat(Day17.part1(lines, 6)).isEqualTo(112);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day17/input.txt");
        Assertions.assertThat(Day17.part1(lines, 6)).isEqualTo(448);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day17/test1.txt");
        Assertions.assertThat(Day17.part2(lines, 6)).isEqualTo(848);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day17/input.txt");
        Assertions.assertThat(Day17.part2(lines, 6)).isEqualTo(2400);
    }
}
