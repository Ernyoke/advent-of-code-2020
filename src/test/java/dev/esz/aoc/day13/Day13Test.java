package dev.esz.aoc.day13;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Day13Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day13/test1.txt");
        int estimate = Integer.parseInt(lines.get(0));
        List<String> departures = Arrays.stream(lines.get(1).split(",")).collect(Collectors.toUnmodifiableList());
        Assertions.assertThat(Day13.part1(estimate, departures)).isEqualTo(295);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day13/input.txt");
        int estimate = Integer.parseInt(lines.get(0));
        List<String> departures = Arrays.stream(lines.get(1).split(",")).collect(Collectors.toUnmodifiableList());
        Assertions.assertThat(Day13.part1(estimate, departures)).isEqualTo(3789);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day13/test1.txt");
        List<String> departures = Arrays.stream(lines.get(1).split(",")).collect(Collectors.toUnmodifiableList());
        Assertions.assertThat(Day13.part2(departures)).isEqualTo(1068781);
    }

    @Test
    void part2Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day13/test2.txt");
        List<String> departures = Arrays.stream(lines.get(1).split(",")).collect(Collectors.toUnmodifiableList());
        Assertions.assertThat(Day13.part2(departures)).isEqualTo(3417);
    }

    @Test
    void part2Test3() {
        List<String> lines = Utils.readLines("src/main/resources/day13/test3.txt");
        List<String> departures = Arrays.stream(lines.get(1).split(",")).collect(Collectors.toUnmodifiableList());
        Assertions.assertThat(Day13.part2(departures)).isEqualTo(1202161486);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day13/input.txt");
        List<String> departures = Arrays.stream(lines.get(1).split(",")).collect(Collectors.toUnmodifiableList());
        Assertions.assertThat(Day13.part2(departures)).isEqualTo(667437230788118L);
    }
}
