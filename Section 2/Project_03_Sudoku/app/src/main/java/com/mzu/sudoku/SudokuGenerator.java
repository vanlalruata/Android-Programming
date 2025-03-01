package com.mzu.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {
    private static final int SIZE = 9;
    private static final int BOX_SIZE = 3;
    private static final int EMPTY = 0;
    private final Random random = new Random();

    public static class SudokuPuzzle {
        public int[][] numbers;
        public boolean[][] fixed;

        public SudokuPuzzle(int[][] numbers, boolean[][] fixed) {
            this.numbers = numbers;
            this.fixed = fixed;
        }
    }

    public SudokuPuzzle generate(int emptyCells) {
        int[][] solution = generateFullPuzzle();
        int[][] puzzle = copyArray(solution);
        boolean[][] fixed = new boolean[SIZE][SIZE];

        // Remove numbers to create puzzle
        removeNumbers(puzzle, emptyCells);

        // Mark fixed cells (non-empty cells)
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                fixed[i][j] = puzzle[i][j] != EMPTY;
            }
        }

        return new SudokuPuzzle(puzzle, fixed);
    }

    private int[][] generateFullPuzzle() {
        int[][] grid = new int[SIZE][SIZE];
        solveGrid(grid);
        return grid;
    }

    private boolean solveGrid(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == EMPTY) {
                    List<Integer> numbers = new ArrayList<>();
                    for (int num = 1; num <= SIZE; num++) numbers.add(num);
                    Collections.shuffle(numbers);

                    for (int num : numbers) {
                        if (isValidPlacement(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solveGrid(grid)) return true;
                            grid[row][col] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private void removeNumbers(int[][] puzzle, int emptyCells) {
        int cellsToRemove = emptyCells;
        while (cellsToRemove > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            if (puzzle[row][col] != EMPTY) {
                int backup = puzzle[row][col];
                puzzle[row][col] = EMPTY;

                // Check if puzzle remains solvable (basic check)
                int[][] testGrid = copyArray(puzzle);
                if (!hasUniqueSolution(testGrid)) {
                    puzzle[row][col] = backup; // Put back if multiple solutions
                } else {
                    cellsToRemove--;
                }
            }
        }
    }

    private boolean hasUniqueSolution(int[][] grid) {
        int[][] firstSolution = copyArray(grid);
        solveGrid(firstSolution);

        int[][] secondSolution = copyArray(grid);
        solveGrid(secondSolution);

        // Check if both solutions are identical
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (firstSolution[i][j] != secondSolution[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidPlacement(int[][] grid, int row, int col, int num) {
        // Check row
        for (int c = 0; c < SIZE; c++) {
            if (grid[row][c] == num) return false;
        }

        // Check column
        for (int r = 0; r < SIZE; r++) {
            if (grid[r][col] == num) return false;
        }

        // Check 3x3 box
        int boxRow = row - row % BOX_SIZE;
        int boxCol = col - col % BOX_SIZE;
        for (int r = boxRow; r < boxRow + BOX_SIZE; r++) {
            for (int c = boxCol; c < boxCol + BOX_SIZE; c++) {
                if (grid[r][c] == num) return false;
            }
        }

        return true;
    }

    private int[][] copyArray(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }
}
