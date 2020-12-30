package dev.esz.aoc.day07;

import lombok.Value;

import java.util.*;
import java.util.stream.Collectors;

public interface Day07 {
    static int part1(List<String> lines) {
        List<Bag> bags = lines.stream().map(Day07::getBag).collect(Collectors.toUnmodifiableList());
        return countNumberOfBagsContainingColor("shiny gold", bags);
    }

    static long part2(List<String> lines) {
        List<Bag> bags = lines.stream().map(Day07::getBag).collect(Collectors.toUnmodifiableList());
        return computeMaxContentForBagWithColor("shiny gold", bags);
    }

    private static Bag getBag(String line) {
        String[] parts = line.split(" bags contain ");
        String color = parts[0];
        Map<String, Integer> content = getContent(parts[1]);
        return new Bag(color, content);
    }

    private static Map<String, Integer> getContent(String contentString) {
        String[] parts = contentString.split(",");
        Map<String, Integer> content = new HashMap<>();
        for (String part : parts) {
            String[] subParts = part.trim().split(" ");
            if (!"no".equals(subParts[0])) {
                content.put(subParts[1] + " " + subParts[2], Integer.parseInt(subParts[0]));
            }
        }
        return content;
    }

    private static int countNumberOfBagsContainingColor(String color, List<Bag> bags) {
        Set<String> touched = new HashSet<>();
        touched.add(color);

        Queue<Bag> bagQueue = new ArrayDeque<>(bags.stream()
                .filter(bag -> bag.containsBagWithColor(color))
                .collect(Collectors.toUnmodifiableList()));

        for (Bag bag : bagQueue) {
            touched.add(bag.getColor());
        }

        int nrBags = bagQueue.size();

        while (!bagQueue.isEmpty()) {
            Bag currentBag = bagQueue.poll();
            for (Bag bag : bags) {
                if (!touched.contains(bag.getColor()) && bag.containsBagWithColor(currentBag.getColor())) {
                    bagQueue.offer(bag);
                    touched.add(bag.getColor());
                    nrBags++;
                }
            }
        }

        return nrBags;
    }

    private static long computeMaxContentForBagWithColor(String color, List<Bag> bags) {
        Map<String, Bag> colorToBag = bags.stream().collect(Collectors.toUnmodifiableMap(Bag::getColor, bag -> bag));

        Queue<BagWithCount> bagQueue = new ArrayDeque<>();
        bagQueue.offer(new BagWithCount(colorToBag.get(color), 1));

        long result = 0;
        while (!bagQueue.isEmpty()) {
            BagWithCount currentBagWithCount = bagQueue.poll();
            Bag bag = currentBagWithCount.getBag();
            result += currentBagWithCount.getContentCount();
            for (Map.Entry<String, Integer> entry : bag.getContent().entrySet()) {
                Bag bagContent = colorToBag.get(entry.getKey());
                bagQueue.offer(new BagWithCount(bagContent, currentBagWithCount.getContentCount() * entry.getValue()));
            }
        }

        return result - 1;
    }

}

@Value
class Bag {
    String color;
    Map<String, Integer> content;

    public boolean containsBagWithColor(String color) {
        return content.get(color) != null;
    }
}

@Value
class BagWithCount {
    Bag bag;
    int contentCount;
}
