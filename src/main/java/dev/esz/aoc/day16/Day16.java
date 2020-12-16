package dev.esz.aoc.day16;

import dev.esz.aoc.utils.MathUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public interface Day16 {
    static long part1(List<String> lines) {
        int i = 0;
        List<Field> fields = new ArrayList<>();
        while (true) {
            String line = lines.get(i).trim();
            if (line.isBlank() || line.isEmpty()) {
                break;
            }
            fields.add(parseField(line));
            i++;
        }

        i += 2; // skip line with text "your ticket:"

        Ticket myTicket = Ticket.fromString(lines.get(i));

        i += 3; // skip empty line and "nearby tickets:" line

        List<Ticket> tickets = new ArrayList<>();
        for (; i < lines.size(); i++) {
            tickets.add(Ticket.fromString(lines.get(i)));
        }

        return tickets.stream()
                .flatMap(ticket -> ticket.getInvalidValues(fields).stream())
                .mapToLong(value -> value).sum();
    }

    private static Field parseField(String line) {
        String[] parts = line.split(": ");
        String fieldName = parts[0];
        String[] intervalParts = parts[1].split(" or ");
        List<Interval> intervals = Arrays.stream(intervalParts).map(Interval::fromString).collect(Collectors.toUnmodifiableList());
        return new Field(fieldName, intervals);
    }

    static long part2(List<String> lines) {
        int i = 0;
        List<Field> fields = new ArrayList<>();
        while (true) {
            String line = lines.get(i).trim();
            if (line.isBlank() || line.isEmpty()) {
                break;
            }
            fields.add(parseField(line));
            i++;
        }

        i += 2; // skip line with text "your ticket:"

        Ticket myTicket = Ticket.fromString(lines.get(i));

        i += 3; // skip empty line and "nearby tickets:" line

        List<Ticket> tickets = new ArrayList<>();
        for (; i < lines.size(); i++) {
            tickets.add(Ticket.fromString(lines.get(i)));
        }

        List<Ticket> validTickets = tickets.stream()
                .filter(ticket -> ticket.getInvalidValues(fields).size() <= 0)
                .collect(Collectors.toUnmodifiableList());

        List<List<Integer>> columns = new ArrayList<>();
        for (Field field : fields) {
            columns.add(getPotentialColumns(field, validTickets));
        }

        return getFieldsWithPositionMap(fields, columns).entrySet().stream()
                .filter(entry -> entry.getKey().getName().contains("departure"))
                .mapToLong(entry -> myTicket.getValues().get(entry.getValue()))
                .reduce(1, (a, b) -> a * b);
    }

    private static List<Integer> getPotentialColumns(Field field, List<Ticket> tickets) {
        List<Integer> columns = new ArrayList<>();
        for (int i = 0; i < tickets.get(0).getValues().size(); i++) {
            boolean isValid = true;
            for (Ticket ticket : tickets) {
                int value = ticket.getValues().get(i);
                if (!field.isValid(value)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                columns.add(i);
            }
        }
        return columns;
    }

    private static Map<Field, Integer> getFieldsWithPositionMap(List<Field> fields, List<List<Integer>> columns) {
        Map<Field, Integer> map = new HashMap<>();
        for (int j = 0; j < fields.size(); j++) {
            int index = getColumnIndexWithOneElement(columns);
            int singleElement = columns.get(index).get(0);
            map.put(fields.get(index), singleElement);

            for (int k = 0; k < fields.size(); k++) {
                columns.get(k).remove(Integer.valueOf(singleElement));
            }
        }
        return map;
    }

    private static int getColumnIndexWithOneElement(List<List<Integer>> columns) {
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).size() == 1) {
                return i;
            }
        }
        throw new IllegalStateException();
    }
}

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
class Field {
    private final String name;

    @EqualsAndHashCode.Exclude
    private final List<Interval> intervals;

    public boolean isValid(int value) {
        return intervals.stream().anyMatch(interval -> interval.isValueIn(value));
    }
}

@RequiredArgsConstructor
@Getter
class Interval {
    private final int lower;
    private final int upper;

    public boolean isValueIn(int value) {
        return MathUtils.isBetweenInclusive(value, lower, upper);
    }

    public static Interval fromString(String string) {
        String[] parts = string.split("-");
        return new Interval(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
}


@RequiredArgsConstructor
@Getter
class Ticket {
    private final List<Integer> values;

    public static Ticket fromString(String string) {
        return new Ticket(Arrays.stream(string.split(",")).map(Integer::valueOf).collect(Collectors.toUnmodifiableList()));
    }

    public List<Integer> getInvalidValues(List<Field> fields) {
        return values.stream()
                .filter(value -> isValueInvalid(value, fields))
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean isValueInvalid(int value, List<Field> fields) {
        return fields.stream()
                .noneMatch(field -> field.isValid(value));
    }
}
