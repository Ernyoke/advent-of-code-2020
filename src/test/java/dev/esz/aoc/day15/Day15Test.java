package dev.esz.aoc.day15;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day15Test {

    @Test
    void part1Test0() {
        List<Integer> numbers = List.of(0, 3, 6);
        Assertions.assertThat(Day15.part1(numbers)).isEqualTo(436);
    }

    @Test
    void part1Test1() {
        List<Integer> numbers = List.of(1, 3, 2);
        Assertions.assertThat(Day15.part1(numbers)).isEqualTo(1);
    }

    @Test
    void part1Test2() {
        List<Integer> numbers = List.of(2, 1, 3);
        Assertions.assertThat(Day15.part1(numbers)).isEqualTo(10);
    }

    @Test
    void part1Test3() {
        List<Integer> numbers = List.of(1, 2, 3);
        Assertions.assertThat(Day15.part1(numbers)).isEqualTo(27);
    }

    @Test
    void part1Test4() {
        List<Integer> numbers = List.of(2, 3, 1);
        Assertions.assertThat(Day15.part1(numbers)).isEqualTo(78);
    }

    @Test
    void part1Test5() {
        List<Integer> numbers = List.of(3, 2, 1);
        Assertions.assertThat(Day15.part1(numbers)).isEqualTo(438);
    }

    @Test
    void part1Test6() {
        List<Integer> numbers = List.of(3, 1, 2);
        Assertions.assertThat(Day15.part1(numbers)).isEqualTo(1836);
    }

    @Test
    void part1() {
        List<Integer> numbers = List.of(0, 13, 1, 8, 6, 15);
        Assertions.assertThat(Day15.part1(numbers)).isEqualTo(1618);
    }

    @Test
    void part2Test0() {
        List<Integer> numbers = List.of(0, 3, 6);
        Assertions.assertThat(Day15.part2(numbers, 30000000)).isEqualTo(175594);
    }

    @Test
    void part2() {
        List<Integer> numbers = List.of(0, 13, 1, 8, 6, 15);
        Assertions.assertThat(Day15.part2(numbers, 30000000)).isEqualTo(548531);
    }
}
