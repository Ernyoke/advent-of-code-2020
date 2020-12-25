package dev.esz.aoc.day25;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day25Test {

    @Test
    void part1Test1() {
        Assertions.assertThat(Day25.part1(5764801, 17807724)).isEqualTo(14897079);
    }

    @Test
    void part1() {
        Assertions.assertThat(Day25.part1(1717001, 523731)).isEqualTo(2679568);
    }
}
