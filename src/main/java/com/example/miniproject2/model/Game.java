package com.example.miniproject2.model;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final Board board = new Board();
    private final Board a_board;
    private ArrayList<ArrayList<Integer>> solvedBoard;
    private ArrayList<ArrayList<Integer>> actualBoard;

    public Game() {
        solvedBoard = board.getRandomMatrix(); // Genera una matriz solucionada aleatoria
        a_board = new Board(solvedBoard); // Crea una copia del tablero solucionado
        actualBoard = new ArrayList<>(); // Inicializa el tablero actual
        copy(solvedBoard, actualBoard); // Copia el tablero solucionado para modificarlo
        setActualBoard(); // Configura el tablero con las pistas visibles y el resto en ceros
    }

    public void setActualBoard() {
        Random rand = new Random();

        // Dividir el tablero en 6 subcuadrículas 2x3
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 2; boxCol++) {

                // Obtener todas las posiciones dentro de la subcuadrícula 2x3
                ArrayList<int[]> positions = new ArrayList<>();
                for (int row = boxRow * 2; row < (boxRow * 2) + 2; row++) {
                    for (int col = boxCol * 3; col < (boxCol * 3) + 3; col++) {
                        positions.add(new int[]{row, col});
                    }
                }

                // Seleccionar 2 posiciones aleatorias para dejar visibles
                ArrayList<int[]> visiblePositions = new ArrayList<>();
                while (visiblePositions.size() < 2) {
                    int[] pos = positions.remove(rand.nextInt(positions.size()));
                    visiblePositions.add(pos);
                }

                // Colocar ceros en las posiciones no seleccionadas de la subcuadrícula
                for (int[] pos : positions) {
                    int row = pos[0];
                    int col = pos[1];
                    actualBoard.get(row).set(col, 0); // Colocar 0 en las posiciones no visibles
                }

                // Mantener visibles los números en las posiciones seleccionadas
                for (int[] pos : visiblePositions) {
                    int row = pos[0];
                    int col = pos[1];
                    actualBoard.get(row).set(col, solvedBoard.get(row).get(col)); // Mantener visible
                }
            }
        }
    }


    // Metodo para copiar un tablero a otro
    public void copy(ArrayList<ArrayList<Integer>> board, ArrayList<ArrayList<Integer>> boardCopy) {
        for (int i = 0; i < 6; i++) {
            boardCopy.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                boardCopy.get(i).add(j, board.get(i).get(j));
            }
        }
    }

    // Comprobar si el tablero ha sido resuelto
    public boolean isSolved() {
        return solvedBoard == actualBoard;
    }

    // Obtener el tablero resuelto
    public ArrayList<ArrayList<Integer>> getSolvedBoard() {
        return solvedBoard;
    }

    // Obtener el tablero actual
    public ArrayList<ArrayList<Integer>> getActualBoard() {
        return actualBoard;
    }
}
