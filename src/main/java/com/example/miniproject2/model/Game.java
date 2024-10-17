package com.example.miniproject2.model;

import java.util.ArrayList;

public class Game {
    private final Board board;
    private ArrayList<ArrayList<Integer>> solvedBoard;
    private ArrayList<ArrayList<Integer>> actualBoard;

    public Game(){
        board = new Board();
        solvedBoard = board.getRandomMatrix();
    }

    public void setRandomNumbers(){
        
    }

    public boolean isSolved(){
        return solvedBoard == actualBoard;
    }
}
