package com.example.miniproject2.model;

import java.util.ArrayList;

/**
 * The {@code IBoard} interface defines the contract for Sudoku board implementations.
 * It outlines the essential methods that any board class must implement to manage
 * the state of a Sudoku game.
 */
public interface IBoard {

    /**
     * Fills the specified row of the board with valid numbers according to Sudoku rules.
     *
     * @param i The index of the row to fill.
     */
    void fillMatrix(int i);

    /**
     * Checks if any valid number can be placed in the specified cell.
     *
     * @param i The row index of the cell.
     * @param j The column index of the cell.
     * @return {@code true} if at least one number can be placed, {@code false} otherwise.
     */
    boolean isAnyNumPosible(int i, int j);

    /**
     * Retrieves the randomly generated matrix for the board.
     *
     * @return An ArrayList representing the random matrix of the board.
     */
    ArrayList<ArrayList<Integer>> getRandomMatrix();
}
