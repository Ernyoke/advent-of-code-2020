package dev.esz.aoc.day12;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@SuppressWarnings("DuplicatedCode")
public interface Day12 {
    static int part1(List<String> lines) {
        return getDistance(lines);
    }

    static int part2(List<String> lines) {
        return getDistanceWithWaypoint(lines);
    }

    private static int getDistance(List<String> lines) {
        Vector shipPosition = new Vector(0, 0);
        Direction currentDirection = Direction.E;

        for (String line : lines) {
            String action = line.substring(0, 1);
            int value = Integer.parseInt(line.substring(1));
            if (List.of("N", "S", "E", "W").contains(action)) {
                Direction direction = Direction.valueOf(action);
                shipPosition = shipPosition.add(new Vector(direction.getX() * value, direction.getY() * value));
                continue;
            }
            if (action.equals("F")) {
                shipPosition = shipPosition.add(new Vector(currentDirection.getX() * value, currentDirection.getY() * value));
                continue;
            }
            if (List.of("L", "R").contains(action)) {
                Rotation rotation = new Rotation(Orientation.valueOf(action), value);
                currentDirection = currentDirection.turn(rotation);
            }
        }
        return shipPosition.distance(new Vector(0, 0));
    }

    private static int getDistanceWithWaypoint(List<String> lines) {
        Vector shipPosition = new Vector(0, 0);
        Vector wayPoint = new Vector(1, 10);
        for (String line : lines) {
            String action = line.substring(0, 1);
            int value = Integer.parseInt(line.substring(1));
            if (List.of("N", "S", "E", "W").contains(action)) {
                Direction direction = Direction.valueOf(action);
                wayPoint = wayPoint.add(new Vector(direction.getX() * value, direction.getY() * value));
                continue;
            }
            if (action.equals("F")) {
                shipPosition = shipPosition.add(wayPoint.multiply(value));
                continue;
            }
            if (List.of("L", "R").contains(action)) {
                wayPoint = wayPoint.rotate(value, Orientation.valueOf(action));
            }
        }
        return shipPosition.distance(new Vector(0, 0));
    }
}

@Getter
enum Direction {
    N(1, 0) {
        public Direction turn(Orientation orientation) {
            if (orientation == Orientation.R) {
                return Direction.E;
            }
            return Direction.W;
        }
    }, S(-1, 0) {
        public Direction turn(Orientation orientation) {
            if (orientation == Orientation.R) {
                return Direction.W;
            }
            return Direction.E;
        }
    }, E(0, 1) {
        public Direction turn(Orientation orientation) {
            if (orientation == Orientation.R) {
                return Direction.S;
            }
            return Direction.N;
        }
    }, W(0, -1) {
        public Direction turn(Orientation orientation) {
            if (orientation == Orientation.R) {
                return Direction.N;
            }
            return Direction.S;
        }
    };

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private final int x;
    private final int y;

    public abstract Direction turn(Orientation orientation);

    public Direction turn(Rotation rotation) {
        int angle = rotation.getAngle() % 360;
        Direction currentDirection = this;
        while (angle > 0) {
            currentDirection = currentDirection.turn(rotation.getOrientation());
            angle -= 90;
        }
        return currentDirection;
    }
}

enum Orientation {
    L, R
}

@RequiredArgsConstructor
@Getter
class Rotation {
    private final Orientation orientation;
    private final int angle;
}

@RequiredArgsConstructor
@Getter
class Vector {
    private final int x;
    private final int y;

    public Vector add(Vector other) {
        return new Vector(x + other.getX(), y + other.getY());
    }

    public Vector multiply(int scalar) {
        return new Vector(x * scalar, y * scalar);
    }

    public Vector rotate(int angle, Orientation orientation) {
        if (orientation == Orientation.L) {
            angle = 360 - angle;
        }
        switch (angle) {
            case 90:
                return new Vector(-y, x);
            case 180:
                return new Vector(-x, -y);
            case 270:
                return new Vector(y, -x);
            default:
                return this;
        }
    }

    public int distance(Vector other) {
        return Math.abs(x - other.getX()) + Math.abs(y - other.getY());
    }
}
