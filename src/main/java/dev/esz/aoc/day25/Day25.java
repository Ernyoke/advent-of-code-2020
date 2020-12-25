package dev.esz.aoc.day25;

public interface Day25 {
    static long part1(long publicKey1, long publicKey2) {
        long loopSize2 = getLoopSize(publicKey2);
        return getEncryptionKey(publicKey1, loopSize2);
    }

    private static long getLoopSize(long publicKey) {
        final long subjectNumber = 7;
        final long div = 20201227;
        long value = 1;
        long loopSize = 0;
        while (value != publicKey) {
            value *= subjectNumber;
            value %= div;
            loopSize++;
        }
        return loopSize;
    }

    private static long getEncryptionKey(long publicKey, long loopSize) {
        long div = 20201227;
        long value = 1;
        for (int i = 0; i < loopSize; i++) {
            value *= publicKey;
            value %= div;
        }
        return value;
    }
}
