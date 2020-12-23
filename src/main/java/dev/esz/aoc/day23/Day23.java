package dev.esz.aoc.day23;

import java.util.*;
import java.util.stream.Collectors;

public interface Day23 {

    // This solution was a waste of time regarding part 2. In order to solve part 2, circular linked list approach
    // should be used.
    static String part1WithRotation(List<Integer> cups, int moves) {
        int index = 0;
        List<Integer> copyCups = new ArrayList<>(cups);
        for (int i = 1; i <= moves; i++) {
            List<Integer> doubleCups = new ArrayList<>(copyCups);
            doubleCups.addAll(copyCups);
            Integer currentCup = copyCups.get(index);
            List<Integer> nextTreeCups = doubleCups.subList(index + 1, index + 4);
            Integer destinationCup = getDestinationCup(nextTreeCups, currentCup, cups.size());
            int destinationCupIndex = doubleCups.indexOf(destinationCup);
            if (destinationCupIndex < index) {
                destinationCupIndex += cups.size();
            }
            List<Integer> subList = doubleCups.subList(index + 1, destinationCupIndex + 1);
            Collections.rotate(subList, -3);
            if (destinationCupIndex >= cups.size()) {
                int offset = destinationCupIndex - copyCups.size() + 1;
                copyCups = doubleCups.subList(offset, offset + copyCups.size());
                Collections.rotate(copyCups, offset);
            } else {
                copyCups = doubleCups.subList(0, cups.size());
            }
            index++;
            if (index >= cups.size()) {
                index = 0;
            }
        }

        int oneIndex = copyCups.indexOf(1);
        Collections.rotate(copyCups, -oneIndex);
        copyCups = copyCups.subList(1, copyCups.size());

        return copyCups.stream().map(Object::toString).collect(Collectors.joining(""));
    }

    private static Integer getDestinationCup(List<Integer> nextTreeCups, Integer currentCup, int maxCup) {
        int cup = currentCup - 1;
        if (cup < 1) {
            cup = 9;
        }
        while (nextTreeCups.contains(cup)) {
            cup--;
            if (cup < 1) {
                cup = maxCup;
            }
        }
        return cup;
    }

    static String part1(List<Integer> cups, int moves) {
        Map<Integer, CircularLinkedList.Node> map = new HashMap<>();
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        for (Integer cup : cups) {
            map.put(cup, circularLinkedList.add(cup));
        }

        CircularLinkedList.Node currentCupNode = circularLinkedList.getHead();

        computeConnections(circularLinkedList, map, moves);

        CircularLinkedList.Node first = map.get(1);
        StringBuilder stringBuffer = new StringBuilder();
        CircularLinkedList.Node next = first.getNext();
        while (next != first) {
            stringBuffer.append(next.getValue());
            next = next.getNext();
        }

        return stringBuffer.toString();
    }

    static long part2(List<Integer> cups, int moves) {
        Map<Integer, CircularLinkedList.Node> map = new HashMap<>();
        CircularLinkedList circularLinkedList = new CircularLinkedList();
        for (Integer cup : cups) {
            map.put(cup, circularLinkedList.add(cup));
        }

        int max = cups.stream().mapToInt(i -> i).max().orElseThrow();

        for (int i = max + 1; i <= 1_000_000; i++) {
            map.put(i, circularLinkedList.add(i));
        }

        computeConnections(circularLinkedList, map, moves);

        CircularLinkedList.Node first = map.get(1);
        return (long) first.getNext().getValue() * first.getNext().getNext().getValue();
    }

    private static void computeConnections(CircularLinkedList circularLinkedList, Map<Integer, CircularLinkedList.Node> map, int moves) {
        CircularLinkedList.Node currentCupNode = circularLinkedList.getHead();

        for (int i = 0; i < moves; i++) {

            List<CircularLinkedList.Node> nextTreeCups = List.of(currentCupNode.getNext(),
                    currentCupNode.getNext().getNext(),
                    currentCupNode.getNext().getNext().getNext());

            CircularLinkedList.Node destinationCupNode = getDestinationCup(nextTreeCups, currentCupNode, circularLinkedList.getSize(), map);

            CircularLinkedList.Node lastCupNode = nextTreeCups.get(nextTreeCups.size() - 1);
            currentCupNode.setNext(lastCupNode.getNext());

            CircularLinkedList.Node firstCupNode = nextTreeCups.get(0);
            lastCupNode.setNext(destinationCupNode.getNext());
            destinationCupNode.setNext(firstCupNode);

            currentCupNode = currentCupNode.getNext();
        }
    }

    private static CircularLinkedList.Node getDestinationCup(List<CircularLinkedList.Node> nextTreeCups,
                                                             CircularLinkedList.Node currentCup,
                                                             int maxCup,
                                                             Map<Integer, CircularLinkedList.Node> map) {
        int cup = currentCup.getValue() - 1;
        if (cup < 1) {
            cup = maxCup;
        }
        while (nextTreeCups.contains(map.get(cup))) {
            cup--;
            if (cup < 1) {
                cup = maxCup;
            }
        }
        return map.get(cup);
    }
}
