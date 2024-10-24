package com.example.miniproject2.model;

import java.util.ArrayList;
import java.util.Random;

public interface IBoard {

    public void fillMatrix(int i);

    public boolean isAnyNumPosible(int i, int j);

    public ArrayList<ArrayList<Integer>> getRandomMatrix();
}
