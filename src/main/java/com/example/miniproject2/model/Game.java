package com.example.miniproject2.model;

import java.sql.Struct;
import java.util.ArrayList;
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

    public void setActualBoard(){
        for (int numBox = 1; numBox <= 6; numBox++){
            setBlanks(numBox);
        }
    }
    // Error on counting the number of positions that have to be replaced
    public boolean setBlanks(int numBox){
        int initial_row = (2 * numBox) - 2;
        int initial_col = (3 * numBox) - 3;
        Random rand = new Random();
        ArrayList<ArrayList<Integer>> actualBoardCopy;
        int row, col, num;

        // Solo permitimos un número limitado de intentos
        int maxAttempts = 10;
        for (int i = 0; i < 4; i++) {
            int attempts = 0;
            boolean placedBlank = false;
            while (attempts < maxAttempts) {
                row = rand.nextInt(2) + initial_row;
                col = rand.nextInt(3) + initial_col;

                // Verificar que row y col no excedan los límites de la matriz (0-5)
                if (row >= 6 || col >= 6) {
                    attempts++;
                    continue; // Si los índices están fuera de rango, intentar de nuevo
                }

                if (actualBoard.get(row).get(col) != 0) {
                    num = actualBoard.get(row).get(col);
                    actualBoard.get(row).set(col, 0);

                    // Crear una copia antes de resolver
                    actualBoardCopy = new ArrayList<>();
                    copy(actualBoard, actualBoardCopy);

                    actualBoardSolutions = 0;  // Resetear el contador de soluciones
                    solveActualBoard(actualBoardCopy);  // Intentamos resolver

                    // Si solo hay una solución, dejamos el espacio en blanco
                    if (actualBoardSolutions == 1) {
                        placedBlank = true;
                        break;  // Salimos del bucle while
                    } else {
                        // Si no, revertimos el cambio
                        actualBoard.get(row).set(col, num);
                    }
                }
                attempts++;
            }

            // Si no pudimos colocar un espacio en blanco en esta iteración
            if (!placedBlank) {
                return false;
            }
        }
        return true;
    }



    public void copy(ArrayList<ArrayList<Integer>> board, ArrayList<ArrayList<Integer>> boardCopy){
        for (int i = 0; i < 6; i++){
            boardCopy.add(new ArrayList<>());
            for (int j = 0 ; j < 6; j++){
                boardCopy.get(i).add(j, board.get(i).get(j));
            }
        }
    }

    public boolean solveActualBoard(ArrayList<ArrayList<Integer>> actualBoard){
        for (int row = 0; row < 6; row++){
            for (int col = 0; col < 6; col++){
                if (actualBoard.get(row).get(col) == 0){
                    for (int num = 1; num <= 6; num++){
                        if (a_board.verifyNum(num, row, col)){
                            actualBoard.get(row).set(col, num);
                            if (solveActualBoard(actualBoard)){
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
}
