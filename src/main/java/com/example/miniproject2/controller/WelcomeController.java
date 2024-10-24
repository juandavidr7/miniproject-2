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

public class WelcomeController {

    @FXML
    private Button closeButton;

    @FXML
    private ImageView titleImage;

    @FXML
    public void initialize() {

        Image image = new Image(getClass().getResourceAsStream("/com/example/miniproject2/images/title.png"));

        titleImage.setImage(image);
    }

    @FXML
    void setOnCloseRequest(ActionEvent event) {
        WelcomeStage.deletedInstance();
    }
    @FXML
    void onStartPlay(ActionEvent event) throws IOException {
        WelcomeStage.closeInstance();
        GameStage.getInstance();
    }


    @FXML
    void OnShowInstruccion(ActionEvent event) {
        Stage instructionStage = new Stage();
        instructionStage.setTitle("Instrucciones");

        // Configure the instruction stage as a modal window
        instructionStage.initModality(Modality.WINDOW_MODAL);
        instructionStage.initStyle(StageStyle.UNDECORATED);


        // Create title label for instructions
        Label titleLabel = new Label("INSTRUCCIONES");
        titleLabel.setStyle("-fx-text-fill: white;" +  // White color
                "-fx-font-size: 24px;" +   // Large font size
                "-fx-font-weight: bold;" + // Bold font
                "-fx-padding: 10px;");     // Padding around the text

        // Create labels for each instruction
        Label instructionLabel1 = new Label("1. Completa la cuadrícula 6x6 con los números del 1 al 6.");
        Label instructionLabel2 = new Label("2. Asegúrate de que cada fila contenga todos los números del 1 al 6 sin repetir.");
        Label instructionLabel3 = new Label("3. Asegúrate de que cada columna contenga todos los números del 1 al 6 sin repetir.");
        Label instructionLabel4 = new Label("4. Asegúrate de que cada subcuadrícula 2x3 contenga todos los números del 1 al 6 sin repetir.");
        Label instructionLabel5 = new Label("5. Solo se muestran algunos números de antemano. Llena los espacios vacíos correctamente.");
        Label instructionLabel6 = new Label("6. Gana completando la cuadrícula siguiendo las reglas sin errores.");


        // Set style for instruction labels
        instructionLabel1.setStyle("-fx-text-fill: white;");
        instructionLabel2.setStyle("-fx-text-fill: white;");
        instructionLabel3.setStyle("-fx-text-fill: white;");
        instructionLabel4.setStyle("-fx-text-fill: white;");
        instructionLabel5.setStyle("-fx-text-fill: white;");
        instructionLabel6.setStyle("-fx-text-fill: white;");

        // Create close button for the instruction stage
        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> instructionStage.close());
        closeButton.setStyle(
                "-fx-background-color: #55ff00;" +         // Red background
                        "-fx-text-fill: white;" +              // White text
                        "-fx-font-weight: bold;" +             // Bold font
                        "-fx-font-size: 14px;" +               // Font size
                        "-fx-border-color: white;" +           // White border
                        "-fx-border-width: 2px;" +             // Border thickness
                        "-fx-border-radius: 10px;" +           // Rounded borders
                        "-fx-background-radius: 10px;" +       // Rounded background
                        "-fx-padding: 5px 10px;"               // Internal padding
        );

        // Create layout for instructions and the close button
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, instructionLabel1, instructionLabel2, instructionLabel3, instructionLabel4, instructionLabel5, instructionLabel6, closeButton);
        layout.setStyle("-fx-background-color: rgba(0,0,0,0.4);"); // Dark background to highlight text
        layout.setAlignment(Pos.CENTER);

        // Create scene and set it to the instruction stage
        Scene scene = new Scene(layout, 500, 350);
        instructionStage.setScene(scene);

        // Show the instruction stage
        instructionStage.showAndWait();
    }

    @FXML
    void OnShowCredits(ActionEvent event) {
        Stage creditsStage = new Stage();
        creditsStage.setTitle("Créditos");

        creditsStage.initModality(Modality.WINDOW_MODAL);
        creditsStage.initStyle(StageStyle.UNDECORATED);

        Stage primaryStage = (Stage) closeButton.getScene().getWindow();
        creditsStage.initOwner(primaryStage);

        // Create title label for credits
        Label titleLabel = new Label("CRÉDITOS");
        titleLabel.setStyle("-fx-text-fill: white;" +  // White color
                "-fx-font-size: 24px;" +   // Large font size
                "-fx-font-weight: bold;" + // Bold font
                "-fx-padding: 10px;");     // Padding around the text

        // Create labels for credits information
        Label creditLabel1 = new Label("Desarrollado por: Juan Sebástian Sierra y Juan David Rincón");
        Label creditLabel2 = new Label("Códigos: 202342032");
        Label creditLabel3 = new Label("Correo: juan.rincon.lopez@correounivalle.edu.co");
        Label creditLabel4 = new Label("Materia: Programación Orientada a Eventos - 2024-2");

        // Set style for credit labels
        creditLabel1.setStyle("-fx-text-fill: white;");
        creditLabel2.setStyle("-fx-text-fill: white;");
        creditLabel3.setStyle("-fx-text-fill: white;");
        creditLabel4.setStyle("-fx-text-fill: white;");

        // Create close button for the credits stage
        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> creditsStage.close());
        closeButton.setStyle(
                "-fx-background-color: #55ff00;" +         // Red background
                        "-fx-text-fill: white;" +              // White text
                        "-fx-font-weight: bold;" +             // Bold font
                        "-fx-font-size: 14px;" +               // Font size
                        "-fx-border-color: white;" +           // White border
                        "-fx-border-width: 2px;" +             // Border thickness
                        "-fx-border-radius: 10px;" +           // Rounded borders
                        "-fx-background-radius: 10px;" +       // Rounded background
                        "-fx-padding: 5px 10px;"               // Internal padding
        );

        // Create layout for credits and the close button
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, creditLabel1, creditLabel2, creditLabel3, creditLabel4, closeButton);
        layout.setStyle("-fx-background-color: rgba(0,0,0,0.4);"); // Dark background to highlight text
        layout.setAlignment(Pos.CENTER);

        // Create scene and set it to the credits stage
        Scene scene = new Scene(layout, 350, 250);
        creditsStage.setScene(scene);

        // Show the credits stage
        creditsStage.showAndWait();

    }
}
