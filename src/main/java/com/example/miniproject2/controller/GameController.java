package com.example.miniproject2.controller;

import com.example.miniproject2.model.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;

public class GameController implements ITimer, IFields {

    @FXML
    private GridPane boardGrid;
    @FXML
    private Label timerLabel;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button buttonStartTime;

    private int secondsPassed = 0;
    private Timeline timeline;
    private TextField activeTextField = null;
    private Game game;
    private ArrayList<TextField> textFields = new ArrayList<>();

    public GameController() {
        game = new Game();
    }

    @FXML
    public void initialize() {
        createTextFields();
        startTimer();
        setButtonEvents();

    }

    public void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsPassed++;
            updateLabel();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void updateLabel() {
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
                txt.setText(individualValue == 0 ? "" : individualValue.toString()); // Si el valor en matriz es 0 dejar en blanco

                // Añadir TextField al GridPane en la posición correspondiente (incluyendo la columna 0)
                boardGrid.add(txt, j, i);
                textFields.add(txt);

                txt.setOnMouseClicked(event -> activeTextField = txt);
                // Agregar evento de teclado si es necesario
                onKeyTxtTyped(txt, i, j);
            }
        }
    }

    // Metodo para manejar eventos cuando se tipea en un campo
    private void onKeyTxtTyped(TextField txt, int row, int col) {
        txt.setOnKeyTyped(event -> {
            String input = txt.getText();

            // Asegurarse de que solo se ingrese un número del 1 al 6
            if (input.length() == 1) {
                char c = input.charAt(0);
                if (Character.isDigit(c)) {
                    int num = Character.getNumericValue(c);
                    if (num >= 1 && num <= 6) {
                        game.getActualBoard().get(row).set(col, num);
                        validateAndColorField(txt, row, col, num);
                    } else {
                        txt.clear();
                    }
                } else {
                    txt.clear();
                }
            } else {
                txt.clear();
                txt.positionCaret(1);
            }
        });
    }

    // Método para asignar eventos a los botones
    private void setButtonEvents() {
        button1.setOnAction(event -> insertNumberIntoActiveTextField(1));
        button2.setOnAction(event -> insertNumberIntoActiveTextField(2));
        button3.setOnAction(event -> insertNumberIntoActiveTextField(3));
        button4.setOnAction(event -> insertNumberIntoActiveTextField(4));
        button5.setOnAction(event -> insertNumberIntoActiveTextField(5));
        button6.setOnAction(event -> insertNumberIntoActiveTextField(6));
    }

    // Método para insertar un número en el TextField actualmente seleccionado
    // Método para insertar un número en el TextField actualmente seleccionado
    public void insertNumberIntoActiveTextField(int number) {
        if (activeTextField != null) {
            // Obtener la posición del TextField activo
            int index = textFields.indexOf(activeTextField);
            int row = index / 6; // Fila
            int col = index % 6; // Columna

            // Insertar el número
            activeTextField.setText(String.valueOf(number));
            game.getActualBoard().get(row).set(col, number);

            // Validar y colorear campo
            validateAndColorField(activeTextField, row, col, number);
        }
    }



    // Método para validar y colorear el borde del TextField
    public void validateAndColorField(TextField txt, int row, int col, int number) {
        // Comparar el número ingresado con la solución
        if (number == game.getSolvedBoard().get(row).get(col)) {
            // Si es correcto, mantener el borde blanco
            txt.setStyle("-fx-font-size: 25px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-color: transparent;" +
                    "-fx-border-color: green;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 5px;" +
                    "-fx-text-alignment: center;");
        } else {
            // Si es incorrecto, colorear el borde de rojo
            txt.setStyle("-fx-font-size: 25px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-color: transparent;" +
                    "-fx-border-color: red;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 5px;" +
                    "-fx-text-alignment: center;");
        }
    }

}
