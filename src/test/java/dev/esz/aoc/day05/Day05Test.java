package dev.esz.aoc.day05;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day05Test {

    @Test
    void part1Test1() {
        List<String> codes = List.of("BFFFBBFRRR");
        Assertions.assertThat(Day05.part1(codes)).isEqualTo(567);
    }

    @Test
    void part1Test2() {
        List<String> codes = List.of("FFFBBBFRRR");
        Assertions.assertThat(Day05.part1(codes)).isEqualTo(119);
    }

    @Test
    void part1Test3() {
        List<String> codes = List.of("BBFFBBFRLL");
        Assertions.assertThat(Day05.part1(codes)).isEqualTo(820);
    }

    @Test
    void part1Test() {
        List<String> codes = Utils.readLines("src/main/resources/day05/input.txt");
        Assertions.assertThat(Day05.part1(codes)).isEqualTo(813);
    }

    @Test
    void part2Test() {
        List<String> codes = Utils.readLines("src/main/resources/day05/input.txt");
        Assertions.assertThat(Day05.part2(codes)).isEqualTo(612);
    }
}
