package com.example.miniproject2.model;

import java.util.Random;
import java.util.ArrayList;

/**
 * The {@code Board} class represents the game board for the Sudoku puzzle.
 * It initializes a 6x6 grid and provides methods for filling the board
 * with valid numbers according to Sudoku rules.
 */
public class Board extends ABoard {
    protected ArrayList<ArrayList<Integer>> randomMatrix = new ArrayList<>();

    /**
     * Constructs a new {@code Board} object and initializes the 6x6 grid with zeros.
     * It then fills the grid with random numbers while ensuring Sudoku rules are followed.
     */
    public Board() {
        for (int i = 0; i < 6; i++) {
            randomMatrix.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                randomMatrix.get(i).add(0);
            }
        }
        for (int i = 0; i < 6; i++) {
            fillMatrix(i);
        }
    }

    /**
     * Fills a specific row of the board with random numbers while checking
     * for Sudoku validity (no duplicates in row, column, or box).
     *
     * @param i The index of the row to fill.
     */
    public void fillMatrix(int i) {
        int j = 0;
        while (j < 6) {
            Random rand = new Random();
            int num = 1 + rand.nextInt(6);
            while ((checkColum(randomMatrix, num, i, j) || checkRow(randomMatrix, num, i, j)) || checkBox(randomMatrix, num, i, j)) {
                num = 1 + rand.nextInt(6);
                if (!isAnyNumPosible(i, j)) {
                    randomMatrix.get(i).clear();
                    for (int k = 0; k < 6; k++) {
                        randomMatrix.get(i).add(0);
                    }
                    j = 0;
                }
            }
            randomMatrix.get(i).set(j, num);
            j++;
        }
    }

    /**
     * Checks if a number is present in the 2x3 box that contains the specified cell.
     *
     * @param randomMatrix The matrix to check.
     * @param num         The number to check for.
     * @param i           The row index of the cell.
     * @param j           The column index of the cell.
     * @return {@code true} if the number is found in the box, {@code false} otherwise.
     */
    public static boolean checkBox(ArrayList<ArrayList<Integer>> randomMatrix, int num, int i, int j) {
        int start_row = 2 * (i / 2);
        int start_col = 3 * (j / 3);
        for (int row = start_row; row < start_row + 2; row++) {
            for (int col = start_col; col < start_col + 3; col++) {
                if (row == i && col == j) {
                    continue;
                }
                if (randomMatrix.get(row).get(col) == num) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if a number is present in the specified column.
     *
     * @param randomMatrix The matrix to check.
     * @param num         The number to check for.
     * @param i           The row index of the cell.
     * @param j           The column index of the cell.
     * @return {@code true} if the number is found in the column, {@code false} otherwise.
     */
    public static boolean checkColum(ArrayList<ArrayList<Integer>> randomMatrix, int num, int i, int j) {
        for (int row = 0; row < 6; row++) {
            if (row == i) {
                continue;
            }
            if (num == randomMatrix.get(row).get(j)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a number is present in the specified row.
     *
     * @param randomMatrix The matrix to check.
     * @param num         The number to check for.
     * @param i           The row index of the cell.
     * @param j           The column index of the cell.
     * @return {@code true} if the number is found in the row, {@code false} otherwise.
     */
    public static boolean checkRow(ArrayList<ArrayList<Integer>> randomMatrix, int num, int i, int j) {
        for (int col = 0; col < 6; col++) {
            if (col == j) {
                continue;
            }
            if (num == randomMatrix.get(i).get(col)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any valid number can be placed in the specified cell.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     * @return {@code true} if at least one number can be placed, {@code false} otherwise.
     */
    public boolean isAnyNumPosible(int i, int j) {
        for (int num = 1; num < 7; num++) {
            if (!((checkColum(randomMatrix, num, i, j) || checkRow(randomMatrix, num, i, j)) || checkBox(randomMatrix, num, i, j))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies if a number can be placed in the specified cell without violating Sudoku rules.
     *
     * @param matrix The matrix to check.
     * @param num    The number to check for.
     * @param i      The row index of the cell.
     * @param j      The column index of the cell.
     * @return {@code true} if the number can be placed, {@code false} otherwise.
     */
    public static boolean verifyNum(ArrayList<ArrayList<Integer>> matrix, int num, int i, int j) {
        return !((checkColum(matrix, num, i, j) || checkRow(matrix, num, i, j)) || checkBox(matrix, num, i, j));
    }

    /**
     * Gets the randomly generated matrix for the board.
     *
     * @return The 6x6 matrix representing the Sudoku board.
     */
    public ArrayList<ArrayList<Integer>> getRandomMatrix() {
        return randomMatrix;
    }
}
