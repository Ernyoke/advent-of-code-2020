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
                .mapToLong(Day18::solveExpressionSameOperationPrecedence)
                .sum();
    }

    static long part2(List<String> lines) {
        return lines.stream()
                .map(Day18::splitLine)
                .mapToLong(Day18::solveExpressionAdditionPrecedence)
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

    private static long solveExpressionSameOperationPrecedence(List<String> expression) {
        long result = 0;
        Operation operation = Operation.ADD;
        int i = 0;
        while (i < expression.size()) {
            String part = expression.get(i);

            if (pattern.matcher(part).matches()) {
                long value = Long.parseLong(part);
                result = operation.apply(result, value);
                i++;
                continue;
            }

            if (part.equals("(")) {
                List<String> subEquation = extractSubExpression(expression, i);
                result = operation.apply(result, solveExpressionSameOperationPrecedence(subEquation));
                i += subEquation.size() + 2;
                continue;
            }

            operation = Operation.fromString(part);
            i++;
        }
        return result;
    }

    private static long solveExpressionAdditionPrecedence(List<String> expression) {
        long result = 0;
        int i = 0;
        while (i < expression.size()) {
            String part = expression.get(i);

            if (pattern.matcher(part).matches()) {
                result = Operation.ADD.apply(result, Long.parseLong(part));
                i++;
                continue;
            }

            if (part.equals("(")) {
                List<String> subExpression = extractSubExpression(expression, i);
                result = Operation.ADD.apply(result, solveExpressionAdditionPrecedence(subExpression));
                i += subExpression.size() + 2;
                continue;
            }

            if (Operation.fromString(part) == Operation.MULTIPLY) {
                result = Operation.MULTIPLY.apply(result, solveExpressionAdditionPrecedence(expression.subList(i + 1, expression.size())));
                break;
            }
            i++;
        }
        return result;
    }

    private static List<String> extractSubExpression(List<String> expression, int index) {
        int openBrackets = 1;
        int i = index + 1;
        for (; i < expression.size() && openBrackets > 0; i++) {
            String nextPart = expression.get(i);
            if (nextPart.equals("(")) {
                openBrackets++;
                continue;
            }
            if (nextPart.equals(")")) {
                openBrackets--;
            }
        }
        return expression.subList(index + 1, i - 1);
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
