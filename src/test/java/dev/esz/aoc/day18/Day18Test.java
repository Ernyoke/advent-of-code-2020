package dev.esz.aoc.day18;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {

    @Test
    void part1Test1() {
        List<String> lines = List.of("2 * 3 + (4 * 5)");
        Assertions.assertThat(Day18.part1(lines)).isEqualTo(26);
    }

    @Test
    void part1Test2() {
        List<String> lines = List.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        Assertions.assertThat(Day18.part1(lines)).isEqualTo(12240);
    }

    @Test
    void part1Test3() {
        List<String> lines = List.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        Assertions.assertThat(Day18.part1(lines)).isEqualTo(13632);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day18/input.txt");
        Assertions.assertThat(Day18.part1(lines)).isEqualTo(4696493914530L);
    }

    @Test
    void part2Test1() {
        List<String> lines = List.of("2 * 3 + (4 * 5)");
        Assertions.assertThat(Day18.part2(lines)).isEqualTo(46);
    }

    @Test
    void part2Test2() {
        List<String> lines = List.of("1 + (2 * 3) + (4 * (5 + 6))");
        Assertions.assertThat(Day18.part2(lines)).isEqualTo(51);
    }

    @Test
    void part2Test3() {
        List<String> lines = List.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        Assertions.assertThat(Day18.part2(lines)).isEqualTo(669060);
    }

    @Test
    void part2Test4() {
        List<String> lines = List.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        Assertions.assertThat(Day18.part2(lines)).isEqualTo(23340);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day18/input.txt");
        Assertions.assertThat(Day18.part2(lines)).isEqualTo(362880372308125L);
    }

}
