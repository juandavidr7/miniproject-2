package com.example.miniproject2.controller;

import com.example.miniproject2.view.GameStage;
import com.example.miniproject2.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * The {@code WelcomeController} class handles the logic and user interactions
 * for the welcome stage of the Sudoku game. It provides methods to initialize
 * the stage, handle button actions, and display instructions and credits.
 */
public class WelcomeController {

    @FXML
    private Button closeButton;

    @FXML
    private ImageView titleImage;

    /**
     * Initializes the welcome stage by loading the title image.
     */
    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("/com/example/miniproject2/images/title.png"));
        titleImage.setImage(image);
    }

    /**
     * Closes the welcome stage when the close button is pressed.
     *
     * @param event The action event triggered by pressing the close button.
     */
    @FXML
    void setOnCloseRequest(ActionEvent event) {
        WelcomeStage.deletedInstance();
    }

    /**
     * Starts the game by closing the welcome stage and opening the game stage.
     *
     * @param event The action event triggered by starting the game.
     * @throws IOException If an error occurs while loading the game stage.
     */
    @FXML
    void onStartPlay(ActionEvent event) throws IOException {
        WelcomeStage.closeInstance();
        GameStage.getInstance();
    }

    /**
     * Displays a modal window with instructions for playing the game.
     *
     * @param event The action event triggered by showing the instructions.
     */
    @FXML
    void OnShowInstruccion(ActionEvent event) {
        Stage instructionStage = new Stage();
        instructionStage.setTitle("Instrucciones");

        instructionStage.initModality(Modality.WINDOW_MODAL);
        instructionStage.initStyle(StageStyle.UNDECORATED);

        Label titleLabel = new Label("INSTRUCCIONES");
        titleLabel.setStyle("-fx-text-fill: white;" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10px;");

        Label instructionLabel1 = new Label("1. Completa la cuadrícula 6x6 con los números del 1 al 6.");
        Label instructionLabel2 = new Label("2. Asegúrate de que cada fila contenga todos los números del 1 al 6 sin repetir.");
        Label instructionLabel3 = new Label("3. Asegúrate de que cada columna contenga todos los números del 1 al 6 sin repetir.");
        Label instructionLabel4 = new Label("4. Asegúrate de que cada subcuadrícula 2x3 contenga todos los números del 1 al 6 sin repetir.");
        Label instructionLabel5 = new Label("5. Solo se muestran algunos números de antemano. Llena los espacios vacíos correctamente.");
        Label instructionLabel6 = new Label("6. Gana completando la cuadrícula siguiendo las reglas sin errores.");

        instructionLabel1.setStyle("-fx-text-fill: white;");
        instructionLabel2.setStyle("-fx-text-fill: white;");
        instructionLabel3.setStyle("-fx-text-fill: white;");
        instructionLabel4.setStyle("-fx-text-fill: white;");
        instructionLabel5.setStyle("-fx-text-fill: white;");
        instructionLabel6.setStyle("-fx-text-fill: white;");

        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> instructionStage.close());
        closeButton.setStyle(
                "-fx-background-color: #55ff00;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 5px 10px;"
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, instructionLabel1, instructionLabel2, instructionLabel3, instructionLabel4, instructionLabel5, instructionLabel6, closeButton);
        layout.setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 350);
        instructionStage.setScene(scene);
        instructionStage.showAndWait();
    }

    /**
     * Displays a modal window with credits information about the game.
     *
     * @param event The action event triggered by showing the credits.
     */
    @FXML
    void OnShowCredits(ActionEvent event) {
        Stage creditsStage = new Stage();
        creditsStage.setTitle("Créditos");

        creditsStage.initModality(Modality.WINDOW_MODAL);
        creditsStage.initStyle(StageStyle.UNDECORATED);

        Stage primaryStage = (Stage) closeButton.getScene().getWindow();
        creditsStage.initOwner(primaryStage);

        Label titleLabel = new Label("CRÉDITOS");
        titleLabel.setStyle("-fx-text-fill: white;" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 10px;");

        Label creditLabel1 = new Label("Desarrollado por: Juan Sebástian Sierra y Juan David Rincón");
        Label creditLabel2 = new Label("Códigos: 202342032 - 202343656");
        Label creditLabel3 = new Label("Correos: juan.rincon.lopez@correounivalle.edu.co - juan.sierra.cruz@correounivalle.edu.co");
        Label creditLabel4 = new Label("Materia: Programación Orientada a Eventos - 2024-2");

        creditLabel1.setStyle("-fx-text-fill: white;");
        creditLabel2.setStyle("-fx-text-fill: white;");
        creditLabel3.setStyle("-fx-text-fill: white;");
        creditLabel4.setStyle("-fx-text-fill: white;");

        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> creditsStage.close());
        closeButton.setStyle(
                "-fx-background-color: #55ff00;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-padding: 5px 10px;"
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, creditLabel1, creditLabel2, creditLabel3, creditLabel4, closeButton);
        layout.setStyle("-fx-background-color: rgba(0,0,0,0.4);");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 250);
        creditsStage.setScene(scene);
        creditsStage.showAndWait();
    }
}
