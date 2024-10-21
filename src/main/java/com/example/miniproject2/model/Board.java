package com.example.miniproject2.model;

import java.util.ArrayList;
import java.util.Random;

public class Board implements IBoard {
    private final ArrayList<ArrayList<Integer>> randomMatrix = new ArrayList<>();

    public Board(){
        // Inicializa la matriz 6x6 con ceros
        for (int i = 0; i < 6; i++) {
            randomMatrix.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                randomMatrix.get(i).add(0);
            }
        }
        // Llenar la matriz utilizando el algoritmo de backtracking
        fillMatrix(0, 0);
    }

    public Board(ArrayList<ArrayList<Integer>> arrayList){
        for (int i = 0; i < 6; i++) {
            randomMatrix.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                randomMatrix.get(i).add(arrayList.get(i).get(j));
            }
        }
    }

    @Override
    public boolean fillMatrix(int i, int j) {
        // Si llegamos al final de la última fila, el tablero está completo
        if (i == 6) {
            return true;
        }

        // Determinar la siguiente celda (nextRow y nextCol)
        int nextRow = (j == 5) ? i + 1 : i; // Si alcanzamos el final de la fila, pasar a la siguiente fila
        int nextCol = (j + 1) % 6; // Pasar a la siguiente columna, o reiniciar en 0 si es el final de la fila

        if (randomMatrix.get(i).get(j) != 0) { // Si la celda ya está llena, pasar a la siguiente
            return fillMatrix(nextRow, nextCol);
        }

        Random rand = new Random();
        ArrayList<Integer> availableNumbers = new ArrayList<>();
        for (int num = 1; num <= 6; num++) {
            availableNumbers.add(num); // Agregar números del 1 al 6
        }

        // Intentar colocar números aleatorios en la celda
        while (!availableNumbers.isEmpty()) {
            int num = availableNumbers.remove(rand.nextInt(availableNumbers.size())); // Seleccionar un número aleatorio

            // Verificar si el número cumple con las restricciones
            if (!checkColum(num, i, j) && !checkRow(num, i) && !checkBox(num, i, j)) {
                randomMatrix.get(i).set(j, num); // Colocar el número si es válido

                // Intentar rellenar la siguiente celda
                if (fillMatrix(nextRow, nextCol)) {
                    return true; // Si se pudo llenar el siguiente, continuar
                }

                // Si no fue posible, revertir el cambio
                randomMatrix.get(i).set(j, 0);
            }
        }

        return false; // Si no es posible colocar ningún número válido
    }

    @Override
    public boolean checkBox(int num, int i, int j) {
        // Calcular la posición inicial de la subcuadrícula 2x3
        int start_row = (i / 2) * 2; // Cada subcuadrícula tiene 2 filas
        int start_col = (j / 3) * 3; // Cada subcuadrícula tiene 3 columnas

        for (int row = start_row; row < start_row + 2; row++) {
            for (int col = start_col; col < start_col + 3; col++) {
                if (randomMatrix.get(row).get(col) == num) {
                    return true; // El número ya está en la subcuadrícula
                }
            }
        }
        return false; // El número no está en la subcuadrícula
    }


    @Override
    public boolean checkColum(int num, int i, int j) {
        // Verificar si el número ya está en la columna
        for (int row = 0; row < i; row++) {
            if (randomMatrix.get(row).get(j) == num) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkRow(int num, int i) {
        // Verificar si el número ya está en la fila
        for (int col = 0; col < 6; col++) {
            if (randomMatrix.get(i).get(col) == num) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isAnyNumPosible(int i, int j) {
        // Verificar si algún número es posible en la posición (i, j)
        for (int num = 1; num <= 6; num++) {
            if (!checkColum(num, i, j) && !checkRow(num, i) && !checkBox(num, i, j)) {
                return true;
            }
        }
        return false;
    }


    public ArrayList<ArrayList<Integer>> getRandomMatrix(){
        return randomMatrix;
    }
}
