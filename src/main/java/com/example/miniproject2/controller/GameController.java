package com.example.miniproject2.controller;

import com.example.miniproject2.model.Board;
import com.example.miniproject2.model.Game;
import com.example.miniproject2.view.GameStage;
import com.example.miniproject2.view.WelcomeStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameController implements ITimer, IFields {

    @FXML
    private GridPane boardGrid;
    @FXML
    private Label timerLabel;
    @FXML
    private Button exitButton;
    @FXML
    private Button restartButton;
    @FXML
    private Button helButton;
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
    @FXML
    private Label messagesLabel;
    @FXML
    private Label helpsLabel;

    private final String[][] messagesBox = {
            {"El numero ingresado es correcto :D", "Genial, parece que vas por buen camino...", "Excelente elección"},
            {"No, no, no, ese número no pertenece aquí", "Lo lamento, el número es incorrecto", "Creo que puedes hacerlo mejor"},
            {"Aquí tienes, una ayudita...", "Y que tal si haces esto...", "Sabía que no podías hacerlo solo"},
            {"Lo lamento amigo, no te quedan más ayudas", "Ahora tendrás que continuar sin mi", "¿Más ayudas? Pero si ya te he dado tres!!"},
            {"Espectacular!! Has ganado", "Felicidades, has vencido el juego", "Lo hiciste increible, ¿qué tal otra ronda?"}
    };
    private int secondsPassed = 0;
    private Timeline timeline;
    private TextField activeTextField = null;
    private Game game = new Game();
    private ArrayList<ArrayList<TextField>> textFields = new ArrayList<>();
    private int helpsLeft = 3;
    private String currentMessage = "";

    @FXML
    public void initialize() {
        messagesLabel.setWrapText(true);
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
        timerLabel.setText("Tiempo: " + secondsPassed + "s");
    }

    public void stopTimer() {
        timeline.stop();
    }

    public void resetTimer() {
        stopTimer();
        secondsPassed = 0;
        updateLabel();
    }
    @Override
    public void createTextFields() {
        boardGrid.getChildren().clear();
        textFields.clear();
        for (int i = 0; i < 6; i++) {
            textFields.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                TextField txt = new TextField();
                txt.setPrefWidth(100);
                txt.setPrefHeight(90);
                txt.setStyle("-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 1px;" +
                        "-fx-text-alignment: center;");

                Integer individualValue = game.getActualBoard().get(i).get(j);
                if (individualValue != 0) {
                    txt.setText(individualValue.toString());
                    txt.setDisable(true);
                    txt.setStyle(txt.getStyle() +
                            "-fx-text-fill: darkgreen;" +
                            "-fx-background-color: lightgreen;" +
                            "-fx-border-color: green;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-radius: 5px;");
                }

                boardGrid.add(txt, j, i);
                textFields.get(i).add(txt);

                txt.setOnMouseClicked(event -> activeTextField = txt);
                onKeyTxtTyped(txt, i, j);
            }
        }
    }

    private void onKeyTxtTyped(TextField txt, int row, int col) {
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.equals(newValue, "")){
                validateAndColorFields(txt, row, col, 0);
                textFields.get(row).get(col).setStyle("-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-text-alignment: center;");
            }
        });
        txt.setOnKeyTyped(event -> {
            String input = txt.getText();
            if (input.length() == 1) {
                char c = input.charAt(0);
                if (Character.isDigit(c)) {
                    int num = Character.getNumericValue(c);
                    if (num >= 1 && num <= 6) {
                        validateAndColorFields(txt, row, col, num);
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

    private void setButtonEvents() {
        button1.setOnAction(event -> insertNumberIntoActiveTextField(1));
        button2.setOnAction(event -> insertNumberIntoActiveTextField(2));
        button3.setOnAction(event -> insertNumberIntoActiveTextField(3));
        button4.setOnAction(event -> insertNumberIntoActiveTextField(4));
        button5.setOnAction(event -> insertNumberIntoActiveTextField(5));
        button6.setOnAction(event -> insertNumberIntoActiveTextField(6));
    }
    @Override
    public void insertNumberIntoActiveTextField(int number) {
        if (activeTextField != null) {
            int row = 0, col = 0;

            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 6; j++){
                    if (activeTextField == textFields.get(i).get(j)){
                        row = i;
                        col = j;
                        break;
                    }
                }
            }
            activeTextField.setText(String.valueOf(number));
            validateAndColorFields(activeTextField, row, col, number);
        }
    }

    @Override
    public void validateAndColorFields(TextField txt, int row, int col, int number) {
        game.getActualBoard().get(row).set(col, number);
        if (game.getActualBoard().get(row).get(col) != 0) {
            currentMessage += (Board.verifyNum(game.getActualBoard(), number, row, col) ?
            messagesBox[0][new Random().nextInt(3)] + "\n" :
            messagesBox[1][new Random().nextInt(3)] + "\n");
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int temp = game.getActualBoard().get(i).get(j);
                if (temp == 0 || textFields.get(i).get(j).isDisabled()) {
                    continue;
                }
                if (Board.verifyNum(game.getActualBoard(), temp, i, j)) {
                    textFields.get(i).get(j).setStyle("-fx-font-size: 25px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: darkgreen;" +
                            "-fx-background-color: lightgreen;" +
                            "-fx-border-color: green;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-radius: 5px;" +
                            "-fx-text-alignment: center;");
                } else {
                    textFields.get(i).get(j).setStyle("-fx-font-size: 25px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-text-fill: darkred;" +
                            "-fx-background-color: lightcoral;" +
                            "-fx-border-color: red;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-radius: 5px;" +
                            "-fx-text-alignment: center;");
                }
            }
        }
        messagesLabel.setText(currentMessage);
        if (game.isSolved()) {
            handleWinEvent();
        }
    }

    public void handleHelp(ActionEvent event) {
        if (helpsLeft > 0) {
            Random rand = new Random();
            int i, j, num, actualNum;
            do {
                i = rand.nextInt(6);
                j = rand.nextInt(6);
                actualNum = game.getActualBoard().get(i).get(j);
                num = game.getSolvedBoard().get(i).get(j);
            } while (actualNum == num);

            TextField txt = textFields.get(i).get(j);
            txt.setText(Integer.toString(num));
            helpsLeft--;
            helpsLabel.setText("Ayudas restantes: " + helpsLeft);
            currentMessage += messagesBox[2][new Random().nextInt(3)] + "\n";
            validateAndColorFields(txt, i, j, num);
        } else{
            currentMessage += messagesBox[3][new Random().nextInt(3)] + "\n";
        }
    }

    public void handleExit(ActionEvent event) throws IOException {
        GameStage.deletedInstance();
        WelcomeStage.getInstance();
    }

    public void handleRestart(ActionEvent event){
        stopTimer();
        helpsLabel.setText("Ayudas restantes: 3");
        game = new Game();
        textFields = new ArrayList<>();
        secondsPassed = 0;
        activeTextField = null;
        helpsLeft = 3;
        boardGrid.getChildren().clear();
        textFields.clear();
        createTextFields();
        startTimer();
        setButtonEvents();
    }

    public void handleWinEvent(){
        messagesLabel.setText(messagesBox[4][new Random().nextInt(3)] + "\n");
    }
}
