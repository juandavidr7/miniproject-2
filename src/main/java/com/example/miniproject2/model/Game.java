package com.example.miniproject2.model;

import java.sql.Struct;
import java.util.*;

public class Game {
    private final Board board = new Board();
    private ArrayList<ArrayList<Integer>> solvedBoard = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> actualBoard = new ArrayList<>();
    int actualBoardSolutions = 0;

    public Game() {
        solvedBoard = board.getRandomMatrix();
        actualBoard = copy(solvedBoard);
        show(solvedBoard);
        setActualBoard();
    }

    public void setActualBoard() {
        do {
            actualBoardSolutions = 0;
            actualBoard = copy(solvedBoard);
            setBlanks(actualBoard);
            ArrayList<ArrayList<Integer>> actualBoardCopy = copy(actualBoard);
            solveActualBoard(actualBoardCopy);
        } while (actualBoardSolutions != 1);
    }

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

    public ArrayList<ArrayList<Integer>> getSolvedBoard() {
        return solvedBoard;
    }

    public ArrayList<ArrayList<Integer>> getActualBoard() {
        return actualBoard;
    }

    public void show(ArrayList<ArrayList<Integer>> board) {
        System.out.println("====================================");
        for (int i = 0; i < 6; i++) {
            System.out.println(board.get(i));
        }
    }
}