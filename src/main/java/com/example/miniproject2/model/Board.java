package com.example.miniproject2.model;

import java.util.Random;
import java.util.ArrayList;

public class Board {
    protected ArrayList<ArrayList<Integer>> randomMatrix = new ArrayList<>();

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

    public void fillMatrix(int i) {
        int j = 0;
        while (j < 6) {
            Random rand = new Random();
            int num = 1 + rand.nextInt(6);
            while ((checkColum(randomMatrix, num, i, j) || checkRow(randomMatrix, num, i)) || checkBox(randomMatrix, num, i, j)) {
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

    public static boolean checkBox(ArrayList<ArrayList<Integer>> randomMatrix, int num, int i, int j) {
        int start_row = 2 * (i / 2);
        int start_col = 3 * (j / 3);
        for (int row = start_row; row < start_row + 2; row++) {
            for (int col = start_col; col < start_col + 3; col++) {
                if (randomMatrix.get(row).get(col) == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkColum(ArrayList<ArrayList<Integer>> randomMatrix, int num, int i, int j) {
        for (int row = 0; row < 6; row++) {
            if (num == randomMatrix.get(row).get(j)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRow(ArrayList<ArrayList<Integer>> randomMatrix, int num, int i) {
        for (int col = 0; col < 6; col++) {
            if (num == randomMatrix.get(i).get(col)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnyNumPosible(int i, int j) {
        for (int num = 1; num < 7; num++) {
            if (!((checkColum(randomMatrix, num, i, j) || checkRow(randomMatrix, num, i)) || checkBox(randomMatrix, num, i, j))) {
                return true;
            }
        }
        return false;
    }

    public static boolean verifyNum(ArrayList<ArrayList<Integer>> matrix, int num, int i, int j){
        return !((checkColum(matrix, num, i, j) || checkRow(matrix, num, i)) || checkBox(matrix, num, i, j));
    }

    public ArrayList<ArrayList<Integer>> getRandomMatrix(){
        return randomMatrix;
    }
}