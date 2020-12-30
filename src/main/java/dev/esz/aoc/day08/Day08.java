package dev.esz.aoc.day08;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Day08 {
    static int part1(List<String> lines) {
        List<Instruction> instructions = convertToInstructions(lines);
        return getExecutionResult(instructions).getAccumulatorValue();
    }

    static int part2(List<String> lines) {
        List<Instruction> instructions = new ArrayList<>(convertToInstructions(lines));

        for (int i = 0; i < instructions.size(); i++) {
            Instruction originalInstruction = instructions.get(i);
            Operation operation = originalInstruction.getOperation();
            List<Operation> operationsToSwitch = List.of(Operation.jmp, Operation.nop);

            if (operationsToSwitch.contains(operation)) {
                Instruction replacement = Instruction.builder()
                        .operation(operation == Operation.jmp ? Operation.nop : Operation.jmp)
                        .argument(originalInstruction.getArgument())
                        .build();
                instructions.set(i, replacement);

                ExecutionResult executionResult = getExecutionResult(instructions);
                if (!executionResult.isInfiniteLoop()) {
                    return executionResult.getAccumulatorValue();
                }

                instructions.set(i, originalInstruction);
            }

        }
        throw new IllegalStateException();
    }

    private static List<Instruction> convertToInstructions(List<String> lines) {
        return lines.stream().map(Day08::convertToInstruction).collect(Collectors.toUnmodifiableList());
    }

    private static Instruction convertToInstruction(String line) {
        String[] parts = line.split(" ");
        return new Instruction(Operation.valueOf(parts[0]), Integer.parseInt(parts[1]));
    }

    private static ExecutionResult getExecutionResult(List<Instruction> instructions) {
        int acc = 0;
        int pc = 0;
        Set<Instruction> alreadyExecutedInstructions = new HashSet<>();

        while (pc < instructions.size()) {
            Instruction instruction = instructions.get(pc);

            if (alreadyExecutedInstructions.contains(instruction)) {
                return ExecutionResult.builder()
                        .accumulatorValue(acc)
                        .infiniteLoop(true)
                        .build();
            }

            switch (instruction.getOperation()) {
                case acc:
                    acc += instruction.getArgument();
                    pc++;
                    break;
                case jmp:
                    pc += instruction.getArgument();
                    break;
                case nop:
                    pc++;
                    break;
            }
            alreadyExecutedInstructions.add(instruction);
        }

        return ExecutionResult.builder()
                .accumulatorValue(acc)
                .infiniteLoop(false)
                .build();
    }
}

enum Operation {
    nop, acc, jmp
}

@Value
@Builder
class Instruction {
    Operation operation;
    int argument;
}

@Value
@Builder
class ExecutionResult {
    int accumulatorValue;
    boolean infiniteLoop;
}
