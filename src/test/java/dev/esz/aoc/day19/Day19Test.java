package dev.esz.aoc.day19;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day19Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day19/test1.txt");
        Assertions.assertThat(Day19.part1And2(lines)).isEqualTo(2);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day19/input.txt");
        Assertions.assertThat(Day19.part1And2(lines)).isEqualTo(190);
    }

    @Test
    void part1Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day19/test2a.txt");
        Assertions.assertThat(Day19.part1And2(lines)).isEqualTo(3);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day19/test2.txt");
        Assertions.assertThat(Day19.part1And2(lines)).isEqualTo(12);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day19/input2.txt");
        Assertions.assertThat(Day19.part1And2(lines)).isEqualTo(311);
    }
}
