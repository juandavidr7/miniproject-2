package com.example.miniproject2.model;

import java.util.ArrayList;

/**
 * The {@code IGame} interface defines the contract for Sudoku game implementations.
 * It outlines the essential methods that any game class must implement to manage
 * the state and operations of a Sudoku game.
 */
public interface IGame {

    /**
     * Sets the actual board with the current state of the game.
     */
    void setActualBoard();

    /**
     * Sets blank positions in the specified matrix to create a playable Sudoku board.
     *
     * @param matrix The matrix representing the Sudoku board.
     */
    void setBlanks(ArrayList<ArrayList<Integer>> matrix);

    /**
     * Creates a copy of the provided board.
     *
     * @param board The board to copy.
     * @return A copy of the provided board.
     */
    ArrayList<ArrayList<Integer>> copy(ArrayList<ArrayList<Integer>> board);

    /**
     * Attempts to solve the current state of the actual board.
     *
     * @param actualBoard The current state of the board to solve.
     * @return {@code true} if the board was successfully solved, {@code false} otherwise.
     */
    boolean solveActualBoard(ArrayList<ArrayList<Integer>> actualBoard);

    /**
     * Checks if the current Sudoku puzzle is solved.
     *
     * @return {@code true} if the puzzle is solved, {@code false} otherwise.
     */
    boolean isSolved();

    /**
     * Retrieves the solved board configuration.
     *
     * @return An ArrayList representing the solved board.
     */
    ArrayList<ArrayList<Integer>> getSolvedBoard();

    /**
     * Retrieves the current state of the actual board.
     *
     * @return An ArrayList representing the actual board.
     */
    ArrayList<ArrayList<Integer>> getActualBoard();

    /**
     * Displays the specified board on the console.
     *
     * @param board The board to display.
     */
    void show(ArrayList<ArrayList<Integer>> board);
}
