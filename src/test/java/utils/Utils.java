package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
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
}
