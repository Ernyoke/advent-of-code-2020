package dev.esz.aoc.day16;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day16Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day16/test1.txt");
        Assertions.assertThat(Day16.part1(lines)).isEqualTo(71);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day16/input.txt");
        Assertions.assertThat(Day16.part1(lines)).isEqualTo(25788L);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day16/input.txt");
        Assertions.assertThat(Day16.part2(lines)).isEqualTo(3902565915559L);
    }
}
