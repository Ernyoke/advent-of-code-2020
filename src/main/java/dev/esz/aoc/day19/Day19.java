package dev.esz.aoc.day19;

import java.util.*;

public class Day19 {
    static Map<Integer, List<List<Integer>>> ruleToOtherRules;
    static Map<Integer, String> endRules;

    static List<Integer> stack;
    static Set<List<Integer>> uniqStacks;

    static int part1And2(List<String> lines) {
        List<String> ruleLines = new ArrayList<>();
        int i = 0;
        String line = lines.get(i);
        while (!line.trim().isBlank()) {
            ruleLines.add(line);
            line = lines.get(++i);
        }

        parseRules(ruleLines);

        List<String> messages = new ArrayList<>();
        i++;
        for (; i < lines.size(); i++) {
            line = lines.get(i);
            messages.add(line);
        }

        int count = 0;
        for (String message : messages) {
            String[] a = message.split("");
            List<String> messageChars = new ArrayList<>(Arrays.asList(a));
            uniqStacks = new HashSet<>();
            stack = new ArrayList<>();
            boolean solution = checkIfRulesMatchesMessage(0, messageChars);
            if (solution && messageChars.size() == 0) {
                count++;
            }
            // Basically we are saving stack of rules. In case of a loop I've noticed that the output is valid
            // if the there was only one uniq stack saved and the input message was rebuilt.
            // Pretty hacky but it works, this day was painful.
            if (uniqStacks.size() == 1 && message.equals(String.join("", messageChars))) {
                count++;
            }
        }
        return count;
    }

    private static void parseRules(List<String> lines) {
        ruleToOtherRules = new HashMap<>();
        endRules = new HashMap<>();

        for (String line : lines) {

            String[] lineParts = line.split(": ");
            Integer id = Integer.valueOf(lineParts[0]);
            List<List<Integer>> rule = ruleToOtherRules.getOrDefault(id, new ArrayList<>());
            ruleToOtherRules.put(id, rule);
            String ruleSetString = lineParts[1];

            if (ruleSetString.startsWith("\"")) {
                endRules.put(id, ruleSetString.substring(1, ruleSetString.length() - 1));
            } else {
                String[] rulesString = ruleSetString.split(" ");
                List<Integer> subRules = new ArrayList<>();
                for (String ruleString : rulesString) {
                    if (ruleString.equals("|")) {
                        rule.add(subRules);
                        subRules = new ArrayList<>();
                    } else {
                        Integer subRule = Integer.valueOf(ruleString);
                        List<List<Integer>> otherSubRUle = ruleToOtherRules.getOrDefault(subRule, new ArrayList<>());
                        ruleToOtherRules.put(subRule, otherSubRUle);
                        subRules.add(subRule);
                    }
                }
                rule.add(subRules);
            }
        }
    }

    private static boolean checkIfRulesMatchesMessage(Integer rule, List<String> message) {
        if (endRules.containsKey(rule)) {
            if (matches(message, endRules.get(rule))) {
                message.remove(0);
                return true;
            }
        } else {
            List<List<Integer>> subRuleLists = ruleToOtherRules.get(rule);
            for (List<Integer> subRuleList : subRuleLists) {
                boolean allMatches = true;
                List<String> copyMessage = new ArrayList<>(message);
                for (Integer subRule : subRuleList) {
                    stack.add(rule);
                    if (!checkIfRulesMatchesMessage(subRule, copyMessage)) {
                        allMatches = false;
                        stack.remove(rule);
                        break;
                    }
                }
                if (allMatches) {
                    while (message.size() > copyMessage.size()) {
                        message.remove(0);
                    }
                    if (message.size() == 0) {
                        uniqStacks.add(new ArrayList<>(stack));
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean matches(List<String> message, String ruleMatchString) {
        return String.join("", message).startsWith(ruleMatchString);
    }
}
