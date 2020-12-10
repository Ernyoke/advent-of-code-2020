package dev.esz.aoc.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
    public static List<Integer> readInts(String path) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                numbers.add(Integer.valueOf(line));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return numbers;
    }

    public static List<Long> readLongs(String path) {
        List<Long> numbers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                numbers.add(Long.valueOf(line));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return numbers;
    }


    public static Set<Integer> readUniqNumbers(String path) {
        Set<Integer> numbers = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                numbers.add(Integer.valueOf(line));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return numbers;
    }


    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return lines;
    }

    public static List<List<String>> readLinesGroupedByEmptyLine(String path) {
        List<List<String>> groups = new ArrayList<>();
        List<String> group = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isBlank() || line.isBlank()) {
                    groups.add(group);
                    group = new ArrayList<>();
                } else {
                    group.add(line);
                }
            }
            if (!group.isEmpty()) {
                groups.add(group);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return groups;
    }
}
