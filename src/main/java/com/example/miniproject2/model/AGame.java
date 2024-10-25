package com.example.miniproject2.model;

import java.util.ArrayList;

/**
 * The {@code AGame} class is an abstract base class that defines the common
 * interface for different types of Sudoku games. It implements the {@code IGame}
 * interface and provides method stubs for game-related functionalities.
 */
public class AGame implements IGame {

    /**
     * Sets the actual board with the current state of the game.
     * This method should be implemented by subclasses.
     */
    @Override
    public void setActualBoard() {
        // Implementation to be provided in subclasses
    }

    /**
     * Sets blank positions in the specified matrix to create a playable Sudoku board.
     * This method should be implemented by subclasses.
     *
     * @param matrix The matrix representing the Sudoku board.
     */
    @Override
    public void setBlanks(ArrayList<ArrayList<Integer>> matrix) {
        // Implementation to be provided in subclasses
    }

    /**
     * Creates a copy of the provided board.
     * This method should be implemented by subclasses.
     *
     * @param board The board to copy.
     * @return A copy of the provided board.
     */
    @Override
    public ArrayList<ArrayList<Integer>> copy(ArrayList<ArrayList<Integer>> board) {
        return null; // Implementation to be provided in subclasses
    }

    /**
     * Attempts to solve the current state of the actual board.
     * This method should be implemented by subclasses.
     *
     * @param actualBoard The current state of the board to solve.
     * @return {@code true} if the board was successfully solved, {@code false} otherwise.
     */
    @Override
    public boolean solveActualBoard(ArrayList<ArrayList<Integer>> actualBoard) {
        return false; // Implementation to be provided in subclasses
    }

    /**
     * Checks if the current Sudoku puzzle is solved.
     * This method should be implemented by subclasses.
     *
     * @return {@code true} if the puzzle is solved, {@code false} otherwise.
     */
    @Override
    public boolean isSolved() {
        return false; // Implementation to be provided in subclasses
    }

    /**
     * Retrieves the solved board configuration.
     * This method should be implemented by subclasses.
     *
     * @return An ArrayList representing the solved board.
     */
    @Override
    public ArrayList<ArrayList<Integer>> getSolvedBoard() {
        return null; // Implementation to be provided in subclasses
    }

    /**
     * Retrieves the current state of the actual board.
     * This method should be implemented by subclasses.
     *
     * @return An ArrayList representing the actual board.
     */
    @Override
    public ArrayList<ArrayList<Integer>> getActualBoard() {
        return null; // Implementation to be provided in subclasses
    }

    /**
     * Displays the specified board on the console.
     * This method should be implemented by subclasses.
     *
     * @param board The board to display.
     */
    @Override
    public void show(ArrayList<ArrayList<Integer>> board) {
        // Implementation to be provided in subclasses
    }
}
