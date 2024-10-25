package com.example.miniproject2.model;

import java.util.ArrayList;

/**
 * The {@code ABoard} class serves as an abstract base class for different types
 * of Sudoku boards. It implements the {@code IBoard} interface and provides
 * method stubs for board-related functionalities.
 */
public class ABoard implements IBoard {

    /**
     * Fills the specified row of the board with valid numbers according to Sudoku rules.
     * This method should be implemented by subclasses.
     *
     * @param i The index of the row to fill.
     */
    @Override
    public void fillMatrix(int i) {
        // Implementation to be provided in subclasses
    }

    /**
     * Checks if any valid number can be placed in the specified cell.
     * This method should be implemented by subclasses.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     * @return {@code true} if at least one number can be placed, {@code false} otherwise.
     */
    @Override
    public boolean isAnyNumPosible(int i, int j) {
        return false; // Implementation to be provided in subclasses
    }

    /**
     * Retrieves the randomly generated matrix for the board.
     * This method should be implemented by subclasses.
     *
     * @return An ArrayList representing the random matrix of the board.
     */
    @Override
    public ArrayList<ArrayList<Integer>> getRandomMatrix() {
        return null; // Implementation to be provided in subclasses
    }
}
