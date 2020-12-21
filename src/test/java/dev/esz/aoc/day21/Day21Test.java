package dev.esz.aoc.day21;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day21Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day21/test1.txt");
        Assertions.assertThat(Day21.part1(lines)).isEqualTo(5);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day21/input.txt");
        Assertions.assertThat(Day21.part1(lines)).isEqualTo(2627);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day21/test1.txt");
        Assertions.assertThat(Day21.part2(lines)).isEqualTo("mxmxvkd,sqjhc,fvjkl");
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day21/input.txt");
        Assertions.assertThat(Day21.part2(lines)).isEqualTo("hn,dgsdtj,kpksf,sjcvsr,bstzgn,kmmqmv,vkdxfj,bsfqgb");
    }
}
