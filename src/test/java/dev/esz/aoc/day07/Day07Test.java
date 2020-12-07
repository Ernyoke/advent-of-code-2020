package dev.esz.aoc.day07;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day07Test {
    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day07/test.txt");
        Assertions.assertThat(Day07.part1(lines)).isEqualTo(4);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day07/input.txt");
        Assertions.assertThat(Day07.part1(lines)).isEqualTo(372);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day07/test.txt");
        Assertions.assertThat(Day07.part2(lines)).isEqualTo(32);
    }

    @Test
    void part2Test2() {
        List<String> lines = Utils.readLines("src/main/resources/day07/test2.txt");
        Assertions.assertThat(Day07.part2(lines)).isEqualTo(126);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day07/input.txt");
        Assertions.assertThat(Day07.part2(lines)).isEqualTo(8015);
    }
}
