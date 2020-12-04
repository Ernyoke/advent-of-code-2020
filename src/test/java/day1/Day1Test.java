package day1;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

class Day1Test {
    private static Set<Integer> readNumbers() {
        Set<Integer> numbers = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/day1/input.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                numbers.add(Integer.valueOf(line));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return numbers;
    }

    @Test
    void testPart1() {
        System.out.println(Day1.part1(readNumbers()));
    }

    @Test
    void testPart2() {
        System.out.println(Day1.part2(readNumbers()));
    }
}
