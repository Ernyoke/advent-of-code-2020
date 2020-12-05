package dev.esz.aoc.day4;

import java.util.*;

import static dev.esz.aoc.utils.MathUtils.isBetweenInclusive;

public interface Day4 {
    static int part1(List<String> lines) {
        final Set<String> requiredFields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        Set<String> passportFields = new HashSet<>();

        int count = 0;
        for (String rawLine : lines) {
            String line = rawLine.strip();
            if (line.isBlank() || line.isEmpty()) {
                if (passportFields.containsAll(requiredFields)) {
                    count++;
                }
                passportFields.clear();
            } else {
                String[] parts = line.split(" ");
                for (String part : parts) {
                    passportFields.add(part.split(":")[0]);
                }
            }
        }
        if (passportFields.containsAll(requiredFields)) {
            count++;
        }

        return count;
    }

    static int part2(List<String> lines) {
        Map<String, String> passport = new HashMap<>();

        int count = 0;
        for (String rawLine : lines) {
            String line = rawLine.strip();
            if (line.isBlank() || line.isEmpty()) {
                if (isPassportValid(passport)) {
                    count++;
                }
                passport.clear();
            } else {
                String[] parts = line.split(" ");
                for (String part : parts) {
                    passport.put(part.split(":")[0], part.split(":")[1]);
                }
            }
        }
        if (isPassportValid(passport)) {
            count++;
        }

        return count;
    }

    private static boolean isPassportValid(Map<String, String> passport) {
        try {
            return isBirthYearValid(passport.get("byr")) &&
                    isIssueYearValid(passport.get("iyr")) &&
                    isExpirationYearValid(passport.get("eyr")) &&
                    isHeightValid(passport.get("hgt")) &&
                    isHairColorValid(passport.get("hcl")) &&
                    isEyeColorValid(passport.get("ecl")) &&
                    isPassportIdValid(passport.get("pid"));
        } catch (Exception exception) {
            return false;
        }
    }

    private static boolean isBirthYearValid(String value) {
        Objects.requireNonNull(value);
        return isBetweenInclusive(Integer.parseInt(value), 1920, 2002);
    }

    private static boolean isIssueYearValid(String value) {
        Objects.requireNonNull(value);
        return isBetweenInclusive(Integer.parseInt(value), 2010, 2020);
    }

    private static boolean isExpirationYearValid(String value) {
        Objects.requireNonNull(value);
        return isBetweenInclusive(Integer.parseInt(value), 2020, 2030);
    }

    private static boolean isHeightValid(String value) {
        Objects.requireNonNull(value);
        int height = Integer.parseInt(value.replaceAll("\\D+", ""));
        String unit = value.replaceAll("[0-9]+", "");
        switch (unit) {
            case "cm":
                return isBetweenInclusive(height, 150, 193);
            case "in":
                return isBetweenInclusive(height, 59, 76);
            default:
                return false;
        }
    }

    private static boolean isHairColorValid(String value) {
        Objects.requireNonNull(value);
        return value.matches("#([0-9]|[a-f]){6}");
    }

    private static boolean isEyeColorValid(String value) {
        Objects.requireNonNull(value);
        final Set<String> values = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        return values.contains(value);
    }

    private static boolean isPassportIdValid(String value) {
        Objects.requireNonNull(value);
        return value.matches("(\\d){9}");
    }
}
