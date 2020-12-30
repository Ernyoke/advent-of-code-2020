package dev.esz.aoc.day02;

import lombok.Builder;
import lombok.Value;

import java.util.List;

public interface Day02 {
    static long part1(List<String> lines) {
        return lines.stream().map(Day02::getPasswordWithPolicy).filter(Day02::isValidOldPolicy).count();
    }

    static long part2(List<String> lines) {
        return lines.stream().map(Day02::getPasswordWithPolicy).filter(Day02::isValidNewPolicy).count();
    }

    private static boolean isValidOldPolicy(PasswordWithPolicy passwordWithPolicy) {
        String password = passwordWithPolicy.getPassword();
        Policy policy = passwordWithPolicy.getPolicy();
        long charAppearance = password.chars().filter(character -> character == policy.getCharacter()).count();
        return charAppearance >= policy.getMin() && charAppearance <= policy.getMax();
    }

    private static PasswordWithPolicy getPasswordWithPolicy(String line) {
        String[] parts = line.split(":");
        return PasswordWithPolicy.builder()
                .policy(computePolicy(parts[0]))
                .password(parts[1].trim())
                .build();
    }

    private static Policy computePolicy(String policyString) {
        String[] parts = policyString.split(" ");
        String[] numbers = parts[0].split("-");
        return Policy.builder()
                .min(Integer.parseInt(numbers[0]))
                .max(Integer.parseInt(numbers[1]))
                .character(parts[1].charAt(0))
                .build();
    }

    private static boolean isValidNewPolicy(PasswordWithPolicy passwordWithPolicy) {
        Policy policy = passwordWithPolicy.getPolicy();
        String password = passwordWithPolicy.getPassword();
        int count = 0;
        count += password.charAt(policy.getMin() - 1) == policy.getCharacter() ? 1 : 0;
        count += password.charAt(policy.getMax() - 1) == policy.getCharacter() ? 1 : 0;
        return count == 1;
    }
}

@Builder
@Value
class Policy {
    int min;
    int max;
    char character;
}

@Builder
@Value
class PasswordWithPolicy {
    String password;
    Policy policy;
}
