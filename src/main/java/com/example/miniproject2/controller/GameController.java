package com.example.miniproject2.controller;

import com.example.miniproject2.model.Board;
import com.example.miniproject2.model.Game;
import com.example.miniproject2.view.GameStage;
import com.example.miniproject2.view.WelcomeStage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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
    private Button button1, button2, button3, button4, button5, button6;
    @FXML
    private Button buttonStartTime;
    @FXML
    private VBox messagesBox;
    @FXML
    private Label helpsLabel;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane mainGridPane;
    @FXML
    private GridPane boardGrid1, boardGrid2, boardGrid3, boardGrid4, boardGrid5, boardGrid6;

    private GridPane[][] gridPanes;
    private final String[][] messages = {
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

    @FXML
    public void initialize() {
        for (int i = 0; i < 6; i++){
            textFields.add(new ArrayList<>());
        }
        gridPanes = new GridPane[][]{
                {boardGrid1, boardGrid2},
                {boardGrid3, boardGrid4},
                {boardGrid5, boardGrid6}};
        scrollPane.setFitToWidth(true);
        messagesBox.setFillWidth(true);
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
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 2; blockCol++) {
                for (int row = 0; row < 2; row++) {
                    for (int col = 0; col < 3; col++) {
                        int i = blockRow * 2 + row;
                        int j = blockCol * 3 + col;

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

                        gridPanes[blockRow][blockCol].add(txt, col, row);
                        textFields.get(i).add(txt);
                        txt.setOnMouseClicked(event -> activeTextField = txt);
                        onKeyTxtTyped(txt, i, j);
                    }
                }

                gridPanes[blockRow][blockCol].setStyle("-fx-border-color: white; -fx-border-width: 5px; -fx-border-radius: 2px;");
                boardGrid.add(gridPanes[blockRow][blockCol], blockCol, blockRow);
            }
        }
    }

    private void onKeyTxtTyped(TextField txt, int row, int col) {
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.equals(newValue, "")){
                validateAndColorFields(txt, row, col, 0, false);
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
                        validateAndColorFields(txt, row, col, num, false);
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
            validateAndColorFields(activeTextField, row, col, number, false);
        }
    }

    @Override
    public void validateAndColorFields(TextField txt, int row, int col, int number, boolean isClue) {
        game.getActualBoard().get(row).set(col, number);

        if (number != 0 && !isClue) {
            if (Board.verifyNum(game.getActualBoard(), number, row, col)) {
                showMessage("correct");
            } else {
                showMessage("incorrect");
            }
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
            showMessage("help");
            validateAndColorFields(txt, i, j, num, true);
        } else{
            showMessage("noHelpsLeft");
        }
    }

    public void handleExit(ActionEvent event) throws IOException {
        GameStage.deletedInstance();
    }

    public void handleRestart(ActionEvent event) {
        stopTimer();
        game = new Game();
        helpsLabel.setText("Ayudas restantes: 3");
        helpsLeft = 3;
        secondsPassed = 0;
        activeTextField = null;
        textFields = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            textFields.add(new ArrayList<>());
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 2; j++){
                gridPanes[i][j].getChildren().clear();
            }
        }
        createTextFields();
        startTimer();
        setButtonEvents();
    }

    public void handleBackToMenu(ActionEvent event) throws  IOException{
        GameStage.closeInstance();
        WelcomeStage.getInstance();
    }

    public void handleWinEvent(){
        showMessage("win");
        mainGridPane.getChildren().removeIf(node -> {
                    Integer nodeColumn = GridPane.getColumnIndex(node);
                    Integer nodeRow = GridPane.getRowIndex(node);

                    nodeColumn = (nodeColumn == null) ? -1 : nodeColumn;
                    nodeRow = (nodeRow == null) ? -1 : nodeRow;

                    return nodeColumn == 1 && nodeRow == 2;
                }
        );
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                textFields.get(i).get(j).setDisable(true);
            }
        }
        stopTimer();
        Label winLabel = new Label("HAS GANADO!!");
        winLabel.setStyle("-fx-background-color: transparent;" +
                "-fx-font-size: 50px;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Arial', sans-serif;" +
                "-fx-font-weight: bold;" +
                "-fx-alignment: center;" +
                "-fx-text-alignment: center;" +
                "-fx-border-color: transparent;" +
                "-fx-effect: dropshadow( gaussian , rgba(0, 255, 0, 0.75) , 10, 0.5 , 0 , 0 );");
        mainGridPane.add(winLabel,1 , 2);
    }

    public void showMessage(String type){
        String currentMessage = "";

        if (Objects.equals(type, "correct")){
            currentMessage = messages[0][new Random().nextInt(3)];
        } else if (Objects.equals(type, "incorrect")) {
            currentMessage = messages[1][new Random().nextInt(3)];
        } else if (Objects.equals(type, "help")){
            currentMessage = messages[2][new Random().nextInt(3)];
        } else if (Objects.equals(type, "noHelpsLeft")){
            currentMessage = messages[3][new Random().nextInt(3)];
        } else if (Objects.equals(type, "win")){
            currentMessage = messages[4][new Random().nextInt(3)];
        }
        Label message = new Label(currentMessage);
        message.setWrapText(true);
        messagesBox.getChildren().add(message);
        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }
}
