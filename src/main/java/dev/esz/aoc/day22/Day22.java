package dev.esz.aoc.day22;

import java.util.*;

public interface Day22 {

    static long part1(List<String> lines) {
        int i = 0;

        List<String> player1Lines = new ArrayList<>();
        for (; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isBlank()) {
                break;
            }
            player1Lines.add(line);
        }

        i++;

        List<String> player2Lines = new ArrayList<>();
        for (; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isBlank()) {
                break;
            }
            player2Lines.add(line);
        }

        Queue<Integer> player1 = parsePlayerLines(player1Lines);
        Queue<Integer> player2 = parsePlayerLines(player2Lines);

        return playGame(player1, player2);
    }

    static long part2(List<String> lines) {
        int i = 0;

        List<String> player1Lines = new ArrayList<>();
        for (; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isBlank()) {
                break;
            }
            player1Lines.add(line);
        }

        i++;

        List<String> player2Lines = new ArrayList<>();
        for (; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isBlank()) {
                break;
            }
            player2Lines.add(line);
        }

        Queue<Integer> player1 = parsePlayerLines(player1Lines);
        Queue<Integer> player2 = parsePlayerLines(player2Lines);

        return playRecursiveGame(player1, player2);
    }

    private static Queue<Integer> parsePlayerLines(List<String> lines) {
        Queue<Integer> deque = new ArrayDeque<>();
        for (String line : lines) {
            if (line.startsWith("Player")) {
                continue;
            }
            deque.offer(Integer.valueOf(line));
        }
        return deque;
    }

    private static long playGame(Queue<Integer> player1Deck, Queue<Integer> player2Deck) {
        if (subGame(player1Deck, player2Deck) == 1) {
            return computeWinnerResult(player1Deck);
        }

        return computeWinnerResult(player2Deck);
    }

    private static long playRecursiveGame(Queue<Integer> player1Deck, Queue<Integer> player2Deck) {
        Set<List<Integer>> player1History = new HashSet<>();
        Set<List<Integer>> player2History = new HashSet<>();

        while (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {

            List<Integer> player1List = new ArrayList<>(player1Deck);
            List<Integer> player2List = new ArrayList<>(player2Deck);

            if (player1History.contains(player1List) || player2History.contains(player2List)) {
                return computeWinnerResult(player1Deck);
            }

            player1History.add(player1List);
            player2History.add(player2List);

            Integer card1 = player1Deck.poll();
            Integer card2 = player2Deck.poll();

            if (card1 <= player1Deck.size() && card2 <= player2Deck.size()) {
                if (subGame(createSubDeck(player1Deck, card1), createSubDeck(player2Deck, card2)) == 1) {
                    player1Deck.offer(card1);
                    player1Deck.offer(card2);
                } else {
                    player2Deck.offer(card2);
                    player2Deck.offer(card1);
                }
                continue;
            }

            if (card1 > card2) {
                player1Deck.offer(card1);
                player1Deck.offer(card2);
            } else {
                player2Deck.offer(card2);
                player2Deck.offer(card1);
            }
        }

        if (!player1Deck.isEmpty()) {
            return computeWinnerResult(player1Deck);
        }

        return computeWinnerResult(player2Deck);
    }

    private static Queue<Integer> createSubDeck(Queue<Integer> deque, int size) {
        Queue<Integer> subDeque = new ArrayDeque<>();
        int i = 0;
        for (Iterator<Integer> iterator = deque.iterator(); iterator.hasNext() && i < size; i++) {
            subDeque.offer(iterator.next());
        }
        return subDeque;
    }

    private static int subGame(Queue<Integer> player1Deck, Queue<Integer> player2Deck) {
        Set<List<Integer>> player1History = new HashSet<>();
        Set<List<Integer>> player2History = new HashSet<>();

        while (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
            List<Integer> player1List = new ArrayList<>(player1Deck);
            List<Integer> player2List = new ArrayList<>(player2Deck);

            if (player1History.contains(player1List) || player2History.contains(player2List)) {
                return 1;
            }

            player1History.add(player1List);
            player2History.add(player2List);

            Integer card1 = player1Deck.poll();
            Integer card2 = player2Deck.poll();
            if (card1 > card2) {
                player1Deck.offer(card1);
                player1Deck.offer(card2);
            } else {
                player2Deck.offer(card2);
                player2Deck.offer(card1);
            }
        }

        if (!player1Deck.isEmpty()) {
            return 1;
        }
        return 2;
    }

    private static long computeWinnerResult(Queue<Integer> player) {
        long sum = 0;
        int size = player.size();
        for (int i = 0; !player.isEmpty(); i++) {
            sum += ((long) player.poll() * (size - i));
        }
        return sum;
    }
}
