package dev.esz.aoc.day14;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day14 {
    static long part1(List<String> line) {
        return executionSum(line);
    }

    static long part2(List<String> line) {
        return executionSumV2(line);
    }

    private static long executionSum(List<String> lines) {
        Mask mask = new Mask(0, 0);
        Pattern pattern = Pattern.compile("[0-9]+");
        Map<Long, Long> memory = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(" = ");
            String maskStr = parts[1];
            if (parts[0].equals("mask")) {
                mask = Mask.build(maskStr);
            } else {
                Matcher matcher = pattern.matcher(parts[0]);
                if (matcher.find()) {
                    Long address = Long.valueOf(matcher.group(0));
                    long value = Long.parseLong(parts[1]);
                    memory.put(address, mask.apply(value));
                }
            }
        }
        return memory.values().stream().mapToLong(i -> i).sum();
    }

    private static long executionSumV2(List<String> lines) {
        MaskV2 mask = new MaskV2("");
        Pattern pattern = Pattern.compile("[0-9]+");
        Map<Long, Long> memory = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(" = ");
            if (parts[0].equals("mask")) {
                mask = new MaskV2(parts[1]);
            } else {
                Matcher matcher = pattern.matcher(parts[0]);
                if (matcher.find()) {
                    Long address = Long.parseLong(matcher.group(0));
                    Long value = Long.parseLong(parts[1]);
                    mask.apply(address).forEach(a -> memory.put(a, value));
                }
            }
        }
        return memory.values().stream().mapToLong(i -> i).sum();
    }
}

@RequiredArgsConstructor
@Getter
class Mask {
    private final long ones;
    private final long zeros;

    public long apply(long value) {
        return (value & zeros) | ones;
    }

    public static Mask build(String maskStr) {
        long ones = 0;
        long zeros = Long.MAX_VALUE;
        for (char c : maskStr.toCharArray()) {
            zeros = (zeros << 1) | 1;
            ones = ones << 1;
            if (c == '0') {
                zeros = zeros & (Long.MAX_VALUE << 1);
                continue;
            }
            if (c == '1') {
                ones = ones | 1;
            }
        }
        return new Mask(ones, zeros);
    }
}

@RequiredArgsConstructor
@Getter
class MaskV2 {
    private final String mask;

    public List<Long> apply(Long address) {
        String binaryAddress = convertTo36Bits(Long.toBinaryString(address));
        return computeCombinations(binaryAddress).stream()
                .map(a -> Long.parseLong(a, 2))
                .collect(Collectors.toUnmodifiableList());
    }

    /***
     * Implementation for:
     *
     * If the bitmask bit is 0, the corresponding memory address bit is unchanged.
     * If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1.
     * If the bitmask bit is X, the corresponding memory address bit is floating (overwrite with X for now).
     *
     * Returns a 36 chars address with the mask applied, but without the floating combinations computed.
     * @param binaryAddress
     * @return
     */
    private String convertTo36Bits(String binaryAddress) {
        StringBuilder binaryBuilder = new StringBuilder("000000000000000000000000000000000000")
                .replace(36 - binaryAddress.length(), 36, binaryAddress);
        for (int i = 0; i < 36; i++) {
            if (mask.charAt(i) == 'X' || mask.charAt(i) == '1') {
                binaryBuilder.replace(i, i + 1, String.valueOf(mask.charAt(i)));
            }
        }
        return binaryBuilder.toString();
    }

    private static String getBinaryWithPrefix(long i, long n) {
        StringBuilder helper = new StringBuilder();
        for (int k = 0; k < n; k++) {
            helper.append("0");
        }
        String binary = Long.toBinaryString(i);
        return helper.replace(helper.length() - binary.length(), helper.length(), binary).toString();
    }

    private List<String> computeCombinations(String binaryAddress) {
        List<String> result = new ArrayList<>();
        long countX = mask.chars().filter(c -> c == 'X').count();
        for (int i = 0; i < (1 << countX); i++) {
            StringBuilder addressBuilder = new StringBuilder(binaryAddress);
            String binaryCounter = getBinaryWithPrefix(i, countX);
            int k = 0;
            for (int j = 0; j < binaryAddress.length(); j++) {
                if (binaryAddress.charAt(j) == 'X') {
                    addressBuilder.replace(j, j + 1, binaryCounter.substring(k, k + 1));
                    k++;
                }
            }
            result.add(addressBuilder.toString());
        }
        return result;
    }

}
