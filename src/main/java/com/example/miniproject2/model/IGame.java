package com.example.miniproject2.model;

import java.util.ArrayList;
import java.util.Random;

public interface IGame {
    public void setActualBoard();

    public void setBlanks(ArrayList<ArrayList<Integer>> matrix);

    public ArrayList<ArrayList<Integer>> copy(ArrayList<ArrayList<Integer>> board);

    public boolean solveActualBoard(ArrayList<ArrayList<Integer>> actualBoard);

    public boolean isSolved();

    public ArrayList<ArrayList<Integer>> getSolvedBoard();

    public ArrayList<ArrayList<Integer>> getActualBoard();

    public void show(ArrayList<ArrayList<Integer>> board);
}
