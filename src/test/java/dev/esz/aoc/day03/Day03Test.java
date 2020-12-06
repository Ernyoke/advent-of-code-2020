package dev.esz.aoc.day03;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import dev.esz.aoc.utils.Utils;

import java.util.List;

public class Day03Test {

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day03/input.txt");
        Assertions.assertThat(Day03.part1(lines)).isEqualTo(164);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day03/input.txt");
        Assertions.assertThat(Day03.part2(lines)).isEqualTo(5007658656L);
    }
}
