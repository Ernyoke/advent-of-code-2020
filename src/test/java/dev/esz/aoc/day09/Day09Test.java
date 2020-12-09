package dev.esz.aoc.day09;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day09Test {
    @Test
    void part1Test1() {
        List<Long> lines = Utils.readNumbers("src/main/resources/day09/test.txt");
        Assertions.assertThat(Day09.part1(lines, 5)).isEqualTo(127);
    }

    @Test
    void part1() {
        List<Long> lines = Utils.readNumbers("src/main/resources/day09/input.txt");
        Assertions.assertThat(Day09.part1(lines, 25)).isEqualTo(26134589L);
    }

    @Test
    void part2Test1() {
        List<Long> lines = Utils.readNumbers("src/main/resources/day09/test.txt");
        Assertions.assertThat(Day09.part2(lines, 5)).isEqualTo(62);
    }

    @Test
    void part2() {
        List<Long> lines = Utils.readNumbers("src/main/resources/day09/input.txt");
        Assertions.assertThat(Day09.part2(lines, 25)).isEqualTo(3535124L);
    }
}
