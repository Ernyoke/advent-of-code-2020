package dev.esz.aoc.day18;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Day18 {
    Pattern pattern = Pattern.compile("[0-9]+");

    static long part1(List<String> lines) {
        return lines.stream()
                .map(Day18::splitLine)
                .mapToLong(Day18::solveLineSameOperationPrecedence)
                .sum();
    }

    static long part2(List<String> lines) {
        return lines.stream()
                .map(Day18::splitLine)
                .mapToLong(Day18::solveLineAdditionPrecedence)
                .sum();
    }

    private static List<String> splitLine(String line) {
        List<String> parts = new ArrayList<>();
        for (String part : line.split(" ")) {
            if (part.charAt(0) == '(' || part.charAt(part.length() - 1) == ')') {
                Matcher matcher = pattern.matcher(part);
                String number = "";
                if (matcher.find()) {
                    number = matcher.group(0);
                }
                String brackets = part.replaceFirst(number, "");
                if (part.charAt(0) == '(') {
                    for (char c : brackets.toCharArray()) {
                        parts.add(String.valueOf(c));
                    }
                    parts.add(number);
                } else {
                    parts.add(number);
                    for (char c : brackets.toCharArray()) {
                        parts.add(String.valueOf(c));
                    }
                }
                continue;
            }
            parts.add(part);
        }
        return parts;
    }

    private static long solveLineSameOperationPrecedence(List<String> equation) {
        long result = 0;
        Operation operation = Operation.ADD;
        int i = 0;
        while (i < equation.size()) {
            String part = equation.get(i);

            if (pattern.matcher(part).matches()) {
                long value = Long.parseLong(part);
                result = operation.apply(result, value);
                i++;
                continue;
            }

            if (part.equals("(")) {
                List<String> subEquation = extractSubEquation(equation, i);
                result = operation.apply(result, solveLineSameOperationPrecedence(subEquation));
                i += subEquation.size() + 2;
                continue;
            }

            operation = Operation.fromString(part);
            i++;
        }
        return result;
    }

    private static long solveLineAdditionPrecedence(List<String> equation) {
        long result = 0;
        Operation operation = Operation.ADD;
        int i = 0;
        while (i < equation.size()) {
            String part = equation.get(i);

            if (pattern.matcher(part).matches()) {
                if (operation == Operation.MULTIPLY) {
                    result = operation.apply(result, solveLineAdditionPrecedence(equation.subList(i, equation.size())));
                    break;
                }
                result = operation.apply(result, Long.parseLong(part));
                i++;
                continue;
            }

            if (part.equals("(")) {
                if (operation == Operation.MULTIPLY) {
                    result = operation.apply(result, solveLineAdditionPrecedence(equation.subList(i, equation.size())));
                    break;
                }
                List<String> subEquation = extractSubEquation(equation, i);
                result = operation.apply(result, solveLineAdditionPrecedence(subEquation));
                i += subEquation.size() + 2;
                continue;
            }

            operation = Operation.fromString(part);
            i++;
        }
        return result;
    }

    private static List<String> extractSubEquation(List<String> line, int i) {
        int openBrackets = 1;
        int j = i + 1;
        for (; j < line.size() && openBrackets > 0; j++) {
            String nextPart = line.get(j);
            if (nextPart.equals("(")) {
                openBrackets++;
                continue;
            }
            if (nextPart.equals(")")) {
                openBrackets--;
            }
        }
        return line.subList(i + 1, j - 1);
    }
}

enum Operation {
    ADD {
        public long apply(long a, long b) {
            return a + b;
        }
    },
    MULTIPLY {
        public long apply(long a, long b) {
            return a * b;
        }
    };

    public static Operation fromString(String operation) {
        if (operation.equals("+")) {
            return Operation.ADD;
        } else if (operation.equals("*")) {
            return Operation.MULTIPLY;
        }
        throw new IllegalArgumentException();
    }

    public abstract long apply(long a, long b);
}
