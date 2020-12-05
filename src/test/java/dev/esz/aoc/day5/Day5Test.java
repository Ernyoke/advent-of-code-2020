package dev.esz.aoc.day5;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day5Test {

    @Test
    void part1Test1() {
        List<String> codes = List.of("BFFFBBFRRR");
        Assertions.assertThat(Day5.part1(codes)).isEqualTo(567);
    }

    @Test
    void part1Test2() {
        List<String> codes = List.of("FFFBBBFRRR");
        Assertions.assertThat(Day5.part1(codes)).isEqualTo(119);
    }

    @Test
    void part1Test3() {
        List<String> codes = List.of("BBFFBBFRLL");
        Assertions.assertThat(Day5.part1(codes)).isEqualTo(820);
    }

    @Test
    void part1Test() {
        List<String> codes = Utils.readLines("src/main/resources/day5/input.txt");
        Assertions.assertThat(Day5.part1(codes)).isEqualTo(813);
    }

    @Test
    void part2Test() {
        List<String> codes = Utils.readLines("src/main/resources/day5/input.txt");
        Assertions.assertThat(Day5.part2(codes)).isEqualTo(612);
    }
}
