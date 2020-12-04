package day2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2Test {
    private static List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/day2/input.txt"))) {
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
        Assertions.assertThat(Day2.part1(readLines())).isEqualTo(580);
    }

    @Test
    void part2() {
        Assertions.assertThat(Day2.part2(readLines())).isEqualTo(611);
    }
}
