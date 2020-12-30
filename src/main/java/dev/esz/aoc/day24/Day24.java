package dev.esz.aoc.day24;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

import java.util.*;

public interface Day24 {
    static int part1(List<String> lines) {
        return getBlackTiles(lines).size();
    }

    static int part2(List<String> lines, int days) {
        Set<Position> blackTiles = getBlackTiles(lines);

        for (int i = 0; i < days; i++) {
            Set<Position> nextBlackTiles = new HashSet<>();
            for (Position tile : blackTiles) {
                List<Position> adjacentTiles = tile.getAdjacentPositions();
                long nrAdjacentBlackTiles = countCommonTiles(blackTiles, adjacentTiles);
                if (!(nrAdjacentBlackTiles == 0 || nrAdjacentBlackTiles > 2)) {
                    nextBlackTiles.add(tile);
                }
                // Handle white tiles
                for (Position adjacentTile : adjacentTiles) {
                    if (!blackTiles.contains(adjacentTile)) {
                        long nrPossibleBlackTiles = countCommonTiles(blackTiles, adjacentTile.getAdjacentPositions());
                        if (nrPossibleBlackTiles == 2) {
                            nextBlackTiles.add(adjacentTile);
                        }
                    }
                }
            }
            blackTiles = nextBlackTiles;
        }

        return blackTiles.size();
    }

    private static Set<Position> getBlackTiles(List<String> lines) {
        Set<Position> blackTiles = new HashSet<>();
        for (String line : lines) {
            Position tilePosition = computeTilePosition(line);
            if (!blackTiles.contains(tilePosition)) {
                blackTiles.add(tilePosition);
            } else {
                blackTiles.remove(tilePosition);
            }
        }
        return blackTiles;
    }

    private static Position computeTilePosition(String line) {
        String directionsLine = line.trim();
        Position position = new Position(0, 0);
        while (!directionsLine.isBlank()) {
            int charsToTake = 2;
            if (directionsLine.startsWith("e") || directionsLine.startsWith("w")) {
                charsToTake = 1;
            }
            String directionString = directionsLine.substring(0, charsToTake);
            directionsLine = directionsLine.substring(charsToTake);
            position = position.add(Direction.valueOf(directionString.toUpperCase(Locale.ROOT)));
        }
        return position;
    }

    private static long countCommonTiles(Set<Position> tiles, List<Position> otherTiles) {
        return otherTiles.stream()
                .filter(tiles::contains)
                .count();
    }
}


@Getter
enum Direction {
    E(2, 0), SE(1, -1), SW(-1, -1), W(-2, 0), NW(-1, 1), NE(1, 1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

@Value
@EqualsAndHashCode
@ToString
class Position {
    int x, y;

    public Position add(Direction direction) {
        return new Position(x + direction.getX(), y + direction.getY());
    }

    public List<Position> getAdjacentPositions() {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            positions.add(this.add(direction));
        }
        return positions;
    }
}
