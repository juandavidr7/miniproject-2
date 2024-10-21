package com.example.miniproject2.controller;

import com.example.miniproject2.model.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class GameController {

    @FXML
    private GridPane boardGrid;

    @FXML
    private Label timerLabel;

    private int secondsPassed = 0;
    private Timeline timeline;

    private Game game;
    private ArrayList<TextField> textFields = new ArrayList<>();

    public GameController() {
        game = new Game();
    }

    @FXML
    public void initialize() {
        createTextFields();
        startTimer();
    }

    public void startTimer() {

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsPassed++;
            updateLabel();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateLabel() {
        // Actualizar el texto del label con el tiempo transcurrido
        timerLabel.setText("Time: " + secondsPassed + "s");
    }

    // Puedes añadir más funciones, como pausar o reiniciar el temporizador si es necesario
    public void stopTimer() {
        timeline.stop();
    }

    public void resetTimer() {
        stopTimer();
        secondsPassed = 0;
        updateLabel();
    }

    public void createTextFields() {
        // Limpiar cualquier elemento anterior del GridPane
        boardGrid.getChildren().clear();

        textFields.clear();
        // Limpiar la lista de textFields para prepararla para los nuevos campos

        for (int i = 0; i < 6; i++) {  // Iterar sobre las filas
            for (int j = 0; j < 6; j++) {  // Iterar sobre las columnas, comenzando desde 0
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

                // Añadir TextField al GridPane en la posición correspondiente (incluyendo la columna 0)
                boardGrid.add(txt, j, i);
                textFields.add(txt);

                // Agregar evento de teclado si es necesario
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
