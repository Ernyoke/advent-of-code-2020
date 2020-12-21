package dev.esz.aoc.day21;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

public interface Day21 {
    static long part1(List<String> lines) {
        List<Food> foods = lines.stream().map(Day21::parseFood).collect(Collectors.toList());
        return countHealthyIngredients(foods);
    }

    static String part2(List<String> lines) {
        List<Food> foods = lines.stream().map(Day21::parseFood).collect(Collectors.toList());
        return canonicalDangerousIngredient(foods);
    }

    private static Food parseFood(String line) {
        String[] parts = line.split(" \\(");
        String foodsPart = parts[0];
        String allergensPart = parts[1]
                .replace(")", "")
                .replaceFirst("contains ", "");
        Set<String> foodSet = new HashSet<>(Arrays.asList(foodsPart.split(" ")));
        Set<String> allergensSet = new HashSet<>(Arrays.asList(allergensPart.split(", ")));
        return new Food(foodSet, allergensSet);
    }

    private static long countHealthyIngredients(List<Food> foods) {
        Map<String, Integer> countIngredients = new HashMap<>();

        for (Food food : foods) {
            for (String ingredient : food.getIngredients()) {
                countIngredients.merge(ingredient, 1, Integer::sum);
            }
        }

        Map<String, Set<String>> allergicIngredients = getAllergicIngredients(foods);

        Set<String> allergens = allergicIngredients.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        return countIngredients.entrySet().stream()
                .filter(entry -> !allergens.contains(entry.getKey()))
                .mapToLong(Map.Entry::getValue)
                .sum();
    }

    private static Map<String, Set<String>> getAllergicIngredients(List<Food> foods) {
        Map<String, Set<String>> allergicIngredients = new HashMap<>();
        for (Food food : foods) {
            for (String allergen : food.getAllergens()) {
                allergicIngredients.merge(allergen, food.getIngredients(), (existingIngredients, newIngredients) -> {
                    Set<String> copy = new HashSet<>(existingIngredients);
                    copy.retainAll(newIngredients);
                    return copy;
                });
            }
        }

        Set<String> removedIngredients = new HashSet<>();
        boolean isRemove = true;
        while (isRemove) {
            isRemove = false;
            for (var entry : allergicIngredients.entrySet()) {
                Set<String> ingredients = entry.getValue();
                if (ingredients.size() == 1) {
                    String ingredient = new ArrayList<>(ingredients).get(0);
                    if (!removedIngredients.contains(ingredient)) {
                        removedIngredients.add(ingredient);
                        removeIngredient(allergicIngredients, ingredient);
                        isRemove = true;
                    }
                }
            }
        }
        return allergicIngredients;
    }

    private static String canonicalDangerousIngredient(List<Food> foods) {
        Map<String, Set<String>> allergicIngredients = getAllergicIngredients(foods);

        List<String> allergens = allergicIngredients.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return String.join(",", allergens);
    }

    private static void removeIngredient(Map<String, Set<String>> allergicIngredients, String ingredient) {
        for (var entry : allergicIngredients.entrySet()) {
            if (entry.getValue().size() > 1) {
                entry.getValue().remove(ingredient);
            }
        }
    }

}

@RequiredArgsConstructor
@Getter
class Food {
    private final Set<String> ingredients;
    private final Set<String> allergens;
}
