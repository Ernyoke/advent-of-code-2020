package dev.esz.aoc.day11;

import dev.esz.aoc.utils.MathUtils;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Day11 {
    static int part1(List<String> lines) {
        String[][] seatMap = convertToSeatMap(lines);
        String[][] stableSeatMap = getStableMap(seatMap);
        return countSeats(stableSeatMap, "#");
    }

    static int part2(List<String> lines) {
        String[][] seatMap = convertToSeatMap(lines);
        String[][] stableSeatMap = getStableMapWithVisibility(seatMap);
        return countSeats(stableSeatMap, "#");
    }

    private static String[][] convertToSeatMap(List<String> lines) {
        return lines.stream()
                .map(line -> line.split(""))
                .collect(Collectors.toList())
                .toArray(new String[][]{});
    }

    private static String[][] getStableMap(String[][] seatMap) {
        String[][] newSeatMap = null;
        boolean isChange = true;
        while (isChange) {
            newSeatMap = new String[seatMap.length][seatMap[0].length];
            isChange = false;
            for (int x = 0; x < seatMap.length; x++) {
                for (int y = 0; y < seatMap[x].length; y++) {
                    if (seatMap[x][y].equals("L") && countAdjacentSeats(seatMap, x, y, "#") == 0) {
                        newSeatMap[x][y] = "#";
                        isChange = true;
                        continue;
                    }
                    if (seatMap[x][y].equals("#") && countAdjacentSeats(seatMap, x, y, "#") >= 4) {
                        newSeatMap[x][y] = "L";
                        isChange = true;
                        continue;
                    }
                    newSeatMap[x][y] = seatMap[x][y];
                }
            }
            seatMap = newSeatMap;
        }
        return newSeatMap;
    }

    private static int countAdjacentSeats(String[][] seats, int x, int y, String seatStatus) {
        int counter = 0;
        for (Direction direction : Direction.values()) {
            int newX = x + direction.getX();
            int newY = y + direction.getY();
            if (MathUtils.isBetweenInclusive(newX, 0, seats.length - 1) &&
                    MathUtils.isBetweenInclusive(newY, 0, seats[0].length - 1)
            ) {
                if (seats[newX][newY].equals(seatStatus)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int countSeats(String[][] seats, String seatStatus) {
        int counter = 0;
        for (String[] x : seats) {
            for (String y : x) {
                if (y.equals(seatStatus)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static String[][] getStableMapWithVisibility(String[][] seatMap) {
        String[][] newSeatMap = null;
        boolean isChange = true;
        while (isChange) {
            newSeatMap = new String[seatMap.length][seatMap[0].length];
            isChange = false;
            for (int x = 0; x < seatMap.length; x++) {
                for (int y = 0; y < seatMap[x].length; y++) {
                    if (seatMap[x][y].equals("L") && countAdjacentVisibleSeats(seatMap, x, y, "#") == 0) {
                        newSeatMap[x][y] = "#";
                        isChange = true;
                        continue;
                    }
                    if (seatMap[x][y].equals("#") && countAdjacentVisibleSeats(seatMap, x, y, "#") >= 5) {
                        newSeatMap[x][y] = "L";
                        isChange = true;
                        continue;
                    }
                    newSeatMap[x][y] = seatMap[x][y];
                }
            }
            seatMap = newSeatMap;
        }
        return newSeatMap;
    }

    private static int countAdjacentVisibleSeats(String[][] seats, int x, int y, String seatStatus) {
        int counter = 0;
        for (Direction direction : Direction.values()) {
            counter += getVisibleSeat(seats, x, y, direction).filter(seatStatus::equals).isPresent() ? 1 : 0;
        }
        return counter;
    }

    private static Optional<String> getVisibleSeat(String[][] seatMap, int x, int y, Direction direction) {
        int newX = x + direction.getX();
        int newY = y + direction.getY();
        while (MathUtils.isBetweenInclusive(newX, 0, seatMap.length - 1) &&
                MathUtils.isBetweenInclusive(newY, 0, seatMap[0].length - 1)
        ) {
            if (!seatMap[newX][newY].equals(".")) {
                return Optional.of(seatMap[newX][newY]);
            }
            newX = newX + direction.getX();
            newY = newY + direction.getY();
        }
        return Optional.empty();
    }
}

@Getter
enum Direction {
    U(-1, 0), D(1, 0), L(0, -1), R(0, 1),
    LU(-1, -1), LD(1, -1), RU(-1, 1), RD(1, 1);

    private final int x, y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
