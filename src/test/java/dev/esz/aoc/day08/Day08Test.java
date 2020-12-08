package dev.esz.aoc.day08;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day08Test {
    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day08/test.txt");
        Assertions.assertThat(Day08.part1(lines)).isEqualTo(5);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day08/input.txt");
        Assertions.assertThat(Day08.part1(lines)).isEqualTo(1928);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day08/test.txt");
        Assertions.assertThat(Day08.part2(lines)).isEqualTo(8);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day08/input.txt");
        Assertions.assertThat(Day08.part2(lines)).isEqualTo(1319);
    }
}
