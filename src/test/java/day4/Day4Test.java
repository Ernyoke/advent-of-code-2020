package day4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.List;

class Day4Test {
    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day4/input.txt");
        Assertions.assertThat(Day4.part1(lines)).isEqualTo(230);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day4/input.txt");
        Assertions.assertThat(Day4.part2(lines)).isEqualTo(156);
    }
}
