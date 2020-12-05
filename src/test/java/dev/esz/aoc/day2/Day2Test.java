package dev.esz.aoc.day2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.esz.aoc.utils.Utils;

import java.util.List;

public class Day2Test {

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/.day2/input.txt");
        Assertions.assertThat(Day2.part1(lines)).isEqualTo(580);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day2/input.txt");
        Assertions.assertThat(Day2.part2(lines)).isEqualTo(611);
    }
}
