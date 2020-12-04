package day1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.util.Set;

class Day1Test {
    @Test
    void testPart1() {
        Set<Integer> numbers = Utils.readUniqNumbers("src/main/resources/day1/input.txt");
        Assertions.assertThat(Day1.part1(numbers)).isEqualTo(802011);
    }

    @Test
    void testPart2() {
        Set<Integer> numbers = Utils.readUniqNumbers("src/main/resources/day1/input.txt");
        Assertions.assertThat(Day1.part2(numbers)).isEqualTo(248607374);
    }
}
