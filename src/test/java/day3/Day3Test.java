package day3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3Test {
    private static List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/day3/input.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return lines;
    }

    @Test
    void part1() {
        Assertions.assertThat(Day3.part1(readLines())).isEqualTo(164);
    }

    @Test
    void part2() {
        Assertions.assertThat(Day3.part2(readLines())).isEqualTo(5007658656L);
    }
}
