package com.example.miniproject2.controller;

import com.example.miniproject2.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GameController {

    @FXML
    private GridPane boardGrid;
    // Contenedor Grid Pane de la cuadrícula

    private Game game;
    private ArrayList<TextField> textFields = new ArrayList<>();

    public GameController() {
        game = new Game();
    }

    @FXML
    public void initialize() {
        createTextFields();   //Crear los textField al iniciar
    }

    public void createTextFields() {
        boardGrid.getChildren().clear();
        //Limpiar cualquier elemento anterior
        textFields.clear();
        //Limpiar la lista de textFields

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                TextField txt = new TextField();
                txt.setPrefWidth(80);
                txt.setPrefHeight(70);
                txt.setStyle("-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-alignment: center;");

                // Asignar el valor actual de la matriz
                Integer individualValue = game.getActualBoard().get(i).get(j);
                txt.setText(individualValue == 0 ? "" : individualValue.toString());

                // Añadir TextField al GridPane en la posición correspondiente
                boardGrid.add(txt, j, i);
                textFields.add(txt);

                // Si quieres agregar algún evento de teclado o acción, puedes añadirlo aquí
                onKeyTxtTyped(txt, i, j);
            }
        }
    }

    // Metodo para manejar eventos cuando se tipea en un campo
    private void onKeyTxtTyped(TextField txt, int row, int col) {
        txt.setOnKeyTyped(event -> {
            String input = txt.getText();
            if (input.matches("\\d")) {  // Si el valor ingresado es un número
                game.getActualBoard().get(row).set(col, Integer.parseInt(input));
            } else {
                game.getActualBoard().get(row).set(col, 0);  // Si no es válido, pon 0
            }
        });
    }
}
