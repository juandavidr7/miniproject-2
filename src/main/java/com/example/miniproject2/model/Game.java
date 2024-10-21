package com.example.miniproject2.model;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    private final Board board = new Board();
    private final Board a_board;
    private ArrayList<ArrayList<Integer>> solvedBoard;
    private ArrayList<ArrayList<Integer>> actualBoard;
    int actualBoardSolutions = 0;

    public  Game(){
        solvedBoard = board.getRandomMatrix();
        a_board = new Board(solvedBoard);
        actualBoard = a_board.getRandomMatrix();
        setActualBoard();
    }

    public void setActualBoard() {
        show(actualBoard);

        ArrayList<Integer> boxOrder = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            boxOrder.add(i);
        }
        Collections.shuffle(boxOrder);

        setBlanks(boxOrder, null);
    }

    public boolean setBlanks(ArrayList<Integer> boxOrder, ArrayList<ArrayList<Integer>> positions) {
        for (int numBox : boxOrder) {
            int initial_row = (numBox / 2) * 2;
            int initial_col = (numBox % 2 == 0) ? 0 : 3;
            int blanksNum = 0;

            if (positions == null) {
                positions = new ArrayList<>();
                for (int i = initial_row; i < initial_row + 2; i++) {
                    for (int j = initial_col; j < initial_col + 3; j++) {
                        ArrayList<Integer> position = new ArrayList<>();
                        position.add(i);
                        position.add(j);
                        positions.add(position);
                    }
                }
                Collections.shuffle(positions);
                show(positions);
            }

            for (ArrayList<Integer> pos : positions) {
                if (blanksNum < 4) {
                    int row = pos.get(0);
                    int col = pos.get(1);
                    int num = actualBoard.get(row).get(col);

                    if (num != 0) {
                        actualBoard.get(row).set(col, 0);
                        show(actualBoard);

                        ArrayList<ArrayList<Integer>> actualBoardCopy = new ArrayList<>();
                        copy(actualBoard, actualBoardCopy);

                        actualBoardSolutions = 0;
                        solveActualBoard(actualBoardCopy);

                        if (actualBoardSolutions == 1) {
                            blanksNum++;
                            if (setBlanks(boxOrder, positions)) {
                                return true;
                            }
                        }
                        actualBoard.get(row).set(col, num);
                        show(actualBoard);
                    }
                } else {
                    break;
                }
            }
            return false;
        }
        return false;
    }

    public void copy(ArrayList<ArrayList<Integer>> board, ArrayList<ArrayList<Integer>> boardCopy) {
        for (int i = 0; i < 6; i++) {
            boardCopy.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                boardCopy.get(i).add(board.get(i).get(j));
            }
        }
    }

    public boolean solveActualBoard(ArrayList<ArrayList<Integer>> actualBoard) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (actualBoard.get(row).get(col) == 0) {
                    for (int num = 1; num <= 6; num++) {
                        if (a_board.verifyNum(num, row, col)) {
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

    public boolean isSolved(){
        return solvedBoard == actualBoard;
    }

    public ArrayList<ArrayList<Integer>> getSolvedBoard(){
        return solvedBoard;
    }

    public ArrayList<ArrayList<Integer>> getActualBoard(){
        return actualBoard;
    }

    public void show(ArrayList<ArrayList<Integer>> board) {
        System.out.println("====================================");
        for (int i = 0; i < 6; i++) {
            System.out.println(board.get(i));
        }
    }
}
