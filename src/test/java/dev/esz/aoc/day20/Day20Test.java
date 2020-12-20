package dev.esz.aoc.day20;

import dev.esz.aoc.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Day20Test {

    @Test
    void part1Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day20/test1.txt");
        Assertions.assertThat(Day20.part1(lines)).isEqualTo(20899048083289L);
    }

    @Test
    void part1() {
        List<String> lines = Utils.readLines("src/main/resources/day20/input.txt");
        Assertions.assertThat(Day20.part1(lines)).isEqualTo(7901522557967L);
    }

    @Test
    void part2Test1() {
        List<String> lines = Utils.readLines("src/main/resources/day20/test1.txt");
        String[] monster = {"                  # ", "#    ##    ##    ###", " #  #  #  #  #  #   "};
        Assertions.assertThat(Day20.part2(lines, monster)).isEqualTo(273);
    }

    @Test
    void part2() {
        List<String> lines = Utils.readLines("src/main/resources/day20/input.txt");
        String[] monster = {"                  # ", "#    ##    ##    ###", " #  #  #  #  #  #   "};
        Assertions.assertThat(Day20.part2(lines, monster)).isEqualTo(2476);
    }

}
