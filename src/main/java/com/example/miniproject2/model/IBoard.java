package com.example.miniproject2.model;

import java.util.ArrayList;

public interface IBoard {

    boolean fillMatrix(int i, int j);

    boolean checkBox(int num, int i, int j);

    boolean checkColum(int num, int i, int j);

    boolean checkRow(int num, int i);

    boolean isAnyNumPosible(int i, int j);

    ArrayList<ArrayList<Integer>> getRandomMatrix();
}
