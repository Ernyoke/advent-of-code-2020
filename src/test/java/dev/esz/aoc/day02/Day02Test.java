package dev.esz.aoc.day02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.esz.aoc.utils.Utils;

import java.util.List;

public class Day02Test {

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day02/input.txt");
        Assertions.assertThat(Day02.part1(lines)).isEqualTo(580);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day02/input.txt");
        Assertions.assertThat(Day02.part2(lines)).isEqualTo(611);
    }
}
