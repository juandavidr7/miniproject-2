package com.example.miniproject2.model;

import java.sql.Struct;
import java.util.*;

/**
 * The {@code Game} class represents a Sudoku game with a 6x6 grid.
 * It handles the game logic, including generating a solvable puzzle,
 * managing the actual board and solution, and checking if the puzzle is solved.
 */
public class Game extends AGame {
    private final Board board = new Board();
    private ArrayList<ArrayList<Integer>> solvedBoard = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> actualBoard = new ArrayList<>();
    int actualBoardSolutions = 0;

    /**
     * Constructs a new {@code Game} object, initializes the solved board, and creates the actual game board
     * by removing clues to create a solvable puzzle with only one solution.
     */
    public Game() {
        solvedBoard = board.getRandomMatrix();
        actualBoard = copy(solvedBoard);
        show(solvedBoard);
        setActualBoard();
    }

    /**
     * Randomly removes values from the solved board to create the actual game board,
     * ensuring that the board has exactly one solution.
     */
    public void setActualBoard() {
        do {
            actualBoardSolutions = 0;
            actualBoard = copy(solvedBoard);
            setBlanks(actualBoard);
            ArrayList<ArrayList<Integer>> actualBoardCopy = copy(actualBoard);
            solveActualBoard(actualBoardCopy);
        } while (actualBoardSolutions != 1);
    }

    /**
     * Randomly selects positions in each 2x3 subgrid of the board and sets the remaining positions to 0 (blank).
     *
     * @param matrix The matrix to modify by setting blank positions.
     */
    public void setBlanks(ArrayList<ArrayList<Integer>> matrix) {
        Random random = new Random();
        for (int row = 0; row < 6; row += 2) {
            for (int col = 0; col < 6; col += 3) {
                int[][] positions = new int[6][2];
                int index = 0;
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        positions[index][0] = row + i;
                        positions[index][1] = col + j;
                        index++;
                    }
                }
                int clue1 = random.nextInt(6);
                int clue2 = random.nextInt(6);
                while (clue1 == clue2) {
                    clue2 = random.nextInt(6);
                }
                for (int i = 0; i < 6; i++) {
                    if (i != clue1 && i != clue2) {
                        int fila = positions[i][0];
                        int columna = positions[i][1];
                        matrix.get(fila).set(columna, 0);
                    }
                }
            }
        }
    }

    /**
     * Creates a deep copy of the provided 2D board.
     *
     * @param board The original board to copy.
     * @return A deep copy of the board.
     */
    public ArrayList<ArrayList<Integer>> copy(ArrayList<ArrayList<Integer>> board) {
        ArrayList<ArrayList<Integer>> boardCopy = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boardCopy.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                boardCopy.get(i).add(board.get(i).get(j));
            }
        }
        return boardCopy;
    }

    /**
     * Solves the actual game board using a backtracking algorithm and counts the number of solutions.
     *
     * @param actualBoard The current board to solve.
     * @return {@code true} if the board has a solution, {@code false} otherwise.
     */
    public boolean solveActualBoard(ArrayList<ArrayList<Integer>> actualBoard) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (actualBoard.get(row).get(col) == 0) {
                    for (int num = 1; num <= 6; num++) {
                        if (Board.verifyNum(actualBoard, num, row, col)) {
                            actualBoard.get(row).set(col, num);
                            if (solveActualBoard(actualBoard)) {
                                return true;
                            }
                            actualBoard.get(row).set(col, 0);
                        }
                    }
                    return false;
                }
            }
        }
        actualBoardSolutions++;
        return actualBoardSolutions > 1;
    }

    /**
     * Checks if the current state of the board matches the solved board.
     *
     * @return {@code true} if the board is solved, {@code false} otherwise.
     */
    public boolean isSolved() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (!solvedBoard.get(i).get(j).equals(actualBoard.get(i).get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the solved board for the game.
     *
     * @return The solved 6x6 board.
     */
    public ArrayList<ArrayList<Integer>> getSolvedBoard() {
        return solvedBoard;
    }

    /**
     * Gets the current state of the actual game board.
     *
     * @return The current 6x6 game board.
     */
    public ArrayList<ArrayList<Integer>> getActualBoard() {
        return actualBoard;
    }

    /**
     * Displays the board in the console.
     *
     * @param board The board to display.
     */
    public void show(ArrayList<ArrayList<Integer>> board) {
        System.out.println("====================================");
        for (int i = 0; i < 6; i++) {
            System.out.println(board.get(i));
        }
    }
}
