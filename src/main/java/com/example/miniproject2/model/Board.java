package com.example.miniproject2.model;

import java.util.ArrayList;
import java.util.Random;

public class Board implements IBoard {
    private final ArrayList<ArrayList<Integer>> randomMatrix = new ArrayList<>();

    public Board(){
        for (int i = 0; i < 6; i++) {
            randomMatrix.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                randomMatrix.get(i).add(0);
            }
        }
        for (int i = 0; i < 6; i++) {
            fillMatrix(i);
        }
    }
    public Board(ArrayList<ArrayList<Integer>> arrayList){
        for (int i = 0; i < 6; i++) {
            randomMatrix.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                randomMatrix.get(i).add(j, arrayList.get(i).get(j));
            }
        }
    }
    @Override
    public void fillMatrix(int i) {
        int j = 0;
        while (j < 6) {
            Random rand = new Random();
            int num = 1 + rand.nextInt(6);
            while ((checkColum(num, i, j) || checkRow(num, i)) || checkBox(num, i, j)) {
                num = 1 + rand.nextInt(6);
                if (!isAnyNumPosible(i, j)) {
                    randomMatrix.get(i).clear();
                    for (int k = 0; k < 6; k++) {
                        randomMatrix.get(i).add(0);
                    }
                    j = 0;
                }
            }
            randomMatrix.get(i).set(j, num);
            j++;
        }
    }
    @Override
    public boolean checkBox(int num, int i, int j) {
        int start_row = 3 * (i / 3);
        int start_col = 2 * (j / 2);
        for (int row = start_row; row < start_row + 3; row++) {
            for (int col = start_col; col < start_col + 2; col++) {
                if (randomMatrix.get(row).get(col) == num) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public boolean checkColum(int num, int i, int j) {
        for (int row = 0; row < i; row++) {
            if (num == randomMatrix.get(row).get(j)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean checkRow(int num, int i) {
        for (int col = 0; col < 6; col++) {
            if (num == randomMatrix.get(i).get(col)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isAnyNumPosible(int i, int j) {
        for (int num = 1; num < 7; num++) {
            if (!((checkColum(num, i, j) || checkRow(num, i)) || checkBox(num, i, j))) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyNum(int num, int i, int j){
        return !((checkColum(num, i, j) || checkRow(num, i)) || checkBox(num, i, j));
    }

    public ArrayList<ArrayList<Integer>> getRandomMatrix(){
        return randomMatrix;
    }
}
