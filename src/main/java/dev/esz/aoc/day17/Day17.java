package dev.esz.aoc.day17;

import dev.esz.aoc.utils.MathUtils;

import java.util.Arrays;
import java.util.List;

public class Day17 {
    static final int[][] directions3D = new int[26][];

    static {
        int x = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (i == 0 && j == 0 && k == 0) {
                        continue;
                    }
                    directions3D[x] = new int[]{i, j, k};
                    x++;
                }
            }
        }
    }

    static final int[][] directions4D = new int[80][];

    static {
        int x = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (i == 0 && j == 0 && k == 0 && l == 0) {
                            continue;
                        }
                        directions4D[x] = new int[]{i, j, k, l};
                        x++;
                    }
                }
            }
        }
    }

    static int part1(List<String> lines, int iteration) {
        char[][][] cube = createInitialCube3D(lines, iteration);
        for (int i = 0; i < iteration; i++) {
            cube = conway3D(cube);
        }
        return countActive3D(cube);
    }

    static int part2(List<String> lines, int iteration) {
        char[][][][] cube = createInitialCube4D(lines, iteration);
        for (int i = 0; i < iteration; i++) {
            cube = conway4D(cube);
        }
        return countActive4D(cube);
    }

    private static char[][][] createInitialCube3D(List<String> lines, int iteration) {
        int n = lines.size() + iteration * 2;
        char[][][] cube = new char[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    cube[i][j][k] = '.';
                }
            }
        }
        int i = iteration;
        for (String line : lines) {
            int j = iteration;
            for (char c : line.toCharArray()) {
                cube[n / 2 + 1][i][j] = c;
                j++;
            }
            i++;
        }
        return cube;
    }

    private static char[][][][] createInitialCube4D(List<String> lines, int iteration) {
        int n = lines.size() + iteration * 2;
        char[][][][] cube = new char[n][n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        cube[i][j][k][l] = '.';
                    }
                }
            }
        }
        int i = iteration;
        for (String line : lines) {
            int j = iteration;
            for (char c : line.toCharArray()) {
                cube[n / 2 + 1][n / 2 + 1][i][j] = c;
                j++;
            }
            i++;
        }
        return cube;
    }

    private static char[][][] conway3D(char[][][] cube) {
        int n = cube.length;
        char[][][] newCube = new char[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int activeNeighbours = countActiveNeighbours3D(cube, i, j, k);
                    if (cube[i][j][k] == '#' && (activeNeighbours < 2 || activeNeighbours > 3)) {
                        newCube[i][j][k] = '.';
                        continue;
                    } else if (cube[i][j][k] == '.' && activeNeighbours == 3) {
                        newCube[i][j][k] = '#';
                        continue;
                    }
                    newCube[i][j][k] = cube[i][j][k];
                }
            }
        }
        return newCube;
    }

    private static char[][][][] conway4D(char[][][][] cube) {
        int n = cube.length;
        char[][][][] newCube = new char[n][n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        int activeNeighbours = countActiveNeighbours4D(cube, i, j, k, l);
                        if (cube[i][j][k][l] == '#' && (activeNeighbours < 2 || activeNeighbours > 3)) {
                            newCube[i][j][k][l] = '.';
                            continue;
                        } else if (cube[i][j][k][l] == '.' && activeNeighbours == 3) {
                            newCube[i][j][k][l] = '#';
                            continue;
                        }
                        newCube[i][j][k][l] = cube[i][j][k][l];
                    }
                }
            }
        }
        return newCube;
    }

    private static int countActiveNeighbours3D(char[][][] cube, int x, int y, int z) {
        int count = 0;
        int n = cube.length;
        for (int[] direction : directions3D) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            int newZ = z + direction[2];
            if (MathUtils.isBetweenInclusive(newX, 0, n - 1)
                    && MathUtils.isBetweenInclusive(newY, 0, n - 1)
                    && MathUtils.isBetweenInclusive(newZ, 0, n - 1)) {
                if (cube[newX][newY][newZ] == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countActiveNeighbours4D(char[][][][] cube, int x, int y, int z, int w) {
        int count = 0;
        int n = cube.length;
        for (int[] direction : directions4D) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            int newZ = z + direction[2];
            int newW = w + direction[3];
            if (MathUtils.isBetweenInclusive(newX, 0, n - 1)
                    && MathUtils.isBetweenInclusive(newY, 0, n - 1)
                    && MathUtils.isBetweenInclusive(newZ, 0, n - 1)
                    && MathUtils.isBetweenInclusive(newW, 0, n - 1)) {
                if (cube[newX][newY][newZ][newW] == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countActive3D(char[][][] cube) {
        int n = cube.length;
        int count = 0;
        for (char[][] chars : cube) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (chars[j][k] == '#') {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static int countActive4D(char[][][][] cube) {
        int n = cube.length;
        int count = 0;
        for (char[][][] chars : cube) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        if (chars[j][k][l] == '#') {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private static void printDimension(char[][][] cube, int d) {
        for (int i = 0; i < cube.length; i++) {
            System.out.println(Arrays.toString(cube[d][i]));
        }
    }
}
