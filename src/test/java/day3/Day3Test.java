package day3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.List;

public class Day3Test {

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day3/input.txt");
        Assertions.assertThat(Day3.part1(lines)).isEqualTo(164);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day3/input.txt");
        Assertions.assertThat(Day3.part2(lines)).isEqualTo(5007658656L);
    }
}
