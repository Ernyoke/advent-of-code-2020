package dev.esz.aoc.day14;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MaskV2Test {

    @Test
    void applyTest1() {
        MaskV2 maskV2 = new MaskV2("000000000000000000000000000000X1001X");
        List<Long> result = maskV2.apply(42L);
        Assertions.assertThat(result).containsExactlyInAnyOrder(26L, 27L, 58L, 59L);
    }

    @Test
    void applyTest2() {
        MaskV2 maskV2 = new MaskV2("00000000000000000000000000000001X0XX");
        List<Long> result = maskV2.apply(26L);
        Assertions.assertThat(result).containsExactlyInAnyOrder(16L, 17L, 18L, 19L, 24L, 25L, 26L, 27L);
    }
}
