package dev.esz.aoc.day22;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day22Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day22/test1.txt");
        Assertions.assertThat(Day22.part1(lines)).isEqualTo(306);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day22/input.txt");
        Assertions.assertThat(Day22.part1(lines)).isEqualTo(31269);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day22/test1.txt");
        Assertions.assertThat(Day22.part2(lines)).isEqualTo(291);
    }

    @Test
    void part2Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day22/test2.txt");
        Assertions.assertThat(Day22.part2(lines)).isEqualTo(105);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day22/input.txt");
        Assertions.assertThat(Day22.part2(lines)).isEqualTo(31151);
    }
}
