package com.example.miniproject2.model;

import java.util.ArrayList;

public class Game {
    private final Board board;
    private ArrayList<ArrayList<Integer>> solvedBoard;
    private ArrayList<ArrayList<Integer>> actualBoard;

    public Game(){
        board = new Board();
        solvedBoard = board.getRandomMatrix();
        actualBoard = new ArrayList<>();

        for (ArrayList<Integer> row : solvedBoard) {
            actualBoard.add(new ArrayList<>(row));  // Copia los valores de solvedBoard a actualBoard
        }

    }

    public void setRandomNumbers(){
        
    }

    public boolean isSolved(){
        return solvedBoard == actualBoard;
    }

    public ArrayList<ArrayList<Integer>> getActualBoard() {
        return actualBoard;
    }
}





