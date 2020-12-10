package dev.esz.aoc.day10;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day10Test {

    @Test
    void part1Test1() {
        List<Integer> lines = Utils.readInts("src/main/resources/day10/test1.txt");
        Assertions.assertThat(Day10.part1(lines)).isEqualTo(35);
    }

    @Test
    void part1Test2() {
        List<Integer> lines = Utils.readInts("src/main/resources/day10/test2.txt");
        Assertions.assertThat(Day10.part1(lines)).isEqualTo(220);
    }

    @Test
    void part1() {
        List<Integer> lines = Utils.readInts("src/main/resources/day10/input.txt");
        Assertions.assertThat(Day10.part1(lines)).isEqualTo(1890);
    }

    @Test
    void part2Test1() {
        List<Integer> lines = Utils.readInts("src/main/resources/day10/test1.txt");
        Assertions.assertThat(Day10.part2(lines)).isEqualTo(8);
    }

    @Test
    void part2Test2() {
        List<Integer> lines = Utils.readInts("src/main/resources/day10/test2.txt");
        Assertions.assertThat(Day10.part2(lines)).isEqualTo(19208);
    }

    @Test
    void part2() {
        List<Integer> lines = Utils.readInts("src/main/resources/day10/input.txt");
        Assertions.assertThat(Day10.part2(lines)).isEqualTo(49607173328384L);
    }
}
