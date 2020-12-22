package dev.esz.aoc.day22;

import java.util.*;
import java.util.stream.Collectors;

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
        Queue<Integer> queue = new ArrayDeque<>();
        for (String line : lines) {
            if (line.startsWith("Player")) {
                continue;
            }
            queue.offer(Integer.valueOf(line));
        }
        return queue;
    }

    private static long playGame(Queue<Integer> player1Deck, Queue<Integer> player2Deck) {
        switch (playSubGame(player1Deck, player2Deck)) {
            case PLAYER1:
                return computeWinnerResult(player1Deck);
            case PLAYER2:
                return computeWinnerResult(player2Deck);
            default:
                throw new IllegalStateException();
        }
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

            Winner winner;
            if (card1 <= player1Deck.size() && card2 <= player2Deck.size()) {
                winner = playSubGame(createSubDeck(player1Deck, card1), createSubDeck(player2Deck, card2));
            } else {
                winner = card1 > card2 ? Winner.PLAYER1 : Winner.PLAYER2;
            }

            switch (winner) {
                case PLAYER1: {
                    player1Deck.offer(card1);
                    player1Deck.offer(card2);
                    break;
                }
                case PLAYER2: {
                    player2Deck.offer(card2);
                    player2Deck.offer(card1);
                    break;
                }
            }
        }

        if (!player1Deck.isEmpty()) {
            return computeWinnerResult(player1Deck);
        }

        return computeWinnerResult(player2Deck);
    }

    private static Queue<Integer> createSubDeck(Queue<Integer> deck, int size) {
        return deck.stream()
                .limit(size)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private static Winner playSubGame(Queue<Integer> player1Deck, Queue<Integer> player2Deck) {
        Set<List<Integer>> player1History = new HashSet<>();
        Set<List<Integer>> player2History = new HashSet<>();

        while (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
            List<Integer> player1List = new ArrayList<>(player1Deck);
            List<Integer> player2List = new ArrayList<>(player2Deck);

            if (player1History.contains(player1List) || player2History.contains(player2List)) {
                return Winner.PLAYER1;
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
            return Winner.PLAYER1;
        }
        return Winner.PLAYER2;
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

enum Winner {
    PLAYER1, PLAYER2
}
