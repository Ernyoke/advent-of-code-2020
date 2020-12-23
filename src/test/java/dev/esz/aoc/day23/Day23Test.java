package dev.esz.aoc.day23;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Day23Test {

    @Test
    void part1Test1() {
        List<Integer> cups = Arrays.stream("389125467".split("")).map(Integer::valueOf).collect(Collectors.toList());
        Assertions.assertThat(Day23.part1WithRotation(cups, 10)).isEqualTo("92658374");
    }

    @Test
    void part1Test2() {
        List<Integer> cups = Arrays.stream("389125467".split("")).map(Integer::valueOf).collect(Collectors.toList());
        Assertions.assertThat(Day23.part1WithRotation(cups, 100)).isEqualTo("67384529");
    }

    @Test
    void part1() {
        List<Integer> cups = Arrays.stream("362981754".split("")).map(Integer::valueOf).collect(Collectors.toList());
        Assertions.assertThat(Day23.part1WithRotation(cups, 100)).isEqualTo("24798635");
    }

    @Test
    void part1CircularLinkedListTest1() {
        List<Integer> cups = Arrays.stream("389125467".split("")).map(Integer::valueOf).collect(Collectors.toList());
        Assertions.assertThat(Day23.part1(cups, 10)).isEqualTo("92658374");
    }

    @Test
    void part1CircularLinkedListTest2() {
        List<Integer> cups = Arrays.stream("389125467".split("")).map(Integer::valueOf).collect(Collectors.toList());
        Assertions.assertThat(Day23.part1(cups, 100)).isEqualTo("67384529");
    }

    @Test
    void part1CircularLinkedList() {
        List<Integer> cups = Arrays.stream("362981754".split("")).map(Integer::valueOf).collect(Collectors.toList());
        Assertions.assertThat(Day23.part1(cups, 100)).isEqualTo("24798635");
    }


    @Test
    void part2Test1() {
        List<Integer> cups = Arrays.stream("389125467".split("")).map(Integer::valueOf).collect(Collectors.toList());
        Assertions.assertThat(Day23.part2(cups, 10000000)).isEqualTo(149245887792L);
    }

    @Test
    void part2() {
        List<Integer> cups = Arrays.stream("362981754".split("")).map(Integer::valueOf).collect(Collectors.toList());
        Assertions.assertThat(Day23.part2(cups, 10000000)).isEqualTo(12757828710L);
    }
}
